package Sanjay.Journel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.bson.Document;
import org.springframework.web.client.RestTemplate;


//@EnableTransactionManagement
@SpringBootApplication
@EnableScheduling
public class JournelAppApplication {

	public static void main(String[] args) {

		SpringApplication.run(JournelAppApplication.class, args);
	}
//	@Bean
//	public PlatformTransactionManager manager(MongoDatabaseFactory databaseFactory){
//		return new MongoTransactionManager(databaseFactory);
//	}

	@Bean//Used in WeatherStack Server
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}



}
