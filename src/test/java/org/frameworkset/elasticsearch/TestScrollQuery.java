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
import org.frameworkset.elasticsearch.entity.ESDatas;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TestScrollQuery {
	@Test
	public void testScroll(){
		ClientInterface clientUtil = ElasticSearchHelper.getConfigRestClientUtil("esmapper/scroll.xml");
		//scroll分页检索
		ESDatas<Map> response = clientUtil.searchList("agentstat-*/_search?scroll=1m","scrollQuery",Map.class);
		List<Map> datas = response.getDatas();
		List<String > scrollIds = new ArrayList<>();
		long totalSize = response.getTotalSize();
		String scrollId = response.getScrollId();
		if(scrollId != null)
			scrollIds.add(scrollId);
		System.out.println("totalSize:"+totalSize);
		System.out.println("scrollId:"+scrollId);
		if(datas != null && datas.size() > 0) {//每页1000条记录，遍历分页结果
			do {

				response = clientUtil.searchScroll("1m",scrollId,Map.class);
				scrollId = response.getScrollId();
				if(scrollId != null)
					scrollIds.add(scrollId);
				datas = response.getDatas();
				if(datas == null || datas.size() == 0){
					break;
				}
			} while (true);
		}
		//查询scroll
		String scrolls = clientUtil.executeHttp("_nodes/stats/indices/search", ClientUtil.HTTP_GET);
		System.out.println(scrolls);
		//处理完毕后清除scrollids
		if(scrollIds.size() > 0) {
			scrolls = clientUtil.deleteScrolls(scrollIds);
			System.out.println(scrolls);
		}
		//清理完毕后查看scroll
		scrolls = clientUtil.executeHttp("_nodes/stats/indices/search", ClientUtil.HTTP_GET);
		System.out.println(scrolls);
	}

}
