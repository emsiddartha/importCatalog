package com.bheaver.ngl4.importCatalog.factory;

import com.bheaver.ngl4.importCatalog.ImportCatalogRecords;
import com.bheaver.ngl4.importCatalog.impl.ImportMARCRecords;
import com.bheaver.ngl4.importCatalog.model.BeanNames;
import com.bheaver.ngl4.importCatalog.util.Transformer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ImportCatalogFactory {
    @Bean(name = BeanNames.IMPORT_CATALOG_RECORD)
    public ImportCatalogRecords importMARCRecords(){
        return new ImportMARCRecords();
    }

    @Bean(name = BeanNames.TRANSFORMER)
    public Transformer transformer(){
        return new Transformer();
    }
}
