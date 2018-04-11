package com.bheaver.ngl4.importCatalog.impl;

import com.bheaver.ngl4.importCatalog.ImportCatalogRecords;
import com.bheaver.ngl4.importCatalog.model.BeanNames;
import com.bheaver.ngl4.importCatalog.model.MarcRecord;
import com.bheaver.ngl4.importCatalog.util.Transformer;
import org.marc4j.MarcReader;
import org.marc4j.MarcStreamReader;
import org.marc4j.marc.Record;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

public class ImportMARCRecords implements ImportCatalogRecords{
    Logger logger = LoggerFactory.getLogger(ImportMARCRecords.class);
    @Autowired
    @Qualifier(BeanNames.TRANSFORMER)
    Transformer transformer;

    @Override
    public String sayHello(String record) {
        return "Hello "+record;
    }

    @Override
    public Flux<MarcRecord> parseRecords(String rawData) {
        return Flux.fromIterable(getMarcRecords(rawData)).subscribeOn(Schedulers.parallel());

    }
    private Iterable<MarcRecord> getMarcRecords(String rawData){
        InputStream is = new ByteArrayInputStream(rawData.getBytes());
        MarcReader reader = new MarcStreamReader(is);
        return new RecordIterable(reader,transformer);
    }

}
class RecordIterable implements Iterable<MarcRecord>{
    private final MarcReader marcReader;
    private final Transformer transformer;
    public RecordIterable(MarcReader marcReader, Transformer transformer){
        this.marcReader = marcReader;
        this.transformer = transformer;
    }
    @Override
    public Iterator<MarcRecord> iterator() {
        return new Iterator<MarcRecord>() {
            @Override
            public boolean hasNext() {
                return marcReader.hasNext();
            }

            @Override
            public MarcRecord next() {
                return transformer.transform(marcReader.next());
            }
        };
    }
}
