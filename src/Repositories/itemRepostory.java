package Repositories;

import Model.Item;
import Model.Staff;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

/**
 * Created by chett_000 on 2017/05/11.
 */

public interface itemRepostory extends MongoRepository<Item,String> {

    default Item findByIdOrError(String id) {
        return findById(id).orElseThrow(EntityNotFoundException::new);
    }
    @Query("{ 'time' : ?0,'$and':[{'name' : {$ne : ?1}}, {'name' : {$ne : ?2}}],'isToday':true}")
    List<Item> findAllByTimeAndIsTodayIsTrueAndNameNotOrNameNot(String time,String name,String name1);
    List<Item> findAllByTypeAndIsTodayIsTrue(String type);
    List<Item> findAllByTimeAndIsTodayIsTrue(String type);

    Item findByName(String name);


}
