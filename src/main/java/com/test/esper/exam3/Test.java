package com.test.esper.exam3;

import com.espertech.esper.client.*;

public class Test {

    public static void main(String[] args) throws InterruptedException {
        EPServiceProvider epService = EPServiceProviderManager.getDefaultProvider();

        EPAdministrator admin = epService.getEPAdministrator();

        String product = Apple.class.getName();
        String epl = "select avg(price) from " + product + ".win:length_batch(3)";
        EPStatement state = admin.createEPL(epl);
        state.addListener(new AppleListener());

        EPRuntime runtime = epService.getEPRuntime();

        Apple apple1 = new Apple(1, 5);
        runtime.sendEvent(apple1);

        Apple apple2 = new Apple(2, 2);
        runtime.sendEvent(apple2);

        Apple apple3 = new Apple(3, 5);
        runtime.sendEvent(apple3);
    }
}