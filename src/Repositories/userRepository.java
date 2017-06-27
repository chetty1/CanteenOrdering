package Repositories;

import Model.Staff;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by chett_000 on 2017/05/10.
 */
public interface userRepository extends MongoRepository<Staff, String> {

    Staff findByUsername(String username);

    Staff findByPassword(String password);

    List<Staff> findAllByName(String name);

    List<Staff> findAllByNameLike(String name);

    Staff findByIdLike(String id);

    Staff findById(String id);
}
