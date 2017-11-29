package org.frameworkset.elasticsearch;

import com.frameworkset.util.FileUtil;
import org.frameworkset.elasticsearch.client.ClientInterface;
import org.frameworkset.elasticsearch.client.ClientUtil;
import org.frameworkset.elasticsearch.entity.Demo;
import org.frameworkset.elasticsearch.entity.ESDatas;
import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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


	@Test
	public void testBulkAddDateDocument1() throws ParseException {
//		testCreateDemoMapping();
		SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
		String date = format.format(new Date());
		ClientInterface clientUtil = ElasticSearchHelper.getRestClientUtil();
		List<Demo> demos = new ArrayList<>();
		Demo demo = new Demo();
		demo.setDemoId(12l);
		demo.setAgentStarttime(new Date());
		demo.setApplicationName("blackcatdemo2");
		demo.setContentbody("this is content body2");
		demo.setName("liu德华");
		demos.add(demo);

		demo = new Demo();
		demo.setDemoId(13l);
		demo.setAgentStarttime(new Date());
		demo.setApplicationName("blackcatdemo3");
		demo.setContentbody("四大天王，这种文化很好，中华人民共和国");
		demo.setName("zhang学友");
		demos.add(demo);

		//创建模板
		String response = clientUtil.addDateDocuments("demo",//索引表
				"demo",//索引类型
				demos);

		System.out.println("addDateDocument-------------------------");
		System.out.println(response);

		response = clientUtil.getDocument("demo-"+date,//索引表
				"demo",//索引类型
				"12");
		System.out.println("getDocument-------------------------");
		System.out.println(response);

		demo = clientUtil.getDocument("demo-"+date,//索引表
				"demo",//索引类型
				"13",//创建文档对应的脚本名称，在esmapper/estrace/ESTracesMapper.xml中配置
				Demo.class);
	}

	@Test
	public void testGetMapping(){
		ClientInterface clientUtil = ElasticSearchHelper.getRestClientUtil();
		String template = clientUtil.getTempate("pboosmapadresstemplate");
		System.out.println(template);
		System.out.println(clientUtil.getIndexMapping("pboos-map-adress-1503973107",true));
	}
	@Test
	public void testPboosMapAdressTemplate(){
		ClientInterface clientUtil = ElasticSearchHelper.getConfigRestClientUtil("esmapper/estrace/pinyin.xml");
		try {

			String template = clientUtil.deleteTempate("pboosmapadresstemplate");
			System.out.println(template);

		} catch (ElasticSearchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			String template = clientUtil.createTempate("pboosmapadresstemplate", "pboosMapAdressPinyinTemplate1");
			System.out.println(template);
		} catch (ElasticSearchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {

			clientUtil.dropIndice("pboos-map-adress-1503973107");
//			//获取索引表结构
//			System.out.println(clientUtil.getIndice("demo"));
//			//删除索引表结构
//			System.out.println(clientUtil.dropIndice("demo"));
		} catch (ElasticSearchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void importPboosMapData() throws IOException {
		String data = FileUtil.getFileContent("F:\\4_ASIA文档\\1_项目\\13_江西移动\\拼音搜索\\py\\maps_0","UTF-8");
//		StringReader reader = new StringReader(data);
//		LineNumberReader lr = new LineNumberReader(reader);
//		String line = null;
//		StringBuilder builder = new StringBuilder();
//		int i = 0;
//		do{
//			line = lr.readLine();
//
//			System.out.println(line);
//
//			if( line == null)
//				break;
//			else {
//				builder.append(line).append("\n");
//
//				i ++;
//				if(i == 5000)
//					break;
//
//			}
//		}while (true);


		//System.out.print(data);
		ClientInterface clientUtil = ElasticSearchHelper.getRestClientUtil();
		String temp = clientUtil.executeHttp("_bulk",data, ClientUtil.HTTP_POST);
		System.out.println(temp);
	}

	@Test
	public void updateDocument(){
		ClientInterface clientUtil = ElasticSearchHelper.getConfigRestClientUtil("esmapper/estrace/pinyin.xml");
		String temp = clientUtil.executeHttp("pboos-map-adress-1503973107/boosmap/38130122/_update",
				"updateDocument",
				(Object)null,
				ClientInterface.HTTP_POST);
		System.out.println(temp);

	}


	@Test
	public void searchPinyin(){
		ClientInterface clientUtil = ElasticSearchHelper.getConfigRestClientUtil("esmapper/estrace/pinyin.xml");
		Map<String,String> params = new HashMap<String,String>();
//		params.put("detailName","红谷滩红角洲");
		params.put("detailName","huayuan");
		params.put("distance","0.5km");
		params.put("lon","117.101757");
		params.put("lat","28.284787");
		ESDatas<PboosMap> datas = clientUtil.searchList("pboos-map-adress-1503973107/_search","searchPinyin",params,PboosMap.class);
		System.out.print(clientUtil.executeRequest("pboos-map-adress-1503973107/_search","searchPinyin",params));
	}

	@Test
	public void searchPinyinmatch_phrase_prefix(){
		ClientInterface clientUtil = ElasticSearchHelper.getConfigRestClientUtil("esmapper/estrace/pinyin.xml");
		Map<String,String> params = new HashMap<String,String>();
//		params.put("detailName","红谷滩红角洲");
		params.put("detailName","huayuan");
		params.put("distance","0.5km");
		params.put("lon","117.101757");
		params.put("lat","28.284787");
		ESDatas<PboosMap> datas = clientUtil.searchList("pboos-map-adress-1503973107/_search","searchPinyinmatch_phrase_prefix",params,PboosMap.class);
		System.out.print(clientUtil.executeRequest("pboos-map-adress-1503973107/_search","searchPinyinmatch_phrase_prefix",params));
	}

}
