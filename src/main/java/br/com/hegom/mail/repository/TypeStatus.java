package br.com.hegom.mail.repository;

public enum TypeStatus {
    WAITING("WAITING"),
    SENT("SENT"),
    FAILED("FAILED");

    private final String value;

    private TypeStatus(String value){
        this.value = value;
    }

    private String getValue(){
        return this.value;
    }
}
