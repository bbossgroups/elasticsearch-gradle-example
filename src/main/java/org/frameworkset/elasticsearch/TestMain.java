package org.frameworkset.elasticsearch;

import java.text.ParseException;

public class TestMain {

	public static void main(String[] args) throws ParseException {
		ESTest esTest = new ESTest();
		//检查健康性
		esTest.healthCheck();
		//测试模板管理功能
		esTest.testTempate();
		//重新创建模板
		esTest.testCreateTempate();
		//向当天的索引表中添加文档
		esTest.testAddDateDocument();
		//批量创建文档
		esTest.testBulkAddDateDocument();
		//获取索引映射结构
		esTest.testGetmapping();
		
		

	}

}
