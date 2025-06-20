package Sanjay.Journel.service;

import Sanjay.Journel.Entity.JournelEntity;
import Sanjay.Journel.Entity.User;  // Correct import
import Sanjay.Journel.Service.UserService;
import Sanjay.Journel.repository.UserRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserService userService;

    @Test
    public void testFindByUserName() {
        assertEquals(4, 2 + 2);

        User user = userRepo.findByUserName("Sanjay");
        assertNotNull(user, "User should not be null");


    }
}
