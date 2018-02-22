package Repositories;

import Model.Staff;
import Model.Tranaction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Date;
import java.util.List;


/**
 * Created by chett_000 on 2017/06/08.
 */
public interface transactionRepository extends MongoRepository<Tranaction,String> {

Tranaction findById(String Id);

    @Query("{ 'user.username' : ?0 }")
    List<Tranaction> findAllByUser(String name);

    //@Query("{'user.username':?0,'date':?1}")
List<Tranaction> findAllByUserAndDate(Staff user,String date);


    List<Tranaction>  findAllByDate(String date);
   // @Query("{'$or':[ {'type':?0}, {'name':?1} ]}")

    @Query("{'time' : {$ne : ?0},'food.name' : {$ne : ?1}}")
    List<Tranaction> findAllByNotTime(String time1,String time2);

    @Query("{'date':{ $gte: ?0, $lte: ?1}}")
List<Tranaction> findAllBetween(String date1, String date2);

}
