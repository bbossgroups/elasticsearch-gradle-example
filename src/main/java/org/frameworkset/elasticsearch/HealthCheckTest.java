package org.frameworkset.elasticsearch;

import org.frameworkset.elasticsearch.client.ESAddress;
import org.frameworkset.elasticsearch.client.HealthCheck;
import org.frameworkset.spi.remote.http.ClientConfiguration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HealthCheckTest {
	
	public void testCheck(){
		final List<ESAddress> esAddresses = new ArrayList<>();
		esAddresses.add(new ESAddress("localhost:9200","/"));
//		esAddresses.add(new ESAddress("localhost2:9200"));
//		esAddresses.add(new ESAddress("localhost3:9200"));
		String elasticUser = "elastic", elasticPassword = "changeme";
		Map<String,String> headers = new HashMap<>();
		final HealthCheck healthCheck = new HealthCheck("default", ClientConfiguration.getHealthPoolName("default"),esAddresses,5000);
		healthCheck.run();
		
		Thread r = new Thread(new Runnable(){

			@Override
			public void run() {
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					
				}
				esAddresses.get(0).setStatus(1);
				healthCheck.stopCheck();
			}
			
		});
		r.start();
	}
	
	public static void main(String[] args){
		HealthCheckTest test = new HealthCheckTest();
		test.testCheck();
	}

}
