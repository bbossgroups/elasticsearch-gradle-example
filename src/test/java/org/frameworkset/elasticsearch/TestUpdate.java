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
	private ClientInterface configRestClientUtil = ElasticSearchHelper.getConfigRestClientUtil("esmapper/agentstat.xml");
	@Test
	public void testPartitionUpdate(){
		Map params = new HashMap();
		Date date = new Date();
		params.put("eventTimestamp",date.getTime());
		params.put("eventTimestampDate",date);
		/**
		 * 更新索引部分内容
		 */
		StringBuilder path = new StringBuilder();
		path.append("agentinfo/agentinfo/pdpagent/_update?refresh");
		configRestClientUtil.updateByPath(path.toString(),
				"updateAgentInfoEndtime",params);
	}
}
