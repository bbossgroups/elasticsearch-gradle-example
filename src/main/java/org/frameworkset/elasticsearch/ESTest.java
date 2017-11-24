package org.frameworkset.elasticsearch;

import org.frameworkset.elasticsearch.client.ClientInterface;
import org.frameworkset.elasticsearch.client.ClientUtil;
import org.frameworkset.elasticsearch.entity.Demo;
import org.frameworkset.elasticsearch.entity.ESDatas;
import org.frameworkset.elasticsearch.entity.JsonDataResult;
import org.frameworkset.elasticsearch.entity.Traces;
import org.frameworkset.spi.DefaultApplicationContext;
import org.frameworkset.spi.remote.http.MapResponseHandler;
import org.frameworkset.spi.remote.http.StringResponseHandler;
import org.frameworkset.util.FastDateFormat;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
 

public class ESTest {

	public ESTest() {
		// TODO Auto-generated constructor stub
	}

	public void testCondition() throws ParseException{
		TraceESDao traceESDao = new TraceESDao();
		// 响应报文
		// 开始时间

		TraceExtraCriteria traceExtraCriteria = new TraceExtraCriteria();
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
	public void testFastDateFormat() throws ParseException{
		String data = "2005-01-10 12:00:00";
		String format = "yyyy-MM-dd HH:mm:ss";
		FastDateFormat df = FastDateFormat.getInstance(format,TimeZone.getTimeZone("Asia/Shanghai"));
		Object ojb = df.parseObject(data);
		System.out.println();
	}
	 
	public void healthCheck(){
		ClientInterface rest = ElasticSearchHelper.getRestClientUtil("elasticSearch");
		String  status = rest.executeHttp("/",null,ClientUtil.HTTP_GET);
		System.out.println(status);
	}
 
	public void test() throws Exception{
		DefaultApplicationContext context = DefaultApplicationContext.getApplicationContext("conf/elasticsearch.xml");
		ElasticSearch elasticSearchSink = context.getTBeanObject("elasticSearch", ElasticSearch.class);
//		ElasticSearch restelasticSearchSink = context.getTBeanObject("restelasticSearch", ElasticSearch.class);
		
		ClientInterface clientUtil = elasticSearchSink.getRestClientUtil();
		String entity = "{"+
    "\"aggs\": {"+
    "\"top_tags\": {"+
		    "\"terms\": {"+
		    "\"field\": \"rpc.keyword\","+
		    "\"size\": 30"+
		    "},"+
    "\"aggs\": {"+
		    "\"top_sales_hits\": {"+
		    "\"top_hits\": {"+
		    "\"sort\": ["+
		               "{"+
    "\"collectorAcceptTime\": {"+
		            	    "\"order\": \"desc\""+
		            	        "}"+
    "}"+
    "],"+
    "\"_source\": {"+
		            	    "\"includes\": [ \"collectorAcceptTime\", \"rpc\" ]"+
		            	    	    "},"+
    "\"size\" : 1"+
    "}"+
    "}"+
    "}"+
    "}"+
    "}"+
    "}";
		String response = (String) clientUtil.executeRequest("trace-*/_search?size=0",entity);
		
		System.out.println(response);
		
	}
 
	public void querey() throws Exception
	{
		DefaultApplicationContext context = DefaultApplicationContext.getApplicationContext("conf/elasticsearch.xml");
		ElasticSearch elasticSearchSink = context.getTBeanObject("elasticSearch", ElasticSearch.class);
//		ElasticSearch restelasticSearchSink = context.getTBeanObject("restelasticSearch", ElasticSearch.class);
		
		ClientInterface clientUtil = elasticSearchSink.getRestClientUtil();
		String entiry = "{\"query\" : {\"term\" : { \"rpc\" : \"content.page\" }}}";
		String response = (String) clientUtil.executeRequest("trace-*/_search",entiry);
		
		System.out.println(response);
	}
 
	public void testConfig() throws ParseException{
//		ClientInterface clientUtil = ElasticSearchHelper.getConfigRestClientUtil("traceElasticSearch",//可以指定elasticSearch服务器
//				"esmapper/estrace/ESTracesMapper.xml");
		ClientInterface clientUtil = ElasticSearchHelper.getConfigRestClientUtil("esmapper/estrace/ESTracesMapper.xml");
		TraceExtraCriteria traceExtraCriteria = new TraceExtraCriteria();
		traceExtraCriteria.setApplication("testweb1");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		traceExtraCriteria.setStartTime(dateFormat.parse("2017-09-02 00:00:00").getTime());
		traceExtraCriteria.setEndTime(dateFormat.parse("2017-09-10 00:00:00").getTime());
		 String data = clientUtil.executeRequest("trace-*/_search","queryPeriodsTopN",traceExtraCriteria,new StringResponseHandler());
	        System.out.println("------------------------------");
	        System.out.println(data);
	        System.out.println("------------------------------");
	        
	        Map<String,Object> response = clientUtil.executeRequest("trace-*/_search","queryPeriodsTopN",traceExtraCriteria,new MapResponseHandler());
	        if(response.containsKey("error")){
	            return ;
	        }
	}
	
 
	public void testSearh() throws ParseException{

		ClientInterface clientUtil = ElasticSearchHelper.getConfigRestClientUtil("org/frameworkset/elasticsearch/ESTracesMapper.xml");
		TraceExtraCriteria traceExtraCriteria = new TraceExtraCriteria();
		traceExtraCriteria.setApplication("testweb1");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		traceExtraCriteria.setStartTime(dateFormat.parse("2017-09-02 00:00:00").getTime());
		traceExtraCriteria.setEndTime(dateFormat.parse("2017-09-10 00:00:00").getTime());
		String data = clientUtil.executeRequest("trace-*/_search","queryPeriodsTopN",traceExtraCriteria,new StringResponseHandler());
		System.out.println("------------------------------");
		System.out.println(data);
		System.out.println("------------------------------");

		Map<String,Object> response = clientUtil.executeRequest("trace-*/_search","queryPeriodsTopN",traceExtraCriteria,new MapResponseHandler());
		if(response.containsKey("error")){
			return ;
		}
	}

 
	public void testTempate() throws ParseException{

		ClientInterface clientUtil = ElasticSearchHelper.getConfigRestClientUtil("esmapper/estrace/ESTemplate.xml");
		//创建模板
		String response = clientUtil.createTempate("demotemplate_1",//模板名称
				"demoTemplate");//模板对应的脚本名称，在esmapper/estrace/ESTemplate.xml中配置
		System.out.println("createTempate-------------------------");
		System.out.println(response);
		//获取模板
		/**
		 * 指定模板
		 * /_template/demoTemplate_1
		 * /_template/demoTemplate*
		 * 所有模板 /_template
		 *
		 */
		String template = clientUtil.executeHttp("/_template/demotemplate_1",ClientUtil.HTTP_GET);
		System.out.println("HTTP_GET-------------------------");
		System.out.println(template);
		//删除模板
		template = clientUtil.executeHttp("/_template/demotemplate_1",ClientUtil.HTTP_DELETE);
		System.out.println("HTTP_DELETE-------------------------");
		System.out.println(template);

		template = clientUtil.executeHttp("/_template/demotemplate_1",ClientUtil.HTTP_GET);
		System.out.println("HTTP_GET after delete-------------------------");
		System.out.println(template);
	}


	 
	public void testCreateTempate() throws ParseException{

		ClientInterface clientUtil = ElasticSearchHelper.getConfigRestClientUtil("esmapper/estrace/ESTemplate.xml");
		//创建模板
		String response = clientUtil.createTempate("demotemplate_1",//模板名称
				"demoTemplate");//模板对应的脚本名称，在estrace/ESTemplate.xml中配置
		System.out.println("createTempate-------------------------");
		System.out.println(response);
		//获取模板
		/**
		 * 指定模板
		 * /_template/demoTemplate_1
		 * /_template/demoTemplate*
		 * 所有模板 /_template
		 *
		 */
		String template = clientUtil.executeHttp("/_template/demotemplate_1",ClientUtil.HTTP_GET);
		System.out.println("HTTP_GET-------------------------");
		System.out.println(template);

	}
	
	public void testGetmapping(){
		SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
		String date = format.format(new Date());
		ClientInterface clientUtil = ElasticSearchHelper.getRestClientUtil();
		System.out.println(clientUtil.getIndice("demo-"+date));
		clientUtil.dropIndice("demo-"+date);
	}
	
	public void testAddDateDocument() throws ParseException{
		testGetmapping();
		SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
		String date = format.format(new Date());
		ClientInterface clientUtil = ElasticSearchHelper.getConfigRestClientUtil("esmapper/estrace/ESTracesMapper.xml");
		Demo demo = new Demo();
		demo.setDemoId(5l);
		demo.setAgentStarttime(new Date());
		demo.setApplicationName("blackcatdemo");
		demo.setContentbody("this is content body");
		//创建模板
		String response = clientUtil.addDateDocument("demo",//索引表,自动添加日期信息到索引表名称中
				"demo",//索引类型
				"createDemoDocument",//创建文档对应的脚本名称，在esmapper/estrace/ESTracesMapper.xml中配置
				demo);

		System.out.println("addDateDocument-------------------------");
		System.out.println(response);

		response = clientUtil.getDocument("demo-"+date,//索引表，手动指定日期信息
				"demo",//索引类型
				"5");
		System.out.println("getDocument-------------------------");
		System.out.println(response);

		demo = clientUtil.getDocument("demo-"+date,//索引表
				"demo",//索引类型
				"5",//创建文档对应的脚本名称，在esmapper/estrace/ESTracesMapper.xml中配置
				Demo.class);
	}
	
	
	public void testBulkAddDateDocument() throws ParseException{
		testGetmapping();
		SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
		String date = format.format(new Date());
		ClientInterface clientUtil = ElasticSearchHelper.getConfigRestClientUtil("esmapper/estrace/ESTracesMapper.xml");
		List<Demo> demos = new ArrayList<>();
		Demo demo = new Demo();
		demo.setDemoId(2l);
		demo.setAgentStarttime(new Date());
		demo.setApplicationName("blackcatdemo2");
		demo.setContentbody("this is content body2");
		demos.add(demo);

		demo = new Demo();
		demo.setDemoId(3l);
		demo.setAgentStarttime(new Date());
		demo.setApplicationName("blackcatdemo3");
		demo.setContentbody("this is content body3");
		demos.add(demo);

		//创建模板
		String response = clientUtil.addDateDocuments("demo",//索引表
				"demo",//索引类型
				"createDemoDocument",//创建文档对应的脚本名称，在esmapper/estrace/ESTracesMapper.xml中配置
				demos);

		System.out.println("addDateDocument-------------------------");
		System.out.println(response);

		response = clientUtil.getDocument("demo-"+date,//索引表
				"demo",//索引类型
				"2");
		System.out.println("getDocument-------------------------");
		System.out.println(response);

		demo = clientUtil.getDocument("demo-"+date,//索引表
				"demo",//索引类型
				"3",//创建文档对应的脚本名称，在esmapper/estrace/ESTracesMapper.xml中配置
				Demo.class);
	}

}
