package dev.danilosantos.infrasctructure;

import dev.danilosantos.infrasctructure.util.DateFormat;

import java.util.Date;

public class Member {

    private String cardNumber;
    private String name;
    private String date;
    private Document document;

    public Member() {
    }

    public Member(String cardNumber, String name, Date date, Document document) {
        this.cardNumber = cardNumber;
        this.name = name;
        this.date = DateFormat.date.format(date);
        this.document = document;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    @Override
    public String toString() {
        return "\n" + cardNumber + "," + name + "," + date + "," + document;
    }
}
