package com.test.esper.exam2;

public class Test {

    @SuppressWarnings("static-access")
    public static void main(String[] args) throws InterruptedException {
        EsperClient ec = new EsperClient();
        ec.personEventProcess();
        ec.send(new PersonEvent("liangyihuai", 10));
        ec.send(new PersonEvent("huai", 20));
    }
}