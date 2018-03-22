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

import java.util.List;
import java.util.Map;

public class TestMGet {
	@Test
	public void testMget(){
		ClientInterface clientUtil = ElasticSearchHelper.getConfigRestClientUtil("esmapper/estrace/mget.xml");
		String response = clientUtil.executeHttp("_mget","testMget", ClientUtil.HTTP_POST);
		System.out.println(response);
		List<Map> docs = clientUtil.mgetDocuments("_mget","testMget",Map.class);
		System.out.println(docs);
	}
}
