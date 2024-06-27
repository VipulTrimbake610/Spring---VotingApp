package com.jforce.VotingApp.Controller;

import com.jforce.VotingApp.Models.User;
import com.jforce.VotingApp.Requests.LoginRequest;
import com.jforce.VotingApp.Requests.VoteRequest;
import com.jforce.VotingApp.Response.voteResponse;
import com.jforce.VotingApp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500") // Specify the allowed origin
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest){
        return userService.login(loginRequest);
    }

    @PostMapping("/register")
    public String register(@RequestBody User user){
        return userService.register(user);
    }

    @PutMapping("/vote")
    public String vote(@RequestBody VoteRequest voteRequest){
        return userService.vote(voteRequest);
    }

    @GetMapping("/getUser")
    public User getUser(@RequestParam String id){
        return userService.getUser(id);
    }

    @GetMapping("/getVotes")
    public List<Object[]> getVotes(){
        return userService.getVotes();
    }
}
