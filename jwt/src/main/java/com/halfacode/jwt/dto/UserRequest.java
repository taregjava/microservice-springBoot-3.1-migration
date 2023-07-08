package com.halfacode.jwt.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    private String firstName;
    private String lastName;
    private String gender;

    private String accountNumber;
    private String address;
    private BigDecimal accountBalance;

    private String email;
    private String password;

}
