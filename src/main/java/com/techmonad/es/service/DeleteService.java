package com.techmonad.es.service;


import org.elasticsearch.action.deletebyquery.DeleteByQueryAction;
import org.elasticsearch.action.deletebyquery.DeleteByQueryRequestBuilder;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilders;

/***
 * add delete by query plugin to Elastisearch
 * $ bin/plugin install delete-by-query
 */
public class DeleteService {


    private Client client;

    public DeleteService(Client client) {
        this.client = client;
    }


    public boolean delete(String index, String type, String id) {
        return client.prepareDelete(index, type, id).get().isFound();
    }

    public long deleteByQuery(String index, String name) {
        return new DeleteByQueryRequestBuilder(client, DeleteByQueryAction.INSTANCE)
                .setIndices(index)
                .setQuery(QueryBuilders.termQuery("name", name))
                .execute().actionGet().getTotalDeleted();
    }
}
