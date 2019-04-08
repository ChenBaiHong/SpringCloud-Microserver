package com.baihoomuch.cloud.service;

import com.baihoomuch.cloud.entity.User;

/**
 * Description: microservice-simple-provider-user
 * auther Administrator on 2018/7/5
 *
 * User 表映射实体操作逻辑
 */

public interface UserService {
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
