package Sanjay.Journel.scheduler;
import Sanjay.Journel.Entity.JournelEntity;
import Sanjay.Journel.Entity.User;
import Sanjay.Journel.Service.MailService;
import Sanjay.Journel.Service.SentimentalAnalysisService;
import Sanjay.Journel.Service.UserDetailsServiceImpl;
import Sanjay.Journel.cache.AppCache;
import Sanjay.Journel.enums.Sentiment;
import Sanjay.Journel.repository.UserRepo;
import Sanjay.Journel.repository.UserRepoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;


@Component
public class UserScheduler {

    @Autowired
    private MailService mailService;

    @Autowired
    private UserRepoImpl userRepo;

    @Autowired
    private SentimentalAnalysisService sentimentalAnalysisService;

    @Autowired
    private AppCache appCache;

//    }
//    @Scheduled(cron = " 0 0 9 * * SUN")
//    public void fetchUsersAndSendSaMail() {
//        List<User> users = userRepo.getUserForSA();
//
//        for (User user : users) {
//            List<JournelEntity> journalEntries = user.getJournalEntries();
//
//            if (journalEntries == null || journalEntries.isEmpty()) {
//                continue; // Skip if no entries exist
//            }
//
//            List<String> filterEntries = journalEntries.stream()
//                    .filter(x -> x.getDate() != null && x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS)))
//                    .map(JournelEntity::getContent) // Assuming `getContent()` retrieves the journal text
//                    .filter(Objects::nonNull) // Avoid null content
//                    .collect(Collectors.toList());
//
//            if (filterEntries.isEmpty()) {
//                continue; // Skip processing if no relevant entries
//            }
//
//            String entry = String.join(" ", filterEntries); // Join with space for better readability
//            String sentiment = sentimentalAnalysisService.getSA(entry);
//
//            mailService.sendEmail(user.getEmail(), "Sentiment for last 7 days", sentiment);
//        }
//    }





    @Scheduled(cron = "0 0 9 * * SUN")
    public void fetchUsersAndSendSaMail() {
        List<User> users = userRepo.getUserForSA();
        for (User user : users) {
            List<JournelEntity> journalEntries = user.getJournalEntries();
            List<Sentiment> sentiments = journalEntries.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x -> x.getSentiment()).collect(Collectors.toList());
            Map<Sentiment, Integer> sentimentCounts = new HashMap<>();
            for (Sentiment sentiment : sentiments) {
                if (sentiment != null)
                    sentimentCounts.put(sentiment, sentimentCounts.getOrDefault(sentiment, 0) + 1);
            }
            Sentiment mostFrequentSentiment = null;
            int maxCount = 0;
            for (Map.Entry<Sentiment, Integer> entry : sentimentCounts.entrySet()) {
                if (entry.getValue() > maxCount) {
                    maxCount = entry.getValue();
                    mostFrequentSentiment = entry.getKey();
                }
            }
            if (mostFrequentSentiment != null) {
                mailService.sendEmail(user.getEmail(),"Sentiment for last 7 days",mostFrequentSentiment.toString());
//                try{
//                   // kafkaTemplate.send("weekly-sentiments", sentimentData.getMail(), sentimentData);
//                }catch (Exception e){
//                    emailService.sendEmail(sentimentData.getEmail(), "Sentiment for previous week", sentimentData.getSentiment());
//                }
            }
        }
    }





    @Scheduled(cron = " * * * * * *")
    public void clearAppCache(){
        appCache.init();
    }

}
