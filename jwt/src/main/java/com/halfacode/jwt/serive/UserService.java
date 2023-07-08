package com.halfacode.jwt.serive;

import com.halfacode.jwt.dto.UserRequest;
import com.halfacode.jwt.dto.RequestWrapperDTO;
import org.springframework.stereotype.Service;


public interface UserService {

    RequestWrapperDTO addUser(UserRequest userRequest);
}
