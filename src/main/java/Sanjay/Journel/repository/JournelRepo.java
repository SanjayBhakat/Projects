package Sanjay.Journel.repository;

import Sanjay.Journel.Entity.JournelEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournelRepo extends MongoRepository< JournelEntity, ObjectId> {
}
