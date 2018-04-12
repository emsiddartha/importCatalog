package com.bheaver.ngl4.importCatalog;


import com.bheaver.ngl4.importCatalog.model.MarcRecord;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface ImportCatalogRecords {
    public String sayHello(String record);

    public CompletableFuture<List<MarcRecord>> parseRecords(String rawData);
}
