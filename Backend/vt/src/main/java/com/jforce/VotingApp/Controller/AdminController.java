package com.jforce.VotingApp.Controller;

import com.jforce.VotingApp.Models.User;
import com.jforce.VotingApp.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController
{
    @Autowired
    AdminService adminService;

    @GetMapping("/getUser")
    public ResponseEntity getUser(@RequestParam String id){
        try{
            User user = adminService.getUser(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getVotes")
    public ResponseEntity getAllCandidateVotes(){
        try {
            List<Object[]> list = adminService.getAllCandidateVotes();
            return new ResponseEntity<>(list,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
