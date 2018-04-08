package com.bheaver.ngl4.importCatalog.util;

import com.bheaver.ngl4.importCatalog.model.MarcControlField;
import com.bheaver.ngl4.importCatalog.model.MarcDataField;
import com.bheaver.ngl4.importCatalog.model.MarcRecord;
import com.bheaver.ngl4.importCatalog.model.MarcSubField;
import org.marc4j.marc.ControlField;
import org.marc4j.marc.Record;

import java.util.List;
import java.util.stream.Collectors;

public class Transformer {
    public MarcRecord transform(Record record){

        String leader = record.getLeader().marshal();

        List<MarcDataField> dataFieldList = record.getDataFields().stream().map(dataField -> {
            List<MarcSubField> marcSubFields = dataField.getSubfields().stream().map(subfield -> {
                MarcSubField msc = new MarcSubField();
                msc.setIdentifier(subfield.getCode());
                msc.setData(subfield.getData());
                return msc;
            }).collect(Collectors.toList());
            MarcDataField mdf = new MarcDataField();
            mdf.setIndicator1(dataField.getIndicator1());
            mdf.setIndicator2(dataField.getIndicator2());
            mdf.setSubFields(marcSubFields);
            mdf.setTag(dataField.getTag());
            return mdf;
        }).collect(Collectors.toList());

        List<MarcControlField> controlFields = record.getControlFields().stream().map(controlField -> {
            MarcControlField mcf = new MarcControlField();
            mcf.setTag(controlField.getTag());
            mcf.setData(controlField.getData());
            return mcf;
        }).collect(Collectors.toList());
        MarcRecord mrc = new MarcRecord();
        mrc.setLeader(leader);
        mrc.setControlFields(controlFields);
        mrc.setDataFields(dataFieldList);
        return mrc;
    }
}
