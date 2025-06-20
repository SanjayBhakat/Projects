package Sanjay.Journel.Service;
import Sanjay.Journel.Entity.JournelEntity;
import Sanjay.Journel.Entity.User;
import Sanjay.Journel.repository.JournelRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class JournelService {

    @Autowired
    private JournelRepo journelRepo;

    @Autowired
    private UserService userService;

    // Get all entries
    public List<JournelEntity> getAll() {
        return journelRepo.findAll();
    }

    // Save an entry
//    @Transactional
    public void saveEntry(JournelEntity je, String userName) {
        try {
            User user =userService.findByUserName(userName);
            je.setDate(LocalDateTime.now());
            JournelEntity saved=journelRepo.save(je);
            user.getJournalEntries().add(saved);
//            user.setUserName(null);
            userService.saveUser(user);
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException("An error occurred while saving the entry ",e);
        }
    }

    public void saveEntry(JournelEntity je){
        journelRepo.save(je);
    }

    // Delete an entry by ID
    public boolean deleteById(ObjectId id, String userName) {
        boolean removed = false;
        try {
            User user =userService.findByUserName(userName);
            user.getJournalEntries().removeIf(x -> x.getId().equals(id));
            if (removed){
                userService.saveNewUser(user);
                journelRepo.deleteById(id);
            }
        } catch (Exception e) {
            throw new RuntimeException("An error Occurred"+e);
        }

        return removed;
    }

    // Get an entry by ID
    public Optional<JournelEntity> findById(ObjectId id) {
        return journelRepo.findById(id);
    }
    public void deleteAll() {
        journelRepo.deleteAll();
    }

}
