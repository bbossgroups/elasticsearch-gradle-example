package org.frameworkset.elasticsearch;
/**
 * Copyright 2008 biaoping.yin
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.frameworkset.elasticsearch.client.ClientInterface;
import org.frameworkset.runtime.CommonLauncher;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>Description: </p>
 * <p></p>
 * <p>Copyright (c) 2018</p>
 * @Date 2019/11/22 0:00
 * @author biaoping.yin
 * @version 1.0
 */
public class UpdateSetting {
	public static void main(String[] args){
		ClientInterface clientInterface = ElasticSearchHelper.getRestClientUtil();
		int max_result_window = CommonLauncher.getIntProperty("max_result_window",15000);
		Map<String,Object> settings = new HashMap<String,Object>();
		settings.put("index.max_result_window",max_result_window);
		String result = clientInterface.updateAllIndicesSettings(settings);
		System.out.println(result);
		String setting = clientInterface.getClusterSettings();
		System.out.println(setting);


	}
}
