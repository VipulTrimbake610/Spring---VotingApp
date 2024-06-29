package com.jforce.VotingApp.Service;

import com.jforce.VotingApp.Config.JWTAuthFilter;
import com.jforce.VotingApp.Config.JWTUtils;
import com.jforce.VotingApp.DTO.ReqRes;
import com.jforce.VotingApp.Models.User;
import com.jforce.VotingApp.Repository.UserRespository;
import com.jforce.VotingApp.Requests.VoteRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRespository userRespository;

    @Autowired
    private JWTUtils jwtUtils;

    public String setVote(VoteRequest voteRequest)throws Exception{
        String username = jwtUtils.extractUsername(jwtUtils.jToken);

        User user = userRespository.findByUsername(username).get();
        if(user.getVote() != null && !user.getVote().equals("")){
            throw new Exception("User Already Voted!!!");
        }

        user.setVote(voteRequest.getVote());
        user = userRespository.save(user);
        if(user == null){
            throw new Exception("Unable to save vote in the DB!");
        }

        return "user voted successfully with username : "+user.getUsername();
    }
}
