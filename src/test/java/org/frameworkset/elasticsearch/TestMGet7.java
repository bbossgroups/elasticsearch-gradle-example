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
import org.frameworkset.elasticsearch.client.ClientUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Elasticsearch 7+案例
 */
public class TestMGet7 {
	@Test
	public void testMgetWithDSL(){
		ClientInterface clientUtil = ElasticSearchHelper.getConfigRestClientUtil("esmapper/estrace/mget7.xml");
		//通过执行dsl获取多个文档的内容
		List<String> ids = new ArrayList<String>();
		ids.add("TT5A8JQBcIsteX3sNfdw");
		ids.add("TD5A8JQBcIsteX3sNfdw");
		Map params = new HashMap();
		params.put("ids",ids);
		String response = clientUtil.executeHttp("_mget",
												 "testMget",//dsl定义名称
												 params, //存放文档id的参数
				                                 ClientUtil.HTTP_POST);
		System.out.println(response);
		List<Map> docs = clientUtil.mgetDocuments("_mget",
												"testMget",//dsl定义名称
												 params, //存放文档id的参数
												 Map.class);//返回文档对象类型
		System.out.println(docs);
	}

	@Test
	public void testMget(){
		ClientInterface clientUtil = ElasticSearchHelper.getRestClientUtil();
		//获取json报文

        String response  = clientUtil.mgetDocumentsNew("dbdemo",//索引表
                "TT5A8JQBcIsteX3sNfdw","TD5A8JQBcIsteX3sNfdw");//文档id清单，如果是数字类型，请用Integer之类的封装对象
		System.out.println(response);
		//获取封装成对象的文档列表，此处是Map对象，还可以是其他用户定义的对象类型


        List<Map> docs = clientUtil.mgetDocuments("dbdemo",//索引表
                Map.class,//返回文档对象类型
                "TT5A8JQBcIsteX3sNfdw","TD5A8JQBcIsteX3sNfdw");//文档id清单
		System.out.println(docs);
	}


//	private void ser(Object... ids){
//		System.out.println(SerialUtil.object2json(ids));
//	}
//
//	@Test
//	public void testSon(){
//		ser("1","2");
//		ser("1");
//		ser(1,3);
//		ser(new Integer[]{new Integer(1),new Integer(3)});
//	}
}
