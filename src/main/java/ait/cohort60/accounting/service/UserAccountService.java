package ait.cohort60.accounting.service;

import ait.cohort60.accounting.dto.*;

public interface UserAccountService {
     UserDto register(UserRegisterDto userRegisterDto);

     UserDto getUser(String login);

     UserDto removeUser(String login);

     UserDto updateUser(String login, UserEditDto userEditDto);

     RolesDto changeRolesList(String login, String role, boolean isAddRole);

     void changePassword(String login, String newPassword);

     void sendEmail(EmailDto emailDto);
}