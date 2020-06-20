package com.example.franklin.hudumu.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PaymentPlansModel {

    public static HashMap<String, List<String>> populateData(){
        HashMap<String, List<String >> expandableListInfo = new HashMap<String, List<String>>();

        List<String> daily = new ArrayList<String>();
        daily.add("This plan caters for a daily plan in that users can make requests of errands on a daily basis depending on their needs.");
        daily.add("Service charge: Ksh. 350.00");

        List<String> twoWeeks = new ArrayList<String>();
        twoWeeks.add("This plan caters for a monthly plan in that users can request a total of four (4) errands performed to them. Users" +
                " hence have the leisure of choosing when to have their errands done and group them accordingly.");
        twoWeeks.add("Service charge: Ksh. 3,500.00");

        List<String> monthly = new ArrayList<String>();
        monthly.add("This plan caters for a monthly plan in that users can request a total of eight (8) errands performed to them. Users" +
                " hence have the leisure of choosing when to have their errands done and group them accordingly.");
        monthly.add("Service charge: Ksh. 7,000.00");

        expandableListInfo.put("Daily Plan", daily);
        expandableListInfo.put("Two-weeks Plan", twoWeeks);
        expandableListInfo.put("Monthly Plan", monthly);

        return expandableListInfo;
    }
}
