package com.test.esper.exam1;

import com.espertech.esper.client.*;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        EPServiceProvider epService = EPServiceProviderManager.getDefaultProvider();
        EPAdministrator admin = epService.getEPAdministrator();
        String product = ScuRate.class.getName();
        String epl = "select respCode,count(1) from " + product + " (respCode='00').win:length_batch(10)";

        EPStatement state = admin.createEPL(epl);
        state.addListener(new AppleListener());
        EPRuntime runtime = epService.getEPRuntime();
        for (int i = 0; i < 10; i++) {
            ScuRate rate = new ScuRate();
            rate.setRespCode("00");
            runtime.sendEvent(rate);
        }
    }
}

class AppleListener implements UpdateListener {
    public void update(EventBean[] newEvents, EventBean[] oldEvents) {
        if (newEvents != null) {
            Long count = (Long) newEvents[0].get("count(1)");
            System.out.println(count);
        }
    }
}