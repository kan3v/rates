package com.test.demo.demo.services;

import com.test.demo.demo.daos.RateDao;
import com.test.demo.demo.lib.ParseXMLRates;
import com.test.demo.demo.models.Rate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PullRates
{

    @Autowired
    RateDao rateDao;

    @Scheduled(cron = "0 0 12 * * ?")
    public void pull()
    {
        ParseXMLRates parseXMLRates = new ParseXMLRates();
        List<Rate> rates = parseXMLRates.getRates();

        for (Rate rate : rates)
        {
            rateDao.save(rate);
        }
    }
}
