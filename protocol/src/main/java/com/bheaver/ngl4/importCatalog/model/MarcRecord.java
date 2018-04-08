package com.bheaver.ngl4.importCatalog.model;

import lombok.Data;

import java.util.List;

@Data
public class MarcRecord {
    private String leader;
    private String directory;
    private List<MarcControlField> controlFields;
    private List<MarcDataField> dataFields;
}
