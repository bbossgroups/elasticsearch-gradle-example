package org.frameworkset.elasticsearch;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.frameworkset.elasticsearch.entity.ESAggDatas;
import org.frameworkset.elasticsearch.entity.ESDatas;
import org.frameworkset.elasticsearch.entity.ESIndice;
import org.frameworkset.elasticsearch.entity.LongAggRangeHit;
import org.frameworkset.elasticsearch.entity.TraceExtraCriteria;
import org.frameworkset.elasticsearch.entity.Traces;
import org.junit.Test;

public class TraceESDaoTest {
	@Test
	public void testQueryPeriodsTopN() throws ParseException, IOException {
		TraceESDao traceESDao = new TraceESDao();
		TraceExtraCriteria traceExtraCriteria = new TraceExtraCriteria();
		traceExtraCriteria.setApplication("testweb88");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		traceExtraCriteria.setStartTime(dateFormat.parse("2017-09-02 00:00:00").getTime());
		traceExtraCriteria.setEndTime(dateFormat.parse("2017-09-14 00:00:00").getTime());
		ESAggDatas<LongAggRangeHit> topN = traceESDao.queryPeriodsTopN(traceExtraCriteria);
		System.out.println(topN);
	}
	@Test
	public void testQueryTraces() throws ParseException {
		TraceESDao traceESDao = new TraceESDao();
		TraceExtraCriteria traceExtraCriteria = new TraceExtraCriteria();
		traceExtraCriteria.setApplication("testweb88");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		traceExtraCriteria.setStartTime(dateFormat.parse("2017-09-02 00:00:00").getTime());
		traceExtraCriteria.setEndTime(dateFormat.parse("2017-09-13 00:00:00").getTime());
		ESDatas<Traces> data = traceESDao.queryTracesByCriteria(traceExtraCriteria);

	}
	@Test
	public void testQueryServiceTracesAvgElapsed() throws ParseException{
		TraceESDao traceESDao = new TraceESDao();
		TraceExtraCriteria traceExtraCriteria = new TraceExtraCriteria();
		traceExtraCriteria.setApplication("testweb88");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		traceExtraCriteria.setStartTime(dateFormat.parse("2017-09-02 00:00:00").getTime());
		traceExtraCriteria.setEndTime(dateFormat.parse("2017-09-14 00:00:00").getTime());
		traceESDao.queryServiceTracesAvgElapsed(traceExtraCriteria);
	}
	@Test
	public void testFormat() throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		Date d = format.parse("2017-08-31T16:18:27.285Z");
		System.out.println(d);
	}
	@Test
	public void testQueryDocMapping(){
		TraceESDao traceESDao = new TraceESDao();
		System.out.println(traceESDao.queryTracesMapping("tracesql-2017.09.15"));
//		http://127.0.0.1:9200/productindex/_mapping?pretty
	}

	@Test
	public void testDropDocMapping(){
		TraceESDao traceESDao = new TraceESDao();
		System.out.println(traceESDao.deleteMapping("tracesql-*"));
//		http://127.0.0.1:9200/productindex/_mapping?pretty
	}

	@Test
	public void refresh(){
		List<ESIndice> indexes = ElasticSearchHelper.getRestClientUtil().getIndexes();
		for(ESIndice indice:indexes) {
			System.out.println(ElasticSearchHelper.getRestClientUtil().refreshIndexInterval(indice.getIndex(), 30));
		}
	}

	@Test
	public void refreshAll(){
		 System.out.println(ElasticSearchHelper.getRestClientUtil().refreshIndexInterval( 1));

	}

	@Test
	public void refreshAllPreserve(){
		System.out.println(ElasticSearchHelper.getRestClientUtil().refreshIndexInterval( 30,true));

	}

	@Test
	public void refreshPattern(){

			System.out.println(ElasticSearchHelper.getRestClientUtil().refreshIndexInterval("trace-*", 30));

	}
}
