package com.example.shoppingList.validation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class CurrencyExchangeServiceImpl implements CurrencyExchangeService {

    @Autowired
    private RestTemplate restTemplate;

    private final Logger logger = Logger.getLogger(getClass().getName());

    static List<String> links;

    static List<String> endPartsOfLink;

    static {

        links = new LinkedList<>();
        links.add("https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/latest/currencies/");
        links.add("https://raw.githubusercontent.com/fawazahmed0/currency-api/1/latest/currencies/");
        endPartsOfLink = new LinkedList<>();
        endPartsOfLink.add(".min.json");
        endPartsOfLink.add(".json");

    }

    @Override
    public double getRate(String from, String to) {
        double rate = 0;
        for (String link : links) {
            for (String endPart : endPartsOfLink) {
                try {
                    String response = restTemplate.getForObject(link + from.toLowerCase() + "/" + to.toLowerCase() + endPart, String.class);
                    if (response != null) {
                        rate = exchangeRateExtractor(response,to);
                        logger.info("Exchange rate from " + from + " to " + to + " is "+ rate);
                        break;
                    }
                } catch (Exception e) {
                    logger.warning("Exception caused by " + e.getMessage());
                }
            }
        }
        return rate;
    }

    private double exchangeRateExtractor(String json,String currency) {
        for(String line :json.split("[{,}]")){
            if(line.contains(currency)){
                return Double.parseDouble(line.substring(line.indexOf(":")+1));
            }
        }
        return -1;
    }

}
