package Repositories;

import Model.WeatherObject;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by chett_000 on 2017/09/20.
 */
public interface weatherRepository extends MongoRepository<WeatherObject,String>{

WeatherObject findByDate(String Date);
}
