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
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TestUpdate {

	@Test
	public void testPartitionUpdateWithDSL(){
		ClientInterface configRestClientUtil = ElasticSearchHelper.getConfigRestClientUtil("esmapper/agentstat.xml");
		Map params = new HashMap();
		Date date = new Date();
		params.put("eventTimestamp",date.getTime());
		params.put("eventTimestampDate",date);
		/**
		 * 采用dsl更新索引部分内容,dsl定义和路径api可以参考文档：
		 * https://www.elastic.co/guide/en/elasticsearch/reference/current/docs-update.html
		 */
		StringBuilder path = new StringBuilder();
		path.append("agentinfo/agentinfo/pdpagent/_update?refresh");//自行拼接rest api地址
		configRestClientUtil.updateByPath(path.toString(),
										"updateAgentInfoEndtime",//更新文档内容的dsl配置名称
				                         params);
	}

	@Test
	public void testPartitionUpdate(){
		Map params = new HashMap();
		Date date = new Date();
		params.put("eventTimestamp",date.getTime());
		params.put("eventTimestampDate",date);
		/**
		 * 更新索引部分内容
		 */
		ClientInterface restClientUtil = ElasticSearchHelper.getRestClientUtil();
		String response = restClientUtil.updateDocument("agentinfo",//索引表名称
														"agentinfo",//索引type
														"pdpagent",//索引id
														 params,//待更新的索引字段信息
														"refresh");//强制刷新索引
		System.out.println(response);
	}
}
