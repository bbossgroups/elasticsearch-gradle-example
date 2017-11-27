package org.frameworkset.elasticsearch;

import org.frameworkset.elasticsearch.client.ClientInterface;
import org.frameworkset.elasticsearch.entity.Demo;
import org.junit.Test;

import java.util.*;

public class SuggestionTest {
	@Test
	public void testCreateSuggestion(){
		ClientInterface clientUtil = ElasticSearchHelper.getConfigRestClientUtil("esmapper/estrace/suggest.xml");
		try {
			//clientUtil.dropIndice("suggestion");
			String template = clientUtil.createIndiceMapping("suggestion","createSuggestIndice");
			System.out.println(template);


		} catch (ElasticSearchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void testIndex(){
		ClientInterface clientUtil = ElasticSearchHelper.getRestClientUtil();
		List<Demo> demos = new ArrayList<>();
		Demo demo = new Demo();
		demo.setDemoId(2l);
		demo.setAgentStarttime(new Date());
		demo.setApplicationName("blackcatdemo2");
		demo.setContentbody("this is content body2");
		demo.setName("刘德华");
		demos.add(demo);

		demo = new Demo();
		demo.setDemoId(3l);
		demo.setAgentStarttime(new Date());
		demo.setApplicationName("blackcatdemo3");
		demo.setContentbody("四大天王，这种文化很好，中华人民共和国");
		demo.setName("张学友");
		demos.add(demo);

		//创建模板
		String response = clientUtil.addDocuments("suggestion",//索引表
				"suggest",//索引类型
				demos);

		System.out.println("addDateDocument-------------------------");
		System.out.println(response);

		response = clientUtil.getDocument("suggestion",//索引表
				"suggest",//索引类型
				"2");
		System.out.println("getDocument-------------------------");
		System.out.println(response);

		demo = clientUtil.getDocument("suggestion",//索引表
				"suggest",//索引类型
				"3",//创建文档对应的脚本名称，在esmapper/estrace/ESTracesMapper.xml中配置
				Demo.class);
	}
	@Test
	public void testIndex1(){
		ClientInterface clientUtil = ElasticSearchHelper.getRestClientUtil();
		List<Demo> demos = new ArrayList<>();
		Demo demo = new Demo();
		demo.setDemoId(7l);
		demo.setAgentStarttime(new Date());
		demo.setApplicationName("blackcatdemo2");
		demo.setContentbody("this is content body2");
		demo.setName("四大天王，这种刘德华文化很好，张学友中华人民共和国刘德华");
		demos.add(demo);

		demo = new Demo();
		demo.setDemoId(8l);
		demo.setAgentStarttime(new Date());
		demo.setApplicationName("blackcatdemo3");
		demo.setContentbody("四大天王，这种文化很好，中华人民共和国");
		demo.setName("古巨基刘德华张学友");
		demos.add(demo);

		demo = new Demo();
		demo.setDemoId(9l);
		demo.setAgentStarttime(new Date());
		demo.setApplicationName("blackcatdemo3");
		demo.setContentbody("四大天王，这种文化很好，中华人民共和国");
		demo.setName("刘德华");
		demos.add(demo);

		demo = new Demo();
		demo.setDemoId(10l);
		demo.setAgentStarttime(new Date());
		demo.setApplicationName("blackcatdemo3");
		demo.setContentbody("四大天王，这种文化很好，中华人民共和国");
		demo.setName("刘德一");
		demos.add(demo);

		//创建模板
		String response = clientUtil.addDocuments("suggestion",//索引表
				"suggest",//索引类型
				demos);

		System.out.println("addDateDocument-------------------------");
		System.out.println(response);

		response = clientUtil.getDocument("suggestion",//索引表
				"suggest",//索引类型
				"10");
		System.out.println("getDocument-------------------------");
		System.out.println(response);

		demo = clientUtil.getDocument("suggestion",//索引表
				"suggest",//索引类型
				"9",//创建文档对应的脚本名称，在esmapper/estrace/ESTracesMapper.xml中配置
				Demo.class);
	}
	@Test
	public void testComplete(){
		ClientInterface clientUtil = ElasticSearchHelper.getConfigRestClientUtil("esmapper/estrace/suggest.xml");
		try {
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("prefix","liu");
			//clientUtil.dropIndice("suggestion");
			String template = clientUtil.executeHttp("suggestion/suggest/_search?pretty",
														"doComplete",
														params,
														ClientInterface.HTTP_POST);
			System.out.println(template);

		} catch (ElasticSearchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testTerm(){
		ClientInterface clientUtil = ElasticSearchHelper.getConfigRestClientUtil("esmapper/estrace/suggest.xml");
		try {
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("prefix","this is content");
			//clientUtil.dropIndice("suggestion");
			String template = clientUtil.executeHttp("suggestion/suggest/_search?pretty",
					"doTerm",
					params,
					ClientInterface.HTTP_POST);
			System.out.println(template);

		} catch (ElasticSearchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testPhrase(){
		ClientInterface clientUtil = ElasticSearchHelper.getConfigRestClientUtil("esmapper/estrace/suggest.xml");
		try {
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("prefix","this is content");
			//clientUtil.dropIndice("suggestion");
			String template = clientUtil.executeHttp("suggestion/suggest/_search?pretty",
					"doPhrase",
					params,
					ClientInterface.HTTP_POST);
			System.out.println(template);

		} catch (ElasticSearchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
