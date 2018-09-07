package com.bheaver.ngl4.importCatalog;


import com.bheaver.ngl4.importCatalog.model.MarcRecord;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ImportCatalogRecords {
    public String sayHello(String record);

    public Flux<MarcRecord> parseRecords(Mono<String> rawData);
}
