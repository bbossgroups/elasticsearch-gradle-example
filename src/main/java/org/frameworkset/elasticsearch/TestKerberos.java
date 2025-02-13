package org.frameworkset.elasticsearch;
/**
 * Copyright 2025 bboss
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

/**
 * <p>Description: </p>
 * <p></p>
 *
 * @author biaoping.yin
 * @Date 2025/2/13
 */
public class TestKerberos {
    public static void main(String[] args){
        ClientInterface clientUtil = ElasticSearchHelper.getRestClientUtil();
        //获取ES版本信息
        String result = clientUtil.executeHttp("/?pretty", ClientInterface.HTTP_GET);
        System.out.println(result);
        System.out.println(clientUtil.getClusterSettings());


    }
}
