package com.test.demo.demo.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Rate
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String currency;

    private Float rate;

    @Temporal(TemporalType.DATE)
    private Date date;

    @DateTimeFormat
    private Date dateCreated;

    public Rate()
    {
    }

    public Rate(String currency,Float rate,Date date)
    {
        this.currency = currency;
        this.rate = rate;
        this.date = date;
        this.dateCreated = new Date();
    }


    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getCurrency()
    {
        return currency;
    }

    public void setCurrency(String currency)
    {
        this.currency = currency;
    }

    public Float getRate()
    {
        return rate;
    }

    public void setRate(Float rate)
    {
        this.rate = rate;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    public Date getDateCreated()
    {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated)
    {
        this.dateCreated = dateCreated;
    }
}
