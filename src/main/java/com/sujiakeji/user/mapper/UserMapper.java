package com.sujiakeji.user.mapper;

import com.sujiakeji.user.domain.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface UserMapper {

    Integer insert(User user);

    User selectOne(Map<String, Object> params);

    Integer selectCount(Map<String, Object> params);

    List<User> selectList(Map<String, Object> params);

    Integer update(User user);

    void delete(Map<String, Object> params);

}