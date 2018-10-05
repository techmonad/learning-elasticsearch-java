package com.techmonad.es.service;


import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.client.Client;

import java.util.List;

public class IngestService {

    private Client client;

    public IngestService(Client client) {
        this.client = client;
    }

    public boolean ingest(String index, String type, String doc) {
        return client.prepareIndex(index, type).setSource(doc).get().isCreated();
    }


    public boolean ingest(String index, String type, List<String> docs) {
        BulkRequestBuilder bulkRequest = client.prepareBulk();
        docs.forEach(doc -> bulkRequest.add(client.prepareIndex(index, type).setSource(doc)));
        return bulkRequest.get().hasFailures();

    }


}
