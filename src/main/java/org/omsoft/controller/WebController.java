package org.omsoft.controller;

import org.omsoft.dto.CustomRequest;
import org.omsoft.dto.CustomResponse;
import org.omsoft.services.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;

@RestController
public class WebController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtil jwtUtil;

    @GetMapping("/web")
    public String webEndpoint(){
        return " This is web endpoint.";
    }
    @GetMapping("/webone")
    public String webEndpointOne(){
        return " This is web 1 endpoint.";
    }
    @GetMapping("/webtwo")
    public String webEndpointTwo(){
        return " This is web 2 endpoint.";
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> webAuthenticate(@RequestBody CustomRequest request){
        System.out.println(request.getUserName());
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(),request.getPassword()));
        final String jwtString = jwtUtil.generateToken(request.getUserName(),request.getPassword());
        System.out.println(jwtString);
        return ResponseEntity.ok(new CustomResponse(jwtString));


    }


}
