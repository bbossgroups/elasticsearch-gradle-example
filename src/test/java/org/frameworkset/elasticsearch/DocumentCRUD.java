package org.frameworkset.elasticsearch;/*
 *  Copyright 2008 biaoping.yin
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

import org.frameworkset.elasticsearch.client.ClientInterface;
import org.frameworkset.elasticsearch.entity.Demo;
import org.junit.Test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DocumentCRUD {
	@Test
	public void testCreateIndice(){
		ClientInterface clientUtil = ElasticSearchHelper.getConfigRestClientUtil("esmapper/demo.xml");
		try {
			//删除mapping
			clientUtil.dropIndice("demo");
		} catch (ElasticSearchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//创建mapping
		clientUtil.createIndiceMapping("demo","createDemoIndice");
	}
	@Test
	public void testAddDocument() throws ParseException {
//		testCreateDemoMapping();

		ClientInterface clientUtil = ElasticSearchHelper.getRestClientUtil();
		//构建一个对象，日期类型，字符串类型属性演示
		Demo demo = new Demo();
		demo.setDemoId(2l);//文档id，唯一标识，@PrimaryKey注解标示,如果demoId已经存在做修改操作，否则做添加文档操作
		demo.setAgentStarttime(new Date());
		demo.setApplicationName("blackcatdemo2");
		demo.setContentbody("this is content body2");
		demo.setName("刘德华");


		//添加或者修改文档,如果demoId已经存在做修改操作，否则做添加文档操作，返回处理结果
		String response = clientUtil.addDocument("demo",//索引表
				"demo",//索引类型
				demo);

		System.out.println("打印结果：addDocument-------------------------");
		System.out.println(response);
		//根据文档id获取文档对象，返回json报文字符串
		response = clientUtil.getDocument("demo",//索引表
				"demo",//索引类型
				"2");//w

		System.out.println("打印结果：getDocument-------------------------");
		System.out.println(response);
		//根据文档id获取文档对象，返回Demo对象
		demo = clientUtil.getDocument("demo",//索引表
				"demo",//索引类型
				"2",//文档id
				Demo.class);

		//删除文档
		clientUtil.deleteDocument("demo",//索引表
				"demo",//索引类型
				"2");//文档id
		//批量删除文档
		clientUtil.deleteDocuments("demo",//索引表
				"demo",//索引类型
				new String[]{"2","3"});//批量删除文档ids

	}

	@Test
	public void testBulkAddDocument() throws ParseException {
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

		//批量添加或者修改文档
		String response = clientUtil.addDocuments("demo",//索引表
				"demo",//索引类型
				demos);

		System.out.println("addDateDocument-------------------------");
		System.out.println(response);

		response = clientUtil.getDocument("demo",//索引表
				"demo",//索引类型
				"2");//w
		System.out.println("getDocument-------------------------");
		System.out.println(response);

		demo = clientUtil.getDocument("demo",//索引表
				"demo",//索引类型
				"3",//文档id
				Demo.class);
	}

}
