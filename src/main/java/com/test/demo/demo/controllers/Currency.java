package com.test.demo.demo.controllers;



import com.test.demo.demo.daos.RateDao;
import com.test.demo.demo.models.Rate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class Currency
{


    @Autowired
    RateDao rateDao;


    @RequestMapping(value = "/find", method = RequestMethod.GET)
    @ResponseBody
    public List<Rate> ratesList(@RequestParam(value = "currency", required = false) String currency, @RequestParam(value = "time", required = false) String time) throws ParseException
    {
        Date date = null;
        if (null != time)
        {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(time);
        }
        return rateDao.find(currency, date);
    }


}
