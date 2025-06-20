package Sanjay.Journel.service;


import Sanjay.Journel.Entity.User;
import Sanjay.Journel.Service.SentimentalAnalysisService;
import Sanjay.Journel.scheduler.UserScheduler;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class SentimentalAnalysisTest {


    @Autowired
    UserScheduler userScheduler;

    @Test
    public void SA_Test(){
        userScheduler.fetchUsersAndSendSaMail();
        log.info("kketi");

    }

    @Test
    public void TEST_2(){
        log.info("TATATATATATAT ATATATATATA TATATATATAT ATATATAT ATATATA");
    }
}
