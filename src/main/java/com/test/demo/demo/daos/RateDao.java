package com.test.demo.demo.daos;




import com.test.demo.demo.models.Rate;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface RateDao extends CrudRepository<Rate, Long>, RateDaoImpl.RateI
{
    List<Rate>findAllByDate(Date date);

    List<Rate>findAllByCurrency(String currency);

    List<Rate>findAllByCurrencyAndDate(String currency, Date date);

}
