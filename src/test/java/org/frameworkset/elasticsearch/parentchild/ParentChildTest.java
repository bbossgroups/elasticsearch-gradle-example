package org.frameworkset.elasticsearch.parentchild;/*
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

import org.frameworkset.elasticsearch.ElasticSearchException;
import org.frameworkset.elasticsearch.ElasticSearchHelper;
import org.frameworkset.elasticsearch.client.ClientInterface;
import org.frameworkset.elasticsearch.client.ClientUtil;
import org.frameworkset.elasticsearch.entity.ESDatas;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParentChildTest {

	public void createIndice(){
		ClientInterface clientUtil = ElasticSearchHelper.getConfigRestClientUtil("esmapper/indexparentchild.xml");
		try {
			//删除mapping
			clientUtil.dropIndice("company");
		} catch (ElasticSearchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//创建mapping
		clientUtil.createIndiceMapping("company","createCompanyEmployeeIndice");
	}

	public void importData(){
		ClientInterface clientUtil = ElasticSearchHelper.getConfigRestClientUtil("esmapper/indexparentchild.xml");

		//导入公司数据,并且实时刷新，测试需要，实际环境不要带refresh
		clientUtil.executeHttp("company/company/_bulk?refresh","bulkImportCompanyData",ClientUtil.HTTP_POST);
		//导入雇员数据,并且实时刷新，测试需要，实际环境不要带refresh
		clientUtil.executeHttp("company/employee/_bulk?refresh","bulkImportEmployeeData",ClientUtil.HTTP_POST);
	}

	public void hasChildSearchByBirthday(){

		ClientInterface clientUtil = ElasticSearchHelper.getConfigRestClientUtil("esmapper/indexparentchild.xml");
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("birthday","1980-01-01");
		ESDatas<Company> escompanys = clientUtil.searchList("company/company/_search","hasChildSearchByBirthday",params,Company.class);
		List<Company> companyList = escompanys.getDatas();//获取符合条件的公司
		long totalSize = escompanys.getTotalSize();
	}


	public void hasChildSearchByName(){

		ClientInterface clientUtil = ElasticSearchHelper.getConfigRestClientUtil("esmapper/indexparentchild.xml");
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("name","Alice Smith");
		ESDatas<Company> escompanys = clientUtil.searchList("company/company/_search","hasChildSearchByName",params,Company.class);
		List<Company> companyList = escompanys.getDatas();//获取符合条件的公司
		long totalSize = escompanys.getTotalSize();

	}

	public void hasChildSearchByMinChild(){

		ClientInterface clientUtil = ElasticSearchHelper.getConfigRestClientUtil("esmapper/indexparentchild.xml");
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("min_children",2);
		ESDatas<Company> escompanys = clientUtil.searchList("company/company/_search","hasChildSearchByMinChild",params,Company.class);
		List<Company> companyList = escompanys.getDatas();//获取符合条件的公司
		long totalSize = escompanys.getTotalSize();

	}

	public void hasParentSearchByCountry(){

		ClientInterface clientUtil = ElasticSearchHelper.getConfigRestClientUtil("esmapper/indexparentchild.xml");
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("country","UK");
		ESDatas<Employee> escompanys = clientUtil.searchList("company/employee/_search","hasParentSearchByCountry",params,Employee.class);
		List<Employee> companyList = escompanys.getDatas();//获取符合条件的公司
		long totalSize = escompanys.getTotalSize();

	}
	@Test
	public void test(){
		createIndice();
		importData();
		hasChildSearchByBirthday();
		this.hasChildSearchByName();
		this.hasChildSearchByMinChild();
		this.hasParentSearchByCountry();
	}
}
