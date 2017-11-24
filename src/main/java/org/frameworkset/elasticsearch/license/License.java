package org.frameworkset.elasticsearch.license;

import org.frameworkset.elasticsearch.ElasticSearchHelper;
import org.frameworkset.elasticsearch.client.ClientInterface;
import org.frameworkset.elasticsearch.client.ClientUtil;

public class License {
	public static void main(String[] args){
		ClientInterface eventClientUtil = ElasticSearchHelper.getConfigRestClientUtil("conf/license.xml");
		String ttt = eventClientUtil.executeHttp("_xpack/license?acknowledge=true","license", ClientUtil.HTTP_PUT);
		System.out.println(ttt);
		System.exit(1);
	}
}
