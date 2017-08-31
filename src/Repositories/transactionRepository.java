package Repositories;

import Model.Staff;
import Model.Tranaction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

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


}
