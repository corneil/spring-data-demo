package org.springframework.data.demo.repository;

import org.springframework.data.demo.data.UserInfo;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserInfo, String> {
    public UserInfo findByUserId(String userId);
}
