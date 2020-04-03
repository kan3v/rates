package com.test.demo.demo.daos;


import com.test.demo.demo.models.Rate;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;

public class RateDaoImpl
{
    public interface RateI
    {
        List<Rate>find(String currency, Date date);
    }

    @Autowired
    EntityManager em;

    public List<Rate>find(String currency, Date date)
    {
        CriteriaBuilder criteria = em.getCriteriaBuilder();
        CriteriaQuery<Rate> query = criteria.createQuery(Rate.class);
        Root<Rate> root = query.from(Rate.class);

        Predicate pm = criteria.and();

        if (null != currency &&currency.length() > 0)
        {
            Predicate p1 = criteria.and((criteria.equal(root.get("currency"), currency)));
            pm.getExpressions().add(p1);
        }

        if (null != date)
        {
            Predicate p2 = criteria.and((criteria.equal(root.get("date"), date)));
            pm.getExpressions().add(p2);
        }

        if (pm.isNotNull() != null)
        {
            query.where(pm).orderBy(criteria.desc(root.get("currency")));
        }
        return em.createQuery(query.select(root)).getResultList();
    }
}
