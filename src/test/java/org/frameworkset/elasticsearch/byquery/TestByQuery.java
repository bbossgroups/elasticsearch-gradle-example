package org.frameworkset.elasticsearch.byquery;/*
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
import org.frameworkset.elasticsearch.entity.ESDatas;
import org.frameworkset.elasticsearch.parentchild.Employee;
import org.frameworkset.elasticsearch.parentchild.ParentChildTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestByQuery {

	/**
	 * 创建索引表并导入测试数据
	 */
	public void initIndiceAndData(){
		ParentChildTest parentChildTest = new ParentChildTest();
		parentChildTest.createIndice();
		parentChildTest.importFromJsonData();
	}
	public void deleteByQuery(){
		ClientInterface clientUtil = ElasticSearchHelper.getConfigRestClientUtil("esmapper/byquery.xml");
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("country","UK");
		//先查询数据是否存在
		ESDatas<Employee> escompanys = clientUtil.searchList("company/employee/_search","deleteByQuery",params,Employee.class);
		List<Employee> companyList = escompanys.getDatas();//获取符合UK国家条件的公司的员工信息
		long totalSize = escompanys.getTotalSize();
		if(companyList != null && companyList.size() > 0) {//如果有雇员信息，则删除之
			String result = clientUtil.deleteByQuery("company/employee/_delete_by_query?scroll_size=5000", "deleteByQuery", params);
			System.out.println(result);

			//删除后再次查询，验证数据是否被删除
			escompanys = clientUtil.searchList("company/employee/_search","deleteByQuery",params,Employee.class);
			companyList = escompanys.getDatas();//获取符合UK国家条件的公司的员工信息
			totalSize = escompanys.getTotalSize();
			System.out.println("删除后再次查询，验证数据是否被删除:totalSize="+totalSize);
		}

	}


	public void updateByQuery(){

	}

	public void testDeleteByQuery(){
		initIndiceAndData();
		deleteByQuery();
	}
}
