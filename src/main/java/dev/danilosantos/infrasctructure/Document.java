package dev.danilosantos.infrasctructure;

import dev.danilosantos.infrasctructure.enums.DocumentType;

public class Document {
    private DocumentType type;
    private String value;

    public Document() {
    }

    public Document(DocumentType type, String value) {
        this.type = type;
        this.value = value;
    }

    public boolean verifyDocument() {
        return true;
    }

    public DocumentType getType() {
        return type;
    }

    public void setType(DocumentType type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String toString() {
        return type + "-" + value;
    }

}
