package com.sysgears.example.domain.symbols;

/**
 * Created by yevgen on 16.12.16.
 */
public class Number implements Mom{

    private Double value = 0.0;

    public String getValue() {
        return String.valueOf(value);
    }

    public Double getDoubleValue() {
        return value;
    }

    public Number(String value){
        this.value = Double.valueOf(value);
    }

}
