package com.jforce.VotingApp.Controller;
import com.jforce.VotingApp.DTO.ReqRes;
import com.jforce.VotingApp.Requests.VoteRequest;
import com.jforce.VotingApp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin(origins = "http://127.0.0.1:5500") // Specify the allowed origin
@RequestMapping("/user")
public class UserController{

    @Autowired
    UserService userService;

    @PutMapping("/vote")
    public ResponseEntity setVote(@RequestBody VoteRequest voteRequest){
        ReqRes reqRes = new ReqRes();
        try{
            String response = userService.setVote(voteRequest);
            reqRes.setStatusCode(200);
            reqRes.setMessage(response);
            return new ResponseEntity<>(reqRes,HttpStatus.OK);
        }catch (Exception e){
            reqRes.setStatusCode(400);
            reqRes.setError(e.getMessage());
            return new ResponseEntity<>(reqRes,HttpStatus.BAD_REQUEST);
            }
        }
    }
