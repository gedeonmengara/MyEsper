package com.test.esper.exam2;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;

public class PersonEventListener implements UpdateListener {

    public void update(EventBean[] newEvents, EventBean[] oldEvents) {
        if (newEvents != null) {
            Double avg = (Double) newEvents[0].get("avg(age)");
            System.out.println("average age is " + avg);
        }

//        if(newEvents != null){
//            EventBean event = newEvents[0];
//            System.out.println("Name: "+event.get("name")+", Age: "+event.get("age"));
//        }else{
//            System.out.println("newEvents is null");
//        }

    }
}