package com.halfacode.jwt.controller;

import com.halfacode.jwt.dto.RequestWrapperDTO;
import com.halfacode.jwt.dto.UserRequest;
import com.halfacode.jwt.serive.UserService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping
    public ResponseEntity<RequestWrapperDTO> createAccount(@RequestBody UserRequest userRequest){
        RequestWrapperDTO requestWrapperDTO = userService.addUser(userRequest);
        return ResponseEntity.ok(requestWrapperDTO);
    }
}
