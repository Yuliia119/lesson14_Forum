package ait.cohort60.accounting.service;

import ait.cohort60.accounting.dto.UserRegisterDto;
import ait.cohort60.post.model.Post;

public interface AccountService {
     void register(UserRegisterDto dto);
}
