package Sanjay.Journel;

import Sanjay.Journel.Service.UserDetailsServiceImpl;
import Sanjay.Journel.repository.UserRepoImpl;
import lombok.extern.slf4j.Slf4j;
import org.bson.assertions.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@Slf4j
@SpringBootTest
class JournelAppApplicationTests {

	@Autowired
	UserRepoImpl userRepo;

	@Test
	void contextLoads() {
		Assert.notNull(userRepo.getUserForSA(),"Not null");
		log.info("HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH");

	}

}
