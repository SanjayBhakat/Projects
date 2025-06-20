package Sanjay.Journel.service;


import Sanjay.Journel.Service.MailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MailServiceTest {

    @Autowired
    private MailService mailService;

    @Test
    void testSendMail(){
        mailService.sendEmail("bhakatsanjay3@gmail.com","Testing","JNL");
    }
}
