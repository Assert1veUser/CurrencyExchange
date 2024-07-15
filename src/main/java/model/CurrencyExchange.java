package model;

import java.time.LocalDate;

public class CurrencyExchange {
    public static final String TABLE_NAME = "currency_exchange";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_VALUE = "value";
    public static final String COLUMN_NOMINAL = "nominal";
    public static final String COLUMN_CURRENCY_NAME = "currency_name";
    public static final String COLUMN_CURRENCY_CODE = "currency_code";
    public static final String COLUMN_DATE = "date";
    private Integer id;
    private Double value;
    private Integer nominal;
    private String currencyName;
    private String currencyCode;
    private LocalDate date;

    public CurrencyExchange() {
    }

    public CurrencyExchange(Integer id, Double value, Integer nominal, String currencyName, String currencyCode, LocalDate date) {
        this.id = id;
        this.value = value;
        this.nominal = nominal;
        this.currencyName = currencyName;
        this.currencyCode = currencyCode;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Integer getNominal() {
        return nominal;
    }

    public void setNominal(Integer nominal) {
        this.nominal = nominal;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
