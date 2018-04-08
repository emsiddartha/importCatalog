package com.bheaver.ngl4.importCatalog;

import reactor.core.publisher.Flux;

public interface ImportCatalogRecords {
    public String sayHello(String record);

    public Flux<com.bheaver.ngl4.importCatalog.model.MarcRecord> parseRecords(String rawData);
}
