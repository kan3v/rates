package com.test.demo;

import com.test.demo.demo.daos.RateDao;
import com.test.demo.demo.lib.ParseXMLRates;
import com.test.demo.demo.models.Rate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.Date;
import java.util.List;

@SpringBootTest
class DemoApplicationTests
{
    @Autowired
    RateDao rateDao;

    @Test
    @Rollback
    void testPulling() throws Exception
    {
        ParseXMLRates parseXMLRates = new ParseXMLRates();
        List<Rate> rates = parseXMLRates.getRates();
        if (rates.size() == 0)
        {
            throw new Exception("Something went wrong. There are 0 rates pulled");
        }
    }

    @Test
    @Rollback
    void testFind()
    {
        Date date = null;
        String currency = "USD";
        List<Rate> rateList = rateDao.find(currency, date);
    }

}
