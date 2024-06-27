package com.jforce.VotingApp.Service;

import com.jforce.VotingApp.Models.User;
import com.jforce.VotingApp.Repository.UserRespository;

import com.jforce.VotingApp.Requests.LoginRequest;
import com.jforce.VotingApp.Requests.VoteRequest;
import com.jforce.VotingApp.Response.voteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRespository userRespository;

    public String login(LoginRequest loginRequest){
        User user1 = userRespository.findUserByUsername(loginRequest.getUsername());
        if(user1!=null){
            return user1.getUUID();
        }
        return null;
    }
    public String register(User user){
        user = userRespository.save(user);
        return user.getUUID();
    }

    public String vote(VoteRequest voteRequest){
        User user = userRespository.findById(voteRequest.getId()).get();
        user.setVote(voteRequest.getVote());
        userRespository.save(user);
        return "user voted successfully!";
    }

    public User getUser(String id){
        return userRespository.findById(id).get();
    }

    public List<Object[]> getVotes(){
        return userRespository.getVotes();
    }
}
