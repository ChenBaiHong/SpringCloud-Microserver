package com.baihoomuch.cloud.repository;

import com.baihoomuch.cloud.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Description: microservice-simple-provider-user
 * auther Administrator on 2018/7/5
 *
 * User 实体，dao层操作
 *
 */
public interface   UserRepository  extends JpaRepository<User,Long> {

    /**
     * 通过用户名和密码查询用户
     * @param password
     * @param username
     * @return
     */
    User findByPasswordAndUsername(Long password, String username);

    /**
     * 通过objectid查询用户
     * @param objectid
     * @return
     */
    User findByObjectid(Long objectid);
}
