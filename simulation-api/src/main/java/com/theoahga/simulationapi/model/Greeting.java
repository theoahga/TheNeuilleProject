package com.theoahga.simulationapi.model;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Greeting {
    private long test;
    private String test1;
    public Greeting(long test, String test1){
        this.test = test;
        this.test1 = test1;
    }

    public long getTest() {
        return test;
    }

    public String getTest1() {
        return test1;
    }
}
