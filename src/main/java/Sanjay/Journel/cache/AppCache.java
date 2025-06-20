package Sanjay.Journel.cache;


import Sanjay.Journel.Entity.ConfigJournelAppEntity;
import Sanjay.Journel.repository.ConfigJournelAppEntityRepo;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.annotation.Collation;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {


    public enum keys{
        WEATHER_API;
    }


    @Autowired
    private ConfigJournelAppEntityRepo configJournelAppEntityRepo;

    public Map<String,String> appCache;


    @PostConstruct
    public void  init(){
        appCache = new HashMap<>();
        List<ConfigJournelAppEntity> all = configJournelAppEntityRepo.findAll();
        for(ConfigJournelAppEntity configJournelAppEntity:all){
            appCache.put(configJournelAppEntity.getKey(), configJournelAppEntity.getValue());
        }
    }

}
