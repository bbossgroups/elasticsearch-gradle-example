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

import java.util.Map;

public class TestDocumentGet {
	@Test
	public void testGetDocSource(){
		ClientInterface clientUtil = ElasticSearchHelper.getRestClientUtil();
		String response = clientUtil.getDocumentSource("agentinfo/agentinfo/10.21.20.168/_source");
		System.out.println(response);
		Map data = clientUtil.getDocumentSource("agentinfo/agentinfo/10.21.20.168/_source",Map.class);
		System.out.println();
	}

	@Test
	public void testGetDoc(){
		ClientInterface clientUtil = ElasticSearchHelper.getRestClientUtil();
		String response = clientUtil.getDocumentByPath("agentinfo/agentinfo/10.21.20.168");
		System.out.println(response);
		Map data = clientUtil.getDocumentByPath("agentinfo/agentinfo/10.21.20.168",Map.class);
		System.out.println();
	}
}
