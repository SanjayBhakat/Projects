package Sanjay.Journel.Controller;


import Sanjay.Journel.Entity.User;
import Sanjay.Journel.Service.UserService;
import Sanjay.Journel.cache.AppCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;


    @Autowired
    private AppCache appCache;

    @GetMapping("/all-users")
    public ResponseEntity<?> getAllUsers(){
        List<User> all =userService.getJournelEntries();
        if(all !=null && !all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create-admin-user")
    public void createUser(@RequestBody User user){
        userService.saveUser(user);
    }

    @GetMapping("/clear-app-cache")
    public void clearAppCache(){
        appCache.init();
    }
}
