package com.example.franklin.hudumu.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListViewData {

    public static HashMap<String, List<String>> populateData(){
        HashMap<String, List<String >> expandableListInfo = new HashMap<String, List<String>>();

        List<String> huduma = new ArrayList<String>();
        huduma.add("A platform which seeks to revolutionalize how errands are performed.");

        List<String> services = new ArrayList<String>();
        services.add("A wide range of services ranging from your favourite pizza orders, car washing, shopping, Market, dobi services and much more.");

        List<String> paymentplan = new ArrayList<String>();
        paymentplan.add("Payment plans are categorized into two. Monthly plans and two weeks plan.");



        expandableListInfo.put("What services does Huduma offer?", services);
        expandableListInfo.put("What are the payment plans?", paymentplan);
        expandableListInfo.put("What is Huduma?", huduma);

        return expandableListInfo;
    }
}
