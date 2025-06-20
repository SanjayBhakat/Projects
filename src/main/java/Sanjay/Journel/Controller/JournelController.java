package Sanjay.Journel.Controller;

import Sanjay.Journel.Entity.JournelEntity;
import Sanjay.Journel.Entity.User;
import Sanjay.Journel.Service.JournelService;
import Sanjay.Journel.Service.UserService;
import Sanjay.Journel.repository.JournelRepo;
import Sanjay.Journel.repository.UserRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/journal")
public class JournelController {

    @Autowired
    private JournelService journelService;

    @Autowired
    private UserService userService;




    @GetMapping
    public ResponseEntity<?> getAllJournalEntriesOfUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User byUserName= userService.findByUserName(userName);
        List<JournelEntity> all = byUserName.getJournalEntries();
        if (all != null && !all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //CreateEntry
    @PostMapping
    public ResponseEntity<JournelEntity> createEntry(@RequestBody JournelEntity JRL) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        try {
            journelService.saveEntry(JRL,userName);
            return new ResponseEntity<>(JRL,HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteAll(){
        journelService.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }



    //GetById
    @GetMapping("id/{myid}")
    public ResponseEntity <JournelEntity> getByIds(@PathVariable String myid){
        ObjectId objectId =new ObjectId(myid);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user=userService.findByUserName(userName);
        List<JournelEntity> collect =user.getJournalEntries().stream().filter(x -> x.getId().equals(objectId)).collect(Collectors.toList());
        if(!collect.isEmpty()){
            Optional<JournelEntity> journelEntity =journelService.findById(objectId);
            if(journelEntity.isPresent()){
                return new ResponseEntity<>(journelEntity.get(),HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);


    }

    //Delete
    @DeleteMapping("id/{myid}")
    public ResponseEntity<?> deletetJournalEntryByIds(@PathVariable ObjectId myid){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        boolean removed =journelService.deleteById(myid,userName);
        if(removed){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("id/{userName}/{myid}")
    public ResponseEntity<?> updatedId(
            @PathVariable ObjectId myId,
            @RequestBody JournelEntity newEntry){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.findByUserName(userName);
        List<JournelEntity> collect = user.getJournalEntries().stream().filter(x -> x.getId().equals(myId)).collect(Collectors.toList());
        if (!collect.isEmpty()) {
            Optional<JournelEntity> journalEntry = journelService.findById(myId);
            if (journalEntry.isPresent()) {
                JournelEntity old = journalEntry.get();
                old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());
                old.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : old.getContent());
                journelService.saveEntry(old);
                return new ResponseEntity<>(old, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }




}







