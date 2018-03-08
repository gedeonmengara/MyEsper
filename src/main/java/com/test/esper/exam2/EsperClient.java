package com.test.esper.exam2;

import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;

public class EsperClient {

    private EPServiceProvider engine;

    public EsperClient() {
        //obtain an engine instance
        this.engine = EPServiceProviderManager.getDefaultProvider();
        //System.out.println(engine.getURI());
    }

//    public void personEventProcess() {
//        //tell the engine about the event type
//        engine.getEPAdministrator().getConfiguration().addEventType(PersonEvent.class);
//        //create an epl statement
//        String epl = "select name, age from PersonEvent";
//        EPStatement statement = engine.getEPAdministrator().createEPL(epl);
//        statement.addListener(new PersonEventListener());
//    }

    public void personEventProcess() {

        //tell the engine about the event type
        engine.getEPAdministrator().getConfiguration().addEventType(PersonEvent.class);
        //create an epl statement
        String epl = "select avg(age) from PersonEvent"+ ".win:length_batch(2)";
        EPStatement statement = engine.getEPAdministrator().createEPL(epl);
        //attach a callback to receive the results
        statement.addListener(new PersonEventListener());
    }

    public void send(Event event) {
        engine.getEPRuntime().sendEvent(event);
    }
}