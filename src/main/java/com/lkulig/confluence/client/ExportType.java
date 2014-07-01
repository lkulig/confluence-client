package com.lkulig.confluence.client;

public enum ExportType {

    TYPE_HTML("TYPE_HTML");

    private final String exportType;

    ExportType(String exportType) {
        this.exportType = exportType;
    }

    public String asString() {
        return exportType;
    }
}
