package Sanjay.Journel.Service;
import Sanjay.Journel.Entity.User;
import Sanjay.Journel.repository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepo userRepo;

    private static final PasswordEncoder passwordEncoder =new BCryptPasswordEncoder();
    //Hard Coded Initialization
    //private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    // Get all entries
    public List<User> getJournelEntries() {
        return userRepo.findAll();
    }

    // Save an entry
    public void saveNewUser(User SaveUser) {
        try {
            SaveUser.setPassword(passwordEncoder.encode(SaveUser.getPassword()));
            SaveUser.setRoles(Arrays.asList("USER"));
            userRepo.save(SaveUser);
        }catch (Exception e){
            log.info("Error Occurred for {}",SaveUser.getUserName(),e);
            log.warn("HAHAHAHAHA");
            log.debug("hahaha");

        }
    }

    //SecondEntry for a user
    public User saveUser(User SaveUser) {
//        SaveUser.setPassword(passwordEncoder.encode(SaveUser.getPassword()));
        return userRepo.save(SaveUser);
    }

    // Delete an entry by ID
    public void deleteById(ObjectId id) {
        userRepo.deleteById(id);
    }

    // Get an entry by ID
    public Optional<User> findById(ObjectId id) {
        return userRepo.findById(id);
    }

    public User findByUserName(String userName) {
        return userRepo.findByUserName(userName);
    }
}

