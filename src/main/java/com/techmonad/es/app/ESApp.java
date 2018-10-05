package com.techmonad.es.app;


import com.techmonad.es.service.CountService;
import com.techmonad.es.service.DataService;
import com.techmonad.es.service.DeleteService;
import com.techmonad.es.service.IngestService;
import com.techmonad.es.util.ESManager;
import org.elasticsearch.client.Client;

import java.util.Arrays;


public class ESApp {

    public static void main(String[] args) {

        Client client = ESManager.getClient("localhost", 9300).get();

        CountService countService = new CountService(client);
        DataService dataService = new DataService(client);
        IngestService ingestService = new IngestService(client);
        DeleteService deleteService = new DeleteService(client);
        String indexName = "test";

        //count
        System.out.println("\ngetMatchAllQueryCount from ES::: " + countService.getMatchAllQueryCount(indexName));
        System.out.println("\ngetBoolQueryCount from ES::: " + countService.getBoolQueryCount(indexName));
        System.out.println("\ngetPhraseQueryCount from ES::: " + countService.getPhraseQueryCount(indexName));


        // Data
        System.out.println("\ngetMatchAllQueryData from ES::: ");
        dataService.getMatchAllQueryData(indexName).forEach(item -> System.out.println(item));

        System.out.println("\ngetBoolQueryData from ES::: ");
        dataService.getBoolQueryData(indexName).forEach(item -> System.out.println(item));

        System.out.println("\ngetPhraseQueryData from ES::: ");
        dataService.getPhraseQueryData(indexName).forEach(item -> System.out.println(item));

        //Ingest
        String json1 = "{" +
                "\"name\":\"skyji\"," +
                "\"job\":\"Admin\"," +
                "\"location\":\"India\"" +
                "}";

        String json2 = "{" +
                "\"name\":\"jom\"," +
                "\"job\":\"assiant\"," +
                "\"location\":\"Meana\"" +
                "}";

        // ingest single record
        boolean isIngested = ingestService.ingest(indexName,"tweet", json1);

        System.out.println("\nIngestService response::: " + isIngested);

        // ingest batch of records
        System.out.println("\nIngestService response::: " + ingestService.ingest(indexName, "tweet", Arrays.asList(json1, json2)));


        // Delete
        System.out.println("delete by query " + deleteService.deleteByQuery(indexName, "satendra"));
        client.close();

    }

}
