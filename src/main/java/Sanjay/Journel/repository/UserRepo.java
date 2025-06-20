package Sanjay.Journel.repository;

import Sanjay.Journel.Entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<User, ObjectId> {
    User findByUserName(String username);


    void deleteByUserName(String userName);
}
