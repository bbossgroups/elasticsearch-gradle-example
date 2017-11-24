package org.frameworkset.elasticsearch;

import org.frameworkset.elasticsearch.client.ClientInterface;
import org.frameworkset.elasticsearch.client.ClientUtil;
import org.junit.Test;

public class TemplateTest {
	private ClientInterface clientUtil = ElasticSearchHelper.getConfigRestClientUtil("esmapper/estrace/ESTemplate.xml");
	@Test
	public void testCreateTemplate(){
		com.fasterxml.jackson.core.json.WriterBasedJsonGenerator s;
		com.fasterxml.jackson.core.io.CharTypes s1;
		String ttt = clientUtil.createTempate("trace_template","traceTemplate");

		ttt = clientUtil.createTempate("tracesql_template","traceSQLTemplate");

		//获取模板
		/**
		 * 指定模板
		 * /_template/trace_template
		 * /_template/trace*
		 * 所有模板 /_template
		 *
		 */
		String template = clientUtil.executeHttp("/_template/trace_template",ClientUtil.HTTP_GET);
		System.out.println("HTTP_GET-------------------------");
		System.out.println(template);
		template = clientUtil.getTempate("trace_template");
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
