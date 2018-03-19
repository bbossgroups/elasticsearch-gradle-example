package org.frameworkset.elasticsearch.suggest;

import com.frameworkset.util.SimpleStringUtil;
import org.frameworkset.elasticsearch.ElasticSearchException;
import org.frameworkset.elasticsearch.ElasticSearchHelper;
import org.frameworkset.elasticsearch.client.ClientInterface;
import org.frameworkset.elasticsearch.client.ClientUtil;
import org.frameworkset.elasticsearch.entity.MapRestResponse;
import org.frameworkset.elasticsearch.entity.suggest.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SuggestionTest {
	@Test
	public void testCreateSuggestion(){
		ClientInterface clientUtil = ElasticSearchHelper.getConfigRestClientUtil("esmapper/estrace/suggest.xml");
		try {
			clientUtil.dropIndice("book");
			String template = clientUtil.createIndiceMapping("book","createCompleteSuggestBookIndice");
			System.out.println(template);

			template = clientUtil.getIndexMapping("book");
			System.out.println(template);


		} catch (ElasticSearchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void testCreateIndex(){
		ClientInterface clientUtil = ElasticSearchHelper.getRestClientUtil();
		List<Book> demos = new ArrayList<Book>();
		Book demo = new Book();
		demo.setId(2l);
		demo.setTitle("scala编程思想");
		demo.setMessage("trying out Elasticsearch");
		demo.setUser("elastic");
		List<SuggestInput> suggest = new ArrayList<>();
		SuggestInput input = new SuggestInput();
		input.setInput("scala编程思想");
		input.setWeight(50);
		suggest.add(input);
		input = new SuggestInput();
		input.setInput("scala快速入门");
		input.setWeight(49);
		suggest.add(input);
		input = new SuggestInput();
		input.setInput("快学scala");
		input.setWeight(49);
		suggest.add(input);
		demo.setSuggest(suggest);
		demos.add(demo);

		demo = new Book();
		demo.setId(3l);
		demo.setTitle("go圣经");
		suggest = new ArrayList<>();
		input = new SuggestInput();
		input.setInput("go圣经");
		input.setWeight(50);
		suggest.add(input);
		input = new SuggestInput();
		input.setInput("golang");
		input.setWeight(49);
		suggest.add(input);
		input = new SuggestInput();
		input.setInput("go编程思想");
		input.setWeight(49);
		suggest.add(input);
		demo.setMessage("trying Elasticsearch");
		demo.setUser("elastic");
		demo.setSuggest(suggest);
		demos.add(demo);

		demo = new Book();
		demo.setId(4l);
		demo.setTitle("go编程思想");
		suggest = new ArrayList<>();
		input = new SuggestInput();
		input.setInput("go圣经");
		input.setWeight(48);
		suggest.add(input);
		input = new SuggestInput();
		input.setInput("golang");
		input.setWeight(49);
		suggest.add(input);
		input = new SuggestInput();
		input.setInput("go编程思想");
		input.setWeight(50);
		suggest.add(input);
		demo.setSuggest(suggest);
		demo.setMessage("out of Elasticsearch");
		demo.setUser("elasticsearch");
		demos.add(demo);
		//创建文档
		String response = clientUtil.addDocuments("book",//索引表
				"book",//索引类型
				demos,"refresh");

		System.out.println("addDocuments-------------------------");
		System.out.println(response);

		response = clientUtil.getDocument("book",//索引表
				"book",//索引类型
				"2");
		System.out.println("getDocument-------------------------");
		System.out.println(response);

		demo = clientUtil.getDocument("book",//索引表
				"book",//索引类型
				"3",
				Book.class);
	}
	@Test
	public void testCompleteSuggest(){
		ClientInterface clientUtil = ElasticSearchHelper.getConfigRestClientUtil("esmapper/estrace/suggest.xml");
		String template = clientUtil.getIndexMapping("book");
		System.out.println(template);
		String mapping = clientUtil.getIndexMapping("book");
		String response = clientUtil.executeHttp("book/_search?pretty","complateSuggestSearch", ClientUtil.HTTP_POST);
		System.out.println(response);

		CompleteRestResponse book = clientUtil.complateSuggest("book/_search", "complateSuggestSearch",Book.class) ;
		Map<String, List<CompleteSuggest>> completeSuggests = book.getSuggests();
		System.out.println();
	}

	@Test
	public void testTermSuggest(){
		ClientInterface clientUtil = ElasticSearchHelper.getConfigRestClientUtil("esmapper/estrace/suggest.xml");
		String template = clientUtil.getIndexMapping("book");
		System.out.println(template);
		String mapping = clientUtil.getIndexMapping("book");
		String response = clientUtil.executeHttp("book/_search?pretty","termSearch", ClientUtil.HTTP_POST);
		System.out.println(response);

		TermRestResponse book = clientUtil.termSuggest("book/_search", "termSearch") ;
		Map<String, List<TermSuggest>> termSuggests = book.getSuggests();
		System.out.println();
	}

	@Test
	public void testCount(){
		ClientInterface clientUtil = ElasticSearchHelper.getConfigRestClientUtil("esmapper/estrace/suggest.xml");
		MapRestResponse response = clientUtil.search("book/_count?pretty", "count") ;
		System.out.println(response);
	}

	@Test
	public void testJsonNull(){
		TestVO testVO = new TestVO();
		System.out.println(SimpleStringUtil.object2json(testVO));
	}
}
