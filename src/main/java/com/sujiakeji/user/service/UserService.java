package com.sujiakeji.user.service;

import com.sujiakeji.user.domain.User;
import com.sujiakeji.user.dto.common.PageRequestDto;
import com.sujiakeji.user.dto.common.QueryFilterDto;
import com.sujiakeji.user.mapper.UserMapper;
import com.sujiakeji.user.util.common.DateTimeUtils;
import com.sujiakeji.user.util.mybatis.MybatisUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MybatisUtils mybatisUtils;

    @Autowired
    private DateTimeUtils dateTimeUtils;

    @Transactional
    public User create(User user) {
        if (user != null) {
            user.setCreateTime(dateTimeUtils.getCurrentDateTime());
        }
        userMapper.insert(user);
        Long userId = user.getId();
        return findById(userId);
    }

    public User findById(Long id) {
        if (id == null) {
            return null;
        }
        List<QueryFilterDto> dtoList = new ArrayList<>();
        dtoList.add(new QueryFilterDto("id", "=", id));
        return find("full", dtoList);
    }

    public User find(String queryType, List<QueryFilterDto> queryFilterDtoList) {
        List<QueryFilterDto> filterList = mybatisUtils.convertQueryFilters(queryFilterDtoList);
        Map<String, Object> params = new HashMap<>();
        params.put("queryType", queryType);
        params.put("filterList", filterList);
        return userMapper.selectOne(params);
    }

    public Integer count(List<QueryFilterDto> queryFilterDtoList) {
        List<QueryFilterDto> filterList = mybatisUtils.convertQueryFilters(queryFilterDtoList);
        Map<String, Object> params = new HashMap<>();
        params.put("filterList", filterList);
        return userMapper.selectCount(params);
    }

    public List<User> list(String queryType, List<QueryFilterDto> queryFilterDtoList, PageRequestDto pageRequestDto) {
        List<QueryFilterDto> filterList = mybatisUtils.convertQueryFilters(queryFilterDtoList);
        String order = pageRequestDto.getOrder();
        String convertedOrder = mybatisUtils.convertPageOrder(order);
        Integer offset = pageRequestDto.getOffset();
        Integer size = pageRequestDto.getSize();
        Map<String, Object> params = new HashMap<>();
        params.put("queryType", queryType);
        params.put("filterList", filterList);
        params.put("order", convertedOrder);
        params.put("offset", offset);
        params.put("size", size);
        return userMapper.selectList(params);
    }

    @Transactional
    public User update(User user) {
        User updatedUser = null;
        Long userId = user.getId();
        User existingUser = findById(userId);
        if (existingUser != null) {
            user.setUpdateTime(dateTimeUtils.getCurrentDateTime());
            userMapper.update(user);
            updatedUser = findById(userId);
        }
        return updatedUser;
    }

    @Transactional
    public Boolean delete(List<QueryFilterDto> queryFilterDtoList) {
        if (count(queryFilterDtoList) == 0) {
            return false;
        }
        List<QueryFilterDto> filterList = mybatisUtils.convertQueryFilters(queryFilterDtoList);
        Map<String, Object> params = new HashMap<>();
        params.put("filterList", filterList);
        userMapper.delete(params);
        Boolean result = false;
        if (count(queryFilterDtoList) == 0) {
            result = true;
        }
        return result;
    }

    @Transactional
    public Boolean deleteById(Long id) {
        if (id == null) {
            return null;
        }
        List<QueryFilterDto> dtoList = new ArrayList<>();
        dtoList.add(new QueryFilterDto("id", "=", id));
        return delete(dtoList);
    }

}