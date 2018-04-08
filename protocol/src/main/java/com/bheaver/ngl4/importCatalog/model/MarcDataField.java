package com.bheaver.ngl4.importCatalog.model;

import lombok.Data;

import java.util.List;

@Data
public class MarcDataField {
    private String tag;
    private Character indicator1;
    private Character indicator2;
    private List<MarcSubField> subFields;
}
