package Sanjay.Journel.Controller;
import Sanjay.Journel.Entity.User;
import Sanjay.Journel.Service.UserService;
import Sanjay.Journel.Service.WeatherService;
import Sanjay.Journel.api.response.WheatherResponse;
import Sanjay.Journel.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/all")
    private List<User> getAll(){
        return  userService.getJournelEntries();
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User uE) {
        userService.saveNewUser(uE);
        return new ResponseEntity<>(uE,HttpStatus.CREATED);
    }


    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName= authentication.getName();
        User userInDb= userService.findByUserName(userName);

        if(userInDb !=null){
            userInDb.setUserName(user.getUserName());
            userInDb.setPassword(user.getPassword());
            userService.saveNewUser(userInDb);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUserById(){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        userRepo.deleteByUserName(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @GetMapping
    public ResponseEntity<?> greeting(){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        WheatherResponse wheatherResponse = weatherService.getWeather("Bhubaneswar");
        String greeting = "";


        if(wheatherResponse !=null){
            greeting = ",Weather feels like : " + wheatherResponse.getCurrent().getTemperature();

        }
        return new ResponseEntity<>("Hi  : "+ authentication.getName()+ greeting,HttpStatus.OK);
    }



}
