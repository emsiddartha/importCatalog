package com.bheaver.ngl4.importCatalog.impl;

import com.bheaver.ngl4.importCatalog.ImportCatalogRecords;
import com.bheaver.ngl4.importCatalog.model.BeanNames;
import com.bheaver.ngl4.importCatalog.model.MarcRecord;
import com.bheaver.ngl4.importCatalog.util.Transformer;
import org.marc4j.MarcReader;
import org.marc4j.MarcStreamReader;
import org.marc4j.marc.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;

public class ImportMARCRecords implements ImportCatalogRecords{
    @Autowired
    @Qualifier(BeanNames.TRANSFORMER)
    Transformer transformer;

    @Override
    public String sayHello(String record) {
        return "Hello "+record;
    }

    @Override
    public Flux<MarcRecord> parseRecords(String rawData) {
        return Flux.fromIterable(getMARCRecords(rawData)).subscribeOn(Schedulers.parallel());
    }

    private ArrayList<MarcRecord> getMARCRecords(String rawData){
        ArrayList<MarcRecord> alReturn = new ArrayList<>();
        try {
            InputStream is = new ByteArrayInputStream(rawData.getBytes());
            MarcReader reader = new MarcStreamReader(is);
            while (reader.hasNext()) {
                Record record = reader.next();
                MarcRecord marcRecord = transformer.transform(record);
                alReturn.add(marcRecord);
            }
        }catch (Exception exp){
            exp.printStackTrace();
        }
        return alReturn;
    }
}
