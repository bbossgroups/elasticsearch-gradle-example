package org.frameworkset.elasticsearch;

import com.frameworkset.util.FileUtil;
import org.frameworkset.elasticsearch.client.ClientInterface;
import org.frameworkset.elasticsearch.client.ClientUtil;
import org.frameworkset.elasticsearch.client.ResultUtil;
import org.frameworkset.elasticsearch.entity.Demo;
import org.frameworkset.elasticsearch.entity.ESDatas;
import org.frameworkset.elasticsearch.entity.SearchHit;
import org.frameworkset.elasticsearch.serial.ESInnerHitSerialThreadLocal;
import org.frameworkset.elasticsearch.serial.ESSerialThreadLocal;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class PingyinTest {
	@Test
	public void testCreateDemoMapping(){

		ClientInterface clientUtil = ElasticSearchHelper.getConfigRestClientUtil("esmapper/estrace/pinyin.xml");
		try {
			//可以先删除索引mapping，重新初始化数据
			clientUtil.dropIndice("demo");
		} catch (ElasticSearchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


//
		//创建索引表结构
		String response = clientUtil.createIndiceMapping("demo","createDemoIndice");
//       获取并打印创建的索引表结构
		System.out.println(clientUtil.getIndice("demo"));

	}

	@Test
	public void testCreateDemoMappingTemplate(){

		ClientInterface clientUtil = ElasticSearchHelper.getConfigRestClientUtil("esmapper/estrace/pinyin.xml");
		try {
			clientUtil.dropIndice("demo-*");
		} catch (ElasticSearchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			String template = clientUtil.deleteTempate("demo_template");
			System.out.println(template);
		} catch (ElasticSearchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			String template = clientUtil.createTempate("demo_template", "demoTemplate");
			System.out.println(template);
		} catch (ElasticSearchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//			//获取索引表结构
//			System.out.println(clientUtil.getIndice("demo"));
//			//删除索引表结构
//			System.out.println(clientUtil.dropIndice("demo"));

//
		//创建索引表结构
		System.out.println(clientUtil.createIndiceMapping("demo","createDemoIndice"));
//
//		System.out.println(clientUtil.getIndice("demo"));
//
//		System.out.println(clientUtil.getIndice("demo"));
//
//		System.out.println(clientUtil.getIndice("demo"));
	}
	@Test
	public void testBulkAddDocuments() throws ParseException {
//		testCreateDemoMapping();

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
		String response = clientUtil.addDocuments("demo",//索引表
				"demo",//索引类型
				demos);

		System.out.println("addDocuments-------------------------");
		System.out.println(response);

		//验证创建的两条索引记录
		response = clientUtil.getDocument("demo",//索引表
				"demo",//索引类型
				"2");
		System.out.println("getDocument-------------------------");
		System.out.println(response);

		demo = clientUtil.getDocument("demo",//索引表
				"demo",//索引类型
				"3",//创建文档对应的脚本名称，在esmapper/estrace/ESTracesMapper.xml中配置
				Demo.class);
	}

	@Test
	public void searchPinyinDemo(){
		ClientInterface clientUtil = ElasticSearchHelper.getConfigRestClientUtil("esmapper/estrace/pinyin.xml");
		Map<String,String> params = new HashMap<String,String>();
		params.put("name","zhang学友");//设置中文拼音混合检索条件
 		ESDatas<Map> esDatas = clientUtil.searchList("demo/_search","searchPinyinDemo",params,Map.class);
 		List<Map> datas = esDatas.getDatas();
 		long totalSize = esDatas.getTotalSize();
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
	public void testBulkAddDocument1() throws ParseException {
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
	public void createShopGoodUserPinyinTemplate() throws IOException {
		ClientInterface clientUtil = ElasticSearchHelper.getConfigRestClientUtil("esmapper/estrace/pinyin.xml");
		try {

			clientUtil.dropIndice("shop-good-user-1512023940");
//			//获取索引表结构
//			System.out.println(clientUtil.getIndice("demo"));
//			//删除索引表结构
//			System.out.println(clientUtil.dropIndice("demo"));
		} catch (ElasticSearchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {

			String template = clientUtil.deleteTempate("shopgooduserpinyintemplate");
			System.out.println(template);

		} catch (ElasticSearchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			String template = clientUtil.createTempate("shopgooduserpinyintemplate", "shopGoodUserPinyinTemplate");
			System.out.println(template);
		} catch (ElasticSearchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		this.importShopGood();
	}

	@Test
	public void createUserShopPinyinTemplate(){
		ClientInterface clientUtil = ElasticSearchHelper.getConfigRestClientUtil("esmapper/estrace/pinyin.xml");
		try {

			String template = clientUtil.deleteTempate("usershop");
			System.out.println(template);

		} catch (ElasticSearchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			String template = clientUtil.createTempate("usershop", "userShop");
			System.out.println(template);
		} catch (ElasticSearchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {

//			clientUtil.dropIndice("shop-good-user-1512023940");
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
	public void dropshopgooduser(){
		ElasticSearchHelper.getRestClientUtil().dropIndice("shop-good-user-1512023940");
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
			String template = clientUtil.createTempate("pboosmapadresstemplate", "pboosMapAdressPinyinTemplate");
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
	public void importPboosMapDatas() throws IOException {

		ExecutorService executorService = Executors.newFixedThreadPool(20);
		File maps = new File("F:\\拼音搜索\\maps");
		File[] mapFiles = maps.listFiles();
		 List<Future> fs = new ArrayList<>();
		for(File mapFile:mapFiles){
			final File f = mapFile;
			fs.add(executorService.submit(new Runnable() {
				@Override
				public void run() {
					try {
						String data = FileUtil.getFileContent(f, "UTF-8");
						ClientInterface clientUtil = ElasticSearchHelper.getRestClientUtil();
						String temp = clientUtil.executeHttp("_bulk", data, ClientUtil.HTTP_POST);
					}
					catch (Exception e){

					}

				}
			}));

		}

		for (Future f:fs){
			try {
				f.get();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}





	}

	@Test
	public void importShopGood() throws IOException {

		ClientInterface clientUtil = ElasticSearchHelper.getRestClientUtil();
		String data = FileUtil.getFileContent("拼音搜索\\数据\\shops","UTF-8");
		long time = System.currentTimeMillis();

		String temp = clientUtil.executeHttp("_bulk",data, ClientUtil.HTTP_POST);
		System.out.println(temp);
		System.out.println("耗时："+(System.currentTimeMillis() - time)+"毫秒");

		data = FileUtil.getFileContent("拼音搜索\\数据\\good","UTF-8");
		time = System.currentTimeMillis();

		temp = clientUtil.executeHttp("_bulk",data, ClientUtil.HTTP_POST);
		System.out.println(temp);
		System.out.println("耗时："+(System.currentTimeMillis() - time)+"毫秒");

		data = FileUtil.getFileContent("F:\\4_ASIA文档\\1_项目\\13_江西移动\\拼音搜索\\数据\\orders","UTF-8");
		time = System.currentTimeMillis();

		temp = clientUtil.executeHttp("_bulk",data, ClientUtil.HTTP_POST);
		System.out.println(temp);
		System.out.println("耗时："+(System.currentTimeMillis() - time)+"毫秒");
	}
	@Test
	public void importPboosMapData() throws IOException {
		String data = FileUtil.getFileContent("拼音搜索\\maps\\maps_0","UTF-8");

	    long time = System.currentTimeMillis();


		ClientInterface clientUtil = ElasticSearchHelper.getRestClientUtil();
		String temp = clientUtil.executeHttp("_bulk",data, ClientUtil.HTTP_POST);
		System.out.println(temp);
		System.out.println("耗时："+(System.currentTimeMillis() - time)+"毫秒");
	}

	@Test
	public void deleteInde(){
		ClientInterface clientUtil = ElasticSearchHelper.getRestClientUtil();
		clientUtil.dropIndice("_all");

	}
	@Test
	public void updateDocument(){
		ClientInterface clientUtil = ElasticSearchHelper.getConfigRestClientUtil("esmapper/estrace/pinyin.xml");

		Map<String,String> params = new HashMap<String,String>();
		params.put("cityName","潭市");
		params.put("standardAddrId","38130122");
		params.put("detailName","tan市贵溪市花园办事处建设路四冶生活区2-11栋3单元1层101");
		params.put("location","28.292781,117.238963");
		params.put("countyName","贵溪市");

		String temp = clientUtil.executeHttp("pboos-map-adress-1503973107/boosmap/38130122/_update",
				"updateDocument",
				params,
				ClientInterface.HTTP_POST);
		System.out.println(temp);

	}


	@Test
	public void searchPinyinPboos(){
		ClientInterface clientUtil = ElasticSearchHelper.getConfigRestClientUtil("esmapper/estrace/pinyin.xml");
		Map<String,String> params = new HashMap<String,String>();
		params.put("detailName","海域香廷160栋1单元3层302室");
		params.put("distance","0.5km");
		params.put("lon","115.824994");
		params.put("lat","28.666162");
		ESDatas<PboosMap> datas = clientUtil.searchList("pboos-map-adress-1503973107/_search","searchPinyinmatch_phrase_prefix",params,PboosMap.class);
		System.out.print(clientUtil.executeRequest("pboos-map-adress-1503973107/_search","searchPinyinmatch_phrase_prefix",params));
	}

	@Test
	public void searchPinyinPboosmatch_phrase_prefix(){
		ClientInterface clientUtil = ElasticSearchHelper.getConfigRestClientUtil("esmapper/estrace/pinyin.xml");
		Map<String,String> params = new HashMap<String,String>();
//		params.put("detailName","红谷滩红角洲");
		params.put("detailName","\\");
		params.put("distance","0.5km");
		params.put("lon","117.101757");
		params.put("lat","28.284787");
		ESDatas<PboosMap> datas = clientUtil.searchList("pboos-map-adress-1503973107/_search",
				"searchPinyinmatch_phrase_prefix",
				params,
				PboosMap.class);
		System.out.print(clientUtil.executeRequest("pboos-map-adress-1503973107/_search","searchPinyinmatch_phrase_prefix",params));
	}



	@Test
	public void searchPinyinShopMatch_phrase_prefix(){
		ClientInterface clientUtil = ElasticSearchHelper.getConfigRestClientUtil("esmapper/estrace/pinyin.xml");
		Map<String,String> params = new HashMap<String,String>();
//		params.put("detailName","红谷滩红角洲");
		params.put("name","xia壕塘15");
//		params.put("name","下壕塘15");
		params.put("distance","2km");
		params.put("lon","115.825472");
		params.put("lat","28.665041");
		ESDatas<Shop> datas = clientUtil.searchList("shop-good-user-1512023940/_search","searchShopGoodUser",params,Shop.class);
		System.out.print(clientUtil.executeRequest("shop-good-user-1512023940/_search?pretty","searchShopGoodUser",params));
	}

	@Test
	public void searchGoodParent(){
		ClientInterface clientUtil = ElasticSearchHelper.getConfigRestClientUtil("esmapper/estrace/pinyin.xml");
		Map<String,String> params = new HashMap<String,String>();
//		params.put("detailName","红谷滩红角洲");
		params.put("name","xiahaotang");
		params.put("distance","2km");
		params.put("lon","115.825472");
		params.put("lat","28.665041");
		try {
			ESInnerHitSerialThreadLocal.setESInnerTypeReferences(Shop.class);
			ESSerialThreadLocal.setESTypeReferences(Good.class);
			ESDatas<SearchHit> datas = clientUtil.searchList("shop-good-user-1512023940/_search","goodParentSearch",params,SearchHit.class);
			List<SearchHit> goods = datas.getDatas();
			for(int i = 0;  i < goods.size(); i ++) {
				List<Shop> shops = ResultUtil.getInnerHits(goods.get(i).getInnerHits(), "shop", Shop.class);
			}
			System.out.print(clientUtil.executeRequest("shop-good-user-1512023940/_search?pretty", "goodParentSearch", params));
		}
		finally {
			ESInnerHitSerialThreadLocal.clean();
			ESSerialThreadLocal.clean();
		}
	}

}
