package Sanjay.Journel.service;

import Sanjay.Journel.Service.UserDetailsServiceImpl;
import Sanjay.Journel.repository.UserRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.ArrayList;

import static org.mockito.Mockito.when;

//@SpringBootTest
public class UserDetailServiceImplTests {


    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Mock
    private UserRepo userRepo;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Disabled
    @Test
    void loadUserByUsernameTest(){
        when(userRepo.findByUserName(ArgumentMatchers.anyString())).thenReturn((Sanjay.Journel.Entity.User) User.builder().username("ram").password("inrinrick").roles(String.valueOf(new ArrayList<>())).build());
        UserDetails user = userDetailsService.loadUserByUsername("ram");
        Assertions.assertNotNull(user);
    }






}
