package com.jforce.VotingApp.Service;

import com.jforce.VotingApp.Config.JWTUtils;
import com.jforce.VotingApp.DTO.ReqRes;
import com.jforce.VotingApp.Models.User;
import com.jforce.VotingApp.Repository.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private UserRespository userRespository;
    @Autowired
    private JWTUtils jwtUtils;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public ReqRes register(ReqRes registrationRequest){
        ReqRes resp = new ReqRes();

        try {
            Optional<User> user = userRespository.findByUsername(registrationRequest.getUsername());
            if(user.isPresent()){
                throw new Exception("User Already Exist!");
            }


            User ourUser = new User();
            ourUser.setUsername(registrationRequest.getUsername());
            ourUser.setEmailId(registrationRequest.getEmail());
            ourUser.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
            ourUser.setPhoneNo(registrationRequest.getPhone());
            ourUser.setVote(registrationRequest.getVote());
            ourUser.setRole(registrationRequest.getRole());

            User ourUsersResult = userRespository.save(ourUser);
            if (ourUsersResult != null) {
                resp.setUser((ourUsersResult));
                resp.setMessage("User Saved Successfully");
                resp.setStatusCode(200);
            }
        }catch (Exception e){
            resp.setStatusCode(500);
            resp.setError("Please provide valid details!");
        }
        return resp;
    }

    public ReqRes login(ReqRes loginRequest){
        ReqRes response = new ReqRes();
        try {
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                            loginRequest.getPassword()));
            var user = userRespository.findByUsername(loginRequest.getUsername()).orElseThrow();
            var jwt = jwtUtils.generateToken(user);
            response.setStatusCode(200);
            response.setToken(jwt);
            response.setRole(user.getRole());
            response.setExpirationTime("24Hrs");
            response.setMessage("Successfully Logged In");

        }catch (Exception e){
            response.setStatusCode(500);
            response.setError(e.getMessage());
        }
        return response;
    }

}
