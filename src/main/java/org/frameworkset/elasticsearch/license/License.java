package org.frameworkset.elasticsearch.license;

import org.frameworkset.elasticsearch.ElasticSearchHelper;
import org.frameworkset.elasticsearch.client.ClientInterface;
import org.frameworkset.elasticsearch.client.ClientUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class License {
	private static Logger logger = LoggerFactory.getLogger(License.class);
	public static void main(String[] args){
		ClientInterface eventClientUtil = ElasticSearchHelper.getConfigRestClientUtil("conf/license.xml");
		String ttt = eventClientUtil.executeHttp("_xpack/license?acknowledge=true","license", ClientUtil.HTTP_PUT);
//		System.out.println(ttt);
		logger.info(ttt);
		System.exit(1);
	}
}
