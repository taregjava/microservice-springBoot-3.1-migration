package com.halfacode.jwt.serive;

import com.halfacode.jwt.dto.RequestWrapperDTO;
import com.halfacode.jwt.dto.UserRequest;
import com.halfacode.jwt.entity.User;
import com.halfacode.jwt.excption.DuplicateRecordException;
import com.halfacode.jwt.excption.Error;
import com.halfacode.jwt.excption.UserUncheckedBusinessException;
import com.halfacode.jwt.repository.UserRepository;
import com.halfacode.jwt.utils.CommonUtils;
import com.halfacode.jwt.utils.UserUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UserServiceImpl implements UserService{

  private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public RequestWrapperDTO addUser(UserRequest userRequest) {

        if (userRepository.existsByEmail(userRequest.getEmail())){
            throw new DuplicateRecordException();
        //  return CommonUtils.mapToWrapper(UserUtils.ACCOUNT_EXIST_CODE,UserUtils.ACCOUNT_EXIST_MESSAGE);
        }

        User newUser =User.builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .gender(userRequest.getGender())
                .address(userRequest.getAddress())

                .accountNumber(UserUtils.generateAccountNumber())
                .email(userRequest.getEmail())
                .accountBalance(BigDecimal.ZERO)
                .status("ACTIVE")
                .password(userRequest.getPassword())
                .build();
        User saveUser = userRepository.save(newUser);
        return CommonUtils.mapToWrapper(UserUtils.ACCOUNT_CREATION_SUCCESS,
                            UserUtils.ACCOUNT_CREATION_MESSAGE,
                                        saveUser.getAccountBalance());
    }

    private void extracted(UserRequest userRequest) {
        User newUser =User.builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .gender(userRequest.getGender())
                .address(userRequest.getAddress())

                .accountNumber(UserUtils.generateAccountNumber())
                .email(userRequest.getEmail())
                .accountBalance(BigDecimal.ZERO)
                .status("ACTIVE")
                .password(userRequest.getPassword())
                .build();
    }
}
