package com.example.config;


import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.solr.SolrProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;




@Configuration
@ConditionalOnClass({ HttpSolrClient.class ,CloudSolrClient.class})
@EnableConfigurationProperties(SolrProperties.class)
public  class HttpSolrConfiguration {

	private final SolrProperties properties;
	 
	  private SolrClient solrClient;
	 
	  public HttpSolrConfiguration(SolrProperties properties) {
	    this.properties = properties;
	  }
	  public SolrClient SolrClient() {
		  this.solrClient = createSolrClient();
		    return this.solrClient;
	  }
	  private SolrClient createSolrClient() {
		    if (StringUtils.hasText(this.properties.getZkHost())) {
		      return new CloudSolrClient(this.properties.getZkHost());
		    }
		    return new HttpSolrClient(this.properties.getHost());
		  }
	


	

   
}

