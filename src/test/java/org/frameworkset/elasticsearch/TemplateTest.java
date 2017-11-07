package org.frameworkset.elasticsearch;

import org.frameworkset.elasticsearch.client.ClientInterface;
import org.frameworkset.elasticsearch.client.ClientUtil;
import org.junit.Test;

public class TemplateTest {
	private ClientInterface clientUtil = ElasticSearchHelper.getConfigRestClientUtil("esmapper/estrace/ESTemplate.xml");
	@Test
	public void testCreateTemplate(){
		String ttt = clientUtil.createTempate("trace_template","traceTemplate");

		ttt = clientUtil.createTempate("tracesql_template","traceSQLTemplate");
	}

	@Test
	public void testQueryTemplate(){
		String ttt = clientUtil.createTempate("trace_template","traceTemplate");

		ttt = clientUtil.createTempate("tracesql_template","traceSQLTemplate");
	}


	@Test
	public void testLicense(){
		String ttt = clientUtil.executeHttp("_xpack/license?acknowledge=true","license",ClientUtil.HTTP_PUT);
		System.out.println(ttt);
//		ttt = clientUtil.createTempate("tracesql_template","traceSQLTemplate");
	}
}
