package org.frameworkset.elasticsearch;

import org.frameworkset.elasticsearch.client.ClientUtil;
import org.frameworkset.elasticsearch.entity.*;
import org.frameworkset.elasticsearch.entity.TraceExtraCriteria;
import org.frameworkset.elasticsearch.handler.ESAggBucketHandle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 从Elastic Search里果询服务链路信息
 */
public class TraceESDao {
	protected ClientUtil clientUtil = null;
    private static Logger logger = LoggerFactory.getLogger(TraceESDao.class);   
    protected void init(){
        if(clientUtil == null)
        {
        	//加载配置文件
        	clientUtil = ElasticSearchHelper.getConfigRestClientUtil("esmapper/estrace/ESTracesMapper.xml");
        }
    }
    protected Long getLong(Object value){
		if(value != null) {
			if(value instanceof  Integer)
				return ((Integer) value).longValue();//: -1,
			else
				return (((Long) value));//: -1,
		}
		return null;
	}

	protected void handleData(Map<String,Object> elapsed_stats){
//		Map<String,Object>  elapsed_stats = (Map<String,Object>)data.get("elapsed_stats");
		Object value = elapsed_stats.get("avg");
		if(value == null)
			elapsed_stats.put("avg",0);
		value = elapsed_stats.get("min");
		if(value == null)
			elapsed_stats.put("min",0);
		value = elapsed_stats.get("max");
		if(value == null)
			elapsed_stats.put("max",0);
		value = elapsed_stats.get("sum");
		if(value == null)
			elapsed_stats.put("sum",0);
	}

	/**
	 * 获取索引表结构
	 * @param index
	 * @return
	 */
	public String queryTracesMapping(String index){

		init();
		String response = (String)clientUtil.getIndexMapping(index);

		return response;
	}

	/**
	 * 删除索引表结构
	 * @param index
	 * @return
	 */
	public String deleteMapping(String index){

		init();
		String response = clientUtil.dropIndice(index);

		return response;
	}
    public ESDatas<Traces> queryTracesByCriteria(TraceExtraCriteria traceExtraCriteria){
        init();
//        String datastr = clientUtil.executeRequest("trace-*/_search","queryTracesByCriteria",traceExtraCriteria);
        ESDatas<Traces> response = clientUtil.searchList("trace-*/_search","queryTracesByCriteria",traceExtraCriteria,Traces.class);
        if(response == null)
            return null;
        return response;

    }

	public ESDatas<TraceScatter> queryTraceScattersByCriteria(TraceExtraCriteria traceExtraCriteria){
		init();
		
//        String datastr = clientUtil.executeRequest("trace-*/_search","queryTracesByCriteria",traceExtraCriteria);//返回json报文的api
		
		//o/r mapping api
		ESDatas<TraceScatter> response = clientUtil.searchList("trace-*/_search","queryTracesByCriteria",traceExtraCriteria,TraceScatter.class);
		if(response == null)
			return null;
		return response;

	}

    /**
     * 根据条件查询服务链路信息，并根据平均耗时进行排序
     *
     * @param traceExtraCriteria 条件信息
     * @return 服务链路信息
     */
    public ESAggDatas<TraceLongAggHit> queryServiceTracesAvgElapsed(TraceExtraCriteria traceExtraCriteria){

        init();
        ESAggDatas<TraceLongAggHit> response = clientUtil.searchAgg("trace-*/_search","queryServiceTracesAvgElapsed",traceExtraCriteria,
                TraceLongAggHit.class,
                "traces","elapsed_stats",new ESAggBucketHandle<TraceLongAggHit>(){
                    @Override
                    public void bucketHandle(RestResponse result, Map<String, Object> bucket, TraceLongAggHit obj, String key) {
                    	Map<String, Object > success_stats = (Map<String, Object >)bucket.get("success_stats");
                    	if(success_stats != null){
                    		List buckets = (List)success_stats.get("buckets");
                    		for(int i = 0 ; i < buckets.size(); i ++){
	                    		Map data = (Map)buckets.get(i);                   		
	                    		
	                    		Integer vkey = (Integer)data.get("key");
	                    		Object value = data.get("doc_count");
	                    		long value_ = value instanceof Long ?(Long)value:((Integer)value).longValue();
                    			if(vkey == 0){
                    				obj.setSuccess(value_);
                    			}
                    			else if(vkey == 1){
                    				obj.setFailed(value_);
                    			}
                    		}
                    	}
                    }
                });
        return response;
    }


    /**
     * 查询在指定时间类，耗时时段的TOPN信息
     *
     * @return 各耗时时段TOPN
     */
    public ESAggDatas<LongAggRangeHit> queryPeriodsTopN(TraceExtraCriteria traceExtraCriteria) throws IOException {

        init();
        //String datastr = clientUtil.executeRequest("trace-*/_search","queryPeriodsTopN",traceExtraCriteria);

        ESAggDatas<LongAggRangeHit> response = clientUtil.searchAgg("trace-*/_search","queryPeriodsTopN",traceExtraCriteria,LongAggRangeHit.class,
                "elapsed_ranges","elapsed_stats");


        return response;

    }

    /**
     * 查询特定时间范围内容指定服务rpc的链路数据
     * @param traceExtraCriteria
     * @return
     */
    public JsonDataResult queryServiceDetailByRpc(TraceExtraCriteria traceExtraCriteria) {
        init();
        ESDatas<Traces> response = clientUtil.searchList("trace-*/_search","queryServiceDetailByRpc",traceExtraCriteria,Traces.class);
		JsonDataResult ret = new JsonDataResult();
		ret.setData(response.getDatas());
		ret.setTotalSize(response.getTotalSize());
		if(response.getAggregations() != null) {
			/**
			 *  "key_as_string": "2017-09-22T14:30:00.000+08:00",
			 "key": 1506061800000,
			 "doc_count": 1
			 */
			List<Map<String, Object>> traces_date_histogram = (List<Map<String, Object>>) response.getAggregations().get("traces_date_histogram").get("buckets");
			ret.setDateHistogram(traces_date_histogram);

		}
		return ret;

    }



    /**
     *  根据条件查询服务链路信息
     *
     * @param traceExtraCriteria 条件信息
     * @return
     */
    public JsonDataResult  queryServiceByCondition (TraceExtraCriteria traceExtraCriteria){
        init();

//        RootCause s;
//            String ret = clientUtil.refreshIndexInterval("trace-*",30);
//        String retString = clientUtil.executeRequest("trace-*/_search","queryServiceByCondition",traceExtraCriteria);
//        System.out.println(retString);
        ESDatas<Traces> response = clientUtil.searchList("trace-*/_search","queryServiceByCondition",traceExtraCriteria,Traces.class);
        JsonDataResult ret = new JsonDataResult();
        ret.setData(response.getDatas());
        ret.setTotalSize(response.getTotalSize());
        if(response.getAggregations() != null) {
            /**
             *  "key_as_string": "2017-09-22T14:30:00.000+08:00",
			 "key": 1506061800000,
			 "doc_count": 1
             */
            List<Map<String, Object>> traces_date_histogram = (List<Map<String, Object>>) response.getAggregations().get("traces_date_histogram").get("buckets");
            ret.setDateHistogram(traces_date_histogram);

        }
        return ret;

    }

    /**
     * 查询topn 慢链路服务
     * @param traceExtraCriteria
     * @return
     */
    public JsonDataResult<List<Traces>> querySlowTraceTopN(TraceExtraCriteria traceExtraCriteria){
        init();
        ESDatas<Traces> response = clientUtil.searchList("trace-*/_search","querySlowTraceTopN",traceExtraCriteria,Traces.class);
        JsonDataResult ret = new JsonDataResult();
        ret.setData(response.getDatas());
        ret.setTotalSize(response.getTotalSize());

        return ret;
    }
}
