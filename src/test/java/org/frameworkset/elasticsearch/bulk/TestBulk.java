package org.frameworkset.elasticsearch.bulk;/*
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

import org.frameworkset.elasticsearch.ElasticSearchHelper;
import org.frameworkset.elasticsearch.client.ClientInterface;
import org.frameworkset.elasticsearch.client.ClientUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestBulk {
	@Test
	public void testBulkUpdate(){
		ClientInterface clientUtil = ElasticSearchHelper.getConfigRestClientUtil("esmapper/bulk.xml");
		List<OnlineGoodsInfoUpdateParams> onlineGoodsInfoUpdateParamss = new ArrayList<>();
		OnlineGoodsInfoUpdateParams onlineGoodsInfoUpdateParams = new OnlineGoodsInfoUpdateParams();
		onlineGoodsInfoUpdateParams.setId("aa");
		onlineGoodsInfoUpdateParams.setType("tt");
		onlineGoodsInfoUpdateParams.setIndex("ddd");
		onlineGoodsInfoUpdateParams.setVersion(1);
		onlineGoodsInfoUpdateParams.setVersionType("aaa");
		onlineGoodsInfoUpdateParams.setGoodsName("dddd");
		onlineGoodsInfoUpdateParamss.add(onlineGoodsInfoUpdateParams);

		onlineGoodsInfoUpdateParams = new OnlineGoodsInfoUpdateParams();
		onlineGoodsInfoUpdateParams.setId("aa");
		onlineGoodsInfoUpdateParams.setType("tt");
		onlineGoodsInfoUpdateParams.setIndex("ddd");
		onlineGoodsInfoUpdateParams.setVersion(1);
		onlineGoodsInfoUpdateParams.setVersionType("aaa");
		onlineGoodsInfoUpdateParams.setGoodsName("dddd");
		onlineGoodsInfoUpdateParamss.add(onlineGoodsInfoUpdateParams);
		Map<String,Object> params = new HashMap<>();
		params.put("onlineGoodsInfoUpdateParamss",onlineGoodsInfoUpdateParamss);
		String response = clientUtil.executeHttp("_bulk", "updateOnlineGoodsesInfo", params, ClientUtil.HTTP_POST);
		System.out.println(response);

	}

	@Test
	public void testOrmBulk(){
		ClientInterface clientUtil = ElasticSearchHelper.getRestClientUtil();
		List<OnlineGoodsInfoUpdateParams> onlineGoodsInfoUpdateParamss = new ArrayList<>();
		OnlineGoodsInfoUpdateParams onlineGoodsInfoUpdateParams = new OnlineGoodsInfoUpdateParams();
		onlineGoodsInfoUpdateParams.setId("aa");
		onlineGoodsInfoUpdateParams.setParentId("ppaa");
		onlineGoodsInfoUpdateParams.setType("tt");
		onlineGoodsInfoUpdateParams.setIndex("ddd");

		onlineGoodsInfoUpdateParams.setGoodsName("dddd");
		onlineGoodsInfoUpdateParams.setDocAsUpsert(true);
		onlineGoodsInfoUpdateParams.setRetryOnConflict(3);
		onlineGoodsInfoUpdateParams.setReturnSource(true);
		onlineGoodsInfoUpdateParams.setRouting("test");
		onlineGoodsInfoUpdateParams.setVersion(1);
		onlineGoodsInfoUpdateParams.setVersionType(ClientInterface.VERSION_TYPE_INTERNAL);

		onlineGoodsInfoUpdateParamss.add(onlineGoodsInfoUpdateParams);

		onlineGoodsInfoUpdateParams = new OnlineGoodsInfoUpdateParams();
		onlineGoodsInfoUpdateParams.setId("aa");
		onlineGoodsInfoUpdateParams.setParentId("ppaa");
		onlineGoodsInfoUpdateParams.setType("tt");
		onlineGoodsInfoUpdateParams.setIndex("ddd");

		onlineGoodsInfoUpdateParams.setGoodsName("dddd");

		/**
		 * 设置更新文档控制变量
		 */
		onlineGoodsInfoUpdateParams.setDocAsUpsert(true);
		onlineGoodsInfoUpdateParams.setRetryOnConflict(3);
		onlineGoodsInfoUpdateParams.setReturnSource(true);
		onlineGoodsInfoUpdateParams.setRouting("test");
		onlineGoodsInfoUpdateParams.setVersion(1);
		onlineGoodsInfoUpdateParams.setVersionType(ClientInterface.VERSION_TYPE_INTERNAL);
		onlineGoodsInfoUpdateParamss.add(onlineGoodsInfoUpdateParams);
		System.out.println(clientUtil.getDynamicIndexName("test"));
		String response = clientUtil.updateDocuments("aa","tt",onlineGoodsInfoUpdateParamss);
		System.out.println(response);

	}

}
