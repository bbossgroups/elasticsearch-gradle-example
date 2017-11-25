package org.frameworkset.elasticsearch;

import org.frameworkset.elasticsearch.client.ClientInterface;
import org.frameworkset.elasticsearch.entity.Demo;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PingyinTest {
	@Test
	public void testCreateDemoMapping(){

		ClientInterface clientUtil = ElasticSearchHelper.getConfigRestClientUtil("esmapper/estrace/pinyin.xml");
		try {
			clientUtil.dropIndice("demo-2017.11.26");
			String template = clientUtil.deleteTempate("demo_template");
			System.out.println(template);
			 template = clientUtil.createTempate("demo_template", "demoTemplate");
			System.out.println(template);
//			//获取索引表结构
//			System.out.println(clientUtil.getIndice("demo"));
//			//删除索引表结构
//			System.out.println(clientUtil.dropIndice("demo"));
		} catch (ElasticSearchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//
//		//创建索引表结构
//		System.out.println(clientUtil.createIndiceMapping("demo","createDemoIndice"));
//
//		System.out.println(clientUtil.getIndice("demo"));
//
//		System.out.println(clientUtil.getIndice("demo"));
//
//		System.out.println(clientUtil.getIndice("demo"));
	}
	@Test
	public void testBulkAddDateDocument() throws ParseException {
//		testCreateDemoMapping();
		SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
		String date = format.format(new Date());
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
		String response = clientUtil.addDateDocuments("demo",//索引表
				"demo",//索引类型
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
