package ait.cohort60.accounting.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserRegisterDto {
    private String login;
    private String firstName;
    private String lastName;
    private String password;
    private List<String> roles;
}
