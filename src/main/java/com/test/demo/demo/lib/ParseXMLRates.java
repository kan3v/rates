package com.test.demo.demo.lib;

import com.test.demo.demo.models.Rate;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ParseXMLRates
{
    static final String url = "https://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml";
    static final String CURRENCY = "currency";
    static final String CUBE_NODES = "//Cube/Cube/Cube";
    static final String TIME_NODE = "//Cube/Cube";
    static final String RATE = "rate";
    static final String TIME = "time";

    private Date date;

    private List<Rate> rates = new ArrayList<>();

    public ParseXMLRates()
    {
        try
        {
            Document doc = this.getDocument();

            NodeList nl = getNodeList(doc);

            for (int i = 0; i < nl.getLength(); i++)
            {
                parseNode(nl, i);
            }

        } catch (Exception e)
        {
            System.out.println(e);
        }
    }

    /**
     *
     * @param nl
     * @param i
     */
    private void parseNode(NodeList nl, int i)
    {
        Node node = nl.item(i);
        NamedNodeMap attribs = node.getAttributes();
        if (attribs.getLength() > 0)
        {
            Node currencyAttrib = attribs.getNamedItem(CURRENCY);
            if (currencyAttrib != null)
            {
                String currencyTxt = currencyAttrib.getNodeValue();
                String rateTxt = attribs.getNamedItem(RATE).getNodeValue();

                Rate rate = new Rate(currencyTxt, Float.parseFloat(rateTxt), date);
                rates.add(rate);

            }
        }
    }

    /**
     *
     * @param doc
     * @return
     * @throws XPathExpressionException
     * @throws ParseException
     */
    private NodeList getNodeList(Document doc) throws XPathExpressionException, ParseException
    {
        XPathFactory xPathfactory = XPathFactory.newInstance();
        XPath xpath = xPathfactory.newXPath();

        XPathExpression expr = xpath.compile(CUBE_NODES);
        NodeList nl = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);

        XPathExpression exprTime = xpath.compile(TIME_NODE);

        Node timeNode = (Node) exprTime.evaluate(doc, XPathConstants.NODE);

        String time = timeNode.getAttributes().getNamedItem(TIME).getNodeValue();
        date = new SimpleDateFormat("yyyy-MM-dd").parse(time);
        return nl;
    }

    /**
     *
     * @return
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */

    private Document getDocument() throws ParserConfigurationException, SAXException, IOException
    {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        return db.parse(new URL(url).openStream());
    }


    public List<Rate> getRates()
    {
        return rates;
    }
}
