package Sanjay.Journel.service;


import Sanjay.Journel.cache.AppCache;
import Sanjay.Journel.scheduler.UserScheduler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserSchedulersTest {

    @Autowired
    private UserScheduler userScheduler;



    @Test
    public void SchedularsTest(){
        userScheduler.fetchUsersAndSendSaMail();
    }
}

