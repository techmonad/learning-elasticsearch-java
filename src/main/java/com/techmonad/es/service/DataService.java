package com.techmonad.es.service;


import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.SearchHit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.*;

public class DataService {


    private Client client;

    public DataService(Client client) {
        this.client = client;
    }

    public List<String> getMatchAllQueryData(String index) {
        QueryBuilder query = matchAllQuery();
        System.out.println("getMatchAllQueryCount query =>" + query.toString());
        SearchHit[] hits = client.prepareSearch(index).setQuery(query).execute().actionGet().getHits().getHits();
        List<String> list = new ArrayList<String>();
        Arrays.stream(hits)
                .forEach(hit -> list.add(hit.getSourceAsString()));
        return Collections.unmodifiableList(list);
    }

    public List<String> getBoolQueryData(String index) {
        QueryBuilder query = boolQuery().must(
                termQuery("name", "satendra")
        ).must(termQuery("location", "india"));
        System.out.println("getBoolQueryCount query =>" + query.toString());
        SearchHit[] hits = client.prepareSearch(index)
                .setQuery(query).execute().actionGet().getHits().getHits();
        List<String> list = new ArrayList<String>();
        Arrays.stream(hits)
                .forEach(hit -> list.add(hit.getSourceAsString()));
        return Collections.unmodifiableList(list);
    }

    public List<String> getPhraseQueryData(String index) {
        QueryBuilder query = matchPhraseQuery("name", "satendra");
        System.out.println("getPhraseQueryCount query =>" + query.toString());
        SearchHit[] hits = client.prepareSearch(index)
                .setQuery(query)
                .execute()
                .actionGet()
                .getHits()
                .getHits();
        List<String> list = new ArrayList<String>();
        Arrays.stream(hits)
                .forEach(hit -> list.add(hit.getSourceAsString()));
        return Collections.unmodifiableList(list);
    }


}
