package Sanjay.Journel.repository;

import Sanjay.Journel.Entity.ConfigJournelAppEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigJournelAppEntityRepo extends MongoRepository<ConfigJournelAppEntity, ObjectId> {

}
