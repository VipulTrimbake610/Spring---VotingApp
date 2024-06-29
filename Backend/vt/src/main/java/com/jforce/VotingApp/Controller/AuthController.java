package com.jforce.VotingApp.Controller;

import com.jforce.VotingApp.DTO.ReqRes;
import com.jforce.VotingApp.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ReqRes> regeister(@RequestBody ReqRes reg){
        return new ResponseEntity<ReqRes>(authService.register(reg), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<ReqRes> login(@RequestBody ReqRes req){
        return new ResponseEntity<ReqRes>(authService.login(req), HttpStatus.OK);
    }


}
