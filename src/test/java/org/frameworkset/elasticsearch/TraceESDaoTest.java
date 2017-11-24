package org.frameworkset.elasticsearch;

import org.frameworkset.elasticsearch.client.ClientInterface;
import org.frameworkset.elasticsearch.client.ClientUtil;
import org.frameworkset.elasticsearch.entity.*;
import org.frameworkset.elasticsearch.entity.TraceExtraCriteria;
import org.frameworkset.spi.remote.http.MapResponseHandler;
import org.junit.Test;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class TraceESDaoTest {
	@Test
	public void healthCheck(){
		ClientInterface rest = ElasticSearchHelper.getRestClientUtil();
		String  status = rest.executeHttp("/",null,ClientUtil.HTTP_GET);
		System.out.println(status);
	}
	@Test
	public void testQueryPeriodsTopN() throws ParseException, IOException {
		TraceESDao traceESDao = new TraceESDao();
		TraceExtraCriteria traceExtraCriteria = new TraceExtraCriteria();
		traceExtraCriteria.setApplication("testweb88");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		traceExtraCriteria.setStartTime(dateFormat.parse("2017-09-02 00:00:00").getTime());
		traceExtraCriteria.setEndTime(dateFormat.parse("2017-09-14 00:00:00").getTime());
		ESAggDatas<LongAggRangeHit> topN = traceESDao.queryPeriodsTopN(traceExtraCriteria);
		System.out.println(topN);
	}
	@Test
	public void testCondition() throws ParseException{
		TraceESDao traceESDao = new TraceESDao();
		// 响应报文
		// 开始时间

		org.frameworkset.elasticsearch.TraceExtraCriteria traceExtraCriteria = new org.frameworkset.elasticsearch.TraceExtraCriteria();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		traceExtraCriteria.setStartTime(dateFormat.parse("2017-10-24 00:00:00").getTime());
		traceExtraCriteria.setEndTime(dateFormat.parse("2017-11-28 23:00:00").getTime());


		// 查询条件
		String queryCondition = "/testweb/admin/content.page";
		// 查询状态：all 全部 success 处理成功 fail 处理失败
		String queryStatus = "all";

		if(queryCondition != null){

			queryCondition = ClientUtil.handleElasticSearchSpecialChars(queryCondition);
//            queryCondition = queryCondition.replace("-","\\\\-");
			//queryCondition = ClientUtil.handleElasticSearchSpecialChars(queryCondition);
		}
		String queryAction = null;
		if(queryAction == null )
			queryAction = "trace";
		traceExtraCriteria.setQueryAction(queryAction);
		traceExtraCriteria.setQueryCondition(queryCondition);
		traceExtraCriteria.setQueryStatus(queryStatus);
		traceExtraCriteria.setApplication("testweb88");
		traceExtraCriteria.setOrderBy("time");
		ClientInterface clientUtil = ElasticSearchHelper.getConfigRestClientUtil("esmapper/estrace/ESTracesMapper.xml");
		String retString = clientUtil.executeRequest("trace-*/_search?explain","queryServiceByCondition",traceExtraCriteria);
		System.out.println(retString);

		retString = clientUtil.executeRequest("trace-*/_search","queryServiceByCondition",traceExtraCriteria);
		System.out.println(retString);
		//全文检索结果
		ESDatas<Traces> response = clientUtil.searchList("trace-*/_search","queryServiceByCondition",traceExtraCriteria,Traces.class);
		//精确检索
		traceExtraCriteria.setExactSearch(true);
		traceExtraCriteria.setSearchFields(new String[]{"rpc"});
		response = clientUtil.searchList("trace-*/_search","exactQueryServiceByCondition",traceExtraCriteria,Traces.class);
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




		System.out.println();
	}
	@Test
	public void testQueryTraces() throws ParseException {
		TraceESDao traceESDao = new TraceESDao();
		TraceExtraCriteria traceExtraCriteria = new TraceExtraCriteria();
		traceExtraCriteria.setApplication("testweb88");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		traceExtraCriteria.setStartTime(dateFormat.parse("2017-09-02 00:00:00").getTime());
		traceExtraCriteria.setEndTime(dateFormat.parse("2017-09-13 00:00:00").getTime());
		ESDatas<Traces> data = traceESDao.queryTracesByCriteria(traceExtraCriteria);

	}
	@Test
	public void testQueryServiceTracesAvgElapsed() throws ParseException{
		TraceESDao traceESDao = new TraceESDao();
		TraceExtraCriteria traceExtraCriteria = new TraceExtraCriteria();
		traceExtraCriteria.setApplication("testweb88");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		traceExtraCriteria.setStartTime(dateFormat.parse("2017-09-02 00:00:00").getTime());
		traceExtraCriteria.setEndTime(dateFormat.parse("2017-09-14 00:00:00").getTime());
		traceESDao.queryServiceTracesAvgElapsed(traceExtraCriteria);
	}
	@Test
	public void testFormat() throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		Date d = format.parse("2017-08-31T16:18:27.285Z");
		System.out.println(d);
	}
	@Test
	public void testQueryDocMapping(){
		TraceESDao traceESDao = new TraceESDao();
		System.out.println(traceESDao.queryTracesMapping("tracesql-2017.09.15"));
//		http://127.0.0.1:9200/productindex/_mapping?pretty
	}

	@Test
	public void testDropDocMapping(){
		TraceESDao traceESDao = new TraceESDao();
		System.out.println(traceESDao.deleteMapping("tracesql-*"));
//		http://127.0.0.1:9200/productindex/_mapping?pretty
	}

	@Test
	public void refresh(){
		List<ESIndice> indexes = ElasticSearchHelper.getRestClientUtil().getIndexes();
		for(ESIndice indice:indexes) {
			System.out.println(ElasticSearchHelper.getRestClientUtil().refreshIndexInterval(indice.getIndex(), 30));
		}
	}

	@Test
	public void refreshAll(){
		 System.out.println(ElasticSearchHelper.getRestClientUtil().refreshIndexInterval( 1));

	}

	@Test
	public void refreshAllPreserve(){
		System.out.println(ElasticSearchHelper.getRestClientUtil().refreshIndexInterval( 30,true));

	}

	@Test
	public void refreshPattern(){

			System.out.println(ElasticSearchHelper.getRestClientUtil().refreshIndexInterval("trace-*", 30));

	}

	@Test
	public void testExactCondition() throws Exception{
		// 响应报文
		// 开始时间

		TraceExtraCriteria traceExtraCriteria = new TraceExtraCriteria();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		traceExtraCriteria.setStartTime(dateFormat.parse("2017-10-24 00:00:00").getTime());
		traceExtraCriteria.setEndTime(dateFormat.parse("2017-11-28 23:00:00").getTime());


		// 查询条件
		String queryCondition = "/testweb/sysmanager/role/toroleauthset.page";
		// 查询状态：all 全部 success 处理成功 fail 处理失败
		String queryStatus = "all";

		if(queryCondition != null){

//			queryCondition = ClientUtil.handleElasticSearchSpecialChars(queryCondition);
//            queryCondition = queryCondition.replace("-","\\\\-");
			//queryCondition = ClientUtil.handleElasticSearchSpecialChars(queryCondition);
		}
		String queryAction = null;
		if(queryAction == null )
			queryAction = "trace";
		traceExtraCriteria.setQueryAction(queryAction);
		traceExtraCriteria.setQueryCondition(queryCondition);
		traceExtraCriteria.setQueryStatus(queryStatus);
		traceExtraCriteria.setApplication("testweb88");
		traceExtraCriteria.setOrderBy("time");
		ClientInterface clientUtil = ElasticSearchHelper.getConfigRestClientUtil("esmapper/estrace/ESTracesMapper.xml");
		//全文检索结果
		ESDatas<Traces> response = null;
		//精确检索
		traceExtraCriteria.setExactSearch(true);
		traceExtraCriteria.setSearchFields(new String[]{"rpc","params"});
		MapRestResponse restResponse = clientUtil.search("trace-*/_search","exactQueryServiceByCondition",traceExtraCriteria);
		response = clientUtil.searchList("trace-*/_search","exactQueryServiceByCondition",traceExtraCriteria,Traces.class);
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




		System.out.println();
	}
	@Test
	public void testGetDocument(){
		String id = "AV9hXgBkioW8Iiove8hI";
		String indexName = "trace-2017.10.28";
		String indexType = "trace";
		ClientInterface clientUtil = ElasticSearchHelper.getRestClientUtil();
		MapSearchHit hit = clientUtil.getDocumentHit(indexName,indexType,id);
		System.out.println("");
	}

	@Test
	public void clusterHeathCheck(){
		ClientInterface clientUtil = ElasticSearchHelper.getRestClientUtil();
		//返回json格式健康状态
		String heath = clientUtil.executeHttp("_cluster/health?pretty",ClientInterface.HTTP_GET);
		System.out.println(heath);

	}

	@Test
	public void clusterState(){
		ClientInterface clientUtil = ElasticSearchHelper.getRestClientUtil();
		//返回json格式集群状态
		String state = clientUtil.executeHttp("_cluster/state?pretty",ClientInterface.HTTP_GET);
		System.out.println(state);

	}

	@Test
	public void clusterMapState(){
		ClientInterface clientUtil = ElasticSearchHelper.getRestClientUtil();
		//返回map类型集群状态信息
		Map<String,Object> state = clientUtil.executeHttp("_cluster/state",ClientInterface.HTTP_GET,new MapResponseHandler());
		clientUtil.executeHttp("_cluster/state",ClientInterface.HTTP_GET,new MapResponseHandler());
		System.out.println(state);

	}
}
