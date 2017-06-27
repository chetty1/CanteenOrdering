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

Tranaction findByUser(Staff user);

    @Query("{ 'user.username' : ?0 }")
    List<Tranaction> findAllByUser(String name);



    List<Tranaction>  findAllByDate(String date);


}
