package org.frameworkset.elasticsearch.suggest;

import org.frameworkset.elasticsearch.ElasticSearchException;
import org.frameworkset.elasticsearch.ElasticSearchHelper;
import org.frameworkset.elasticsearch.client.ClientInterface;
import org.frameworkset.elasticsearch.client.ClientUtil;
import org.frameworkset.elasticsearch.entity.suggest.PhraseRestResponse;
import org.frameworkset.elasticsearch.entity.suggest.PhraseSuggest;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PhraseSuggestionTest {
	@Test
	public void testCreateSuggestion(){
		ClientInterface clientUtil = ElasticSearchHelper.getConfigRestClientUtil("esmapper/estrace/suggest.xml");
		try {
			//clientUtil.dropIndice("test");
			String template = clientUtil.createIndiceMapping("test","createPhraseTestIndice");
			System.out.println(template);

			template = clientUtil.getIndexMapping("test");
			System.out.println(template);


		} catch (ElasticSearchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void testCreateIndex(){
		ClientInterface clientUtil = ElasticSearchHelper.getRestClientUtil();
		List<TestVO> demos = new ArrayList<TestVO>();
		TestVO demo = new TestVO();

		demo.setTitle("noble warriors");

		demos.add(demo);

		demo = new TestVO();

		demo.setTitle("nobel prize");

		demos.add(demo);


		//创建文档
		String response = clientUtil.addDocuments("test",//索引表
				"test",//索引类型
				demos,"refresh");

		System.out.println("addDocuments-------------------------");
		System.out.println(response);


	}
	@Test
	public void testPhraseSuggest(){
		ClientInterface clientUtil = ElasticSearchHelper.getConfigRestClientUtil("esmapper/estrace/suggest.xml");
		String template = clientUtil.getIndexMapping("test");
		System.out.println(template);

		String response = clientUtil.executeHttp("test/_search?pretty","phraseSearch", ClientUtil.HTTP_POST);
		System.out.println(response);

		PhraseRestResponse book = clientUtil.phraseSuggest("test/_search", "phraseSearch") ;
		Map<String, List<PhraseSuggest>> phraseSuggests = book.getSuggests();
		System.out.println();
	}

}
