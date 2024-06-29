package com.jforce.VotingApp.Service;

import com.jforce.VotingApp.Models.User;
import com.jforce.VotingApp.Repository.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    @Autowired
    UserRespository userRespository;

    public User getUser(String id){
        return userRespository.findById(id).get();
    }

    public List<Object[]> getAllCandidateVotes(){
        List<Object[]> userRespositoryVotes = userRespository.getVotes();
        return userRespositoryVotes;
    }
}
