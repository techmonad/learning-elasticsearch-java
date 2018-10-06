package com.techmonad.es.service;


import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilder;
import static org.elasticsearch.index.query.QueryBuilders.*;

public class CountService {

   private Client client;

    public CountService(Client client) {
        this.client = client;
    }

    public long getMatchAllQueryCount(String index) {
        QueryBuilder query = matchAllQuery();
        System.out.println("getMatchAllQueryCount query =>"+ query.toString());
        long count = client.prepareSearch(index).setQuery(query).setSize(0).execute().actionGet().getHits().getTotalHits();
        return count;
    }


    public long getBoolQueryCount(String index) {
        QueryBuilder query = boolQuery().must(
               termQuery("name","satendra")
        ).must( termQuery("location","india"));
        System.out.println("getBoolQueryCount query =>"+ query.toString());
        long count = client.prepareSearch(index).setQuery(query).setSize(0).execute().actionGet().getHits().getTotalHits();
        return count;
    }


    public long getPhraseQueryCount(String index) {
        QueryBuilder query = matchPhraseQuery("name",index);
        System.out.println("getPhraseQueryCount query =>"+ query.toString());
        long count = client.prepareSearch("test").setQuery(query).setSize(0).execute().actionGet().getHits().getTotalHits();
        return count;
    }


}
