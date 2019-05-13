package com.sujiakeji.user.controller;

import com.sujiakeji.user.domain.User;
import com.sujiakeji.user.dto.common.PageRequestDto;
import com.sujiakeji.user.dto.common.PageResponseDto;
import com.sujiakeji.user.dto.common.QueryFilterDto;
import com.sujiakeji.user.service.UserService;
import com.sujiakeji.user.util.common.QueryUtils;
import com.sujiakeji.user.validator.UserValidator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    @Autowired
    private QueryUtils queryUtils;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private ObjectMapper lowerCamelObjectMapper;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(userValidator);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public User create(@RequestBody @Validated User user,
                       BindingResult bindingResult,
                       HttpServletResponse httpResponse) throws IOException {
        if (bindingResult.hasErrors()) {
            List<String> codes = new ArrayList<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                String code = error.getCode();
                logger.debug("code: {}'", code);
                codes.add(code);
            }
            httpResponse.setStatus(HttpStatus.BAD_REQUEST.value());
            String content = lowerCamelObjectMapper.writeValueAsString(codes);
            httpResponse.getWriter().append(content);
            httpResponse.getWriter().close();
            return null;
        }
        try {
            return userService.create(user);
        } catch (Exception e) {
            httpResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            httpResponse.getWriter().append(e.getMessage());
            httpResponse.getWriter().close();
            return null;
        }
    }

    @RequestMapping(value = "/getById", method = RequestMethod.GET)
    public User getById(@RequestParam(value = "id", required = false) Long id) {
        return userService.findById(id);
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public User get(@RequestParam(value = "filters", required = false) String filters,
                    HttpServletResponse response) {
        String queryType = "full";
        List<QueryFilterDto> queryFilterDtoList = null;
        try {
            queryFilterDtoList = queryUtils.convertToDtoList(filters);
        } catch (IOException e) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
        }
        return userService.find(queryType, queryFilterDtoList);
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public Long count(@RequestParam(value = "filters", required = false) String filters,
                      HttpServletResponse response) {
        List<QueryFilterDto> queryFilterDtoList = null;
        try {
            queryFilterDtoList = queryUtils.convertToDtoList(filters);
        } catch (IOException e) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
        }
        Long count = userService.count(queryFilterDtoList).longValue();
        return count;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public PageResponseDto<User> list(@RequestParam(value = "page", required = false) Integer page,
                                      @RequestParam(value = "size", required = false) Integer size,
                                      @RequestParam(value = "order", required = false) String order,
                                      @RequestParam(value = "filters", required = false) String filters,
                                      HttpServletResponse response) {
        String queryType = "full";
        List<QueryFilterDto> queryFilterDtoList = null;
        try {
            queryFilterDtoList = queryUtils.convertToDtoList(filters);
        } catch (IOException e) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
        }
        PageRequestDto pageRequestDto = new PageRequestDto(page, size, order);
        List<User> items = userService.list(queryType, queryFilterDtoList, pageRequestDto);
        Long count = userService.count(queryFilterDtoList).longValue();
        PageResponseDto<User> pageResponseDto = new PageResponseDto<User>(page, size, count, items);
        return pageResponseDto;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public User update(@RequestBody User user,
                       HttpServletResponse response) {
        User updatedUser = null;
        try {
            updatedUser = userService.update(user);
        } catch (Exception e) {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return updatedUser;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Boolean delete(@RequestParam(value = "filters", required = false) String filters,
                          HttpServletResponse response) {
        Boolean result = null;
        List<QueryFilterDto> queryFilterDtoList = null;
        try {
            queryFilterDtoList = queryUtils.convertToDtoList(filters);
        } catch (IOException e) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
        }

        try {
            result = userService.delete(queryFilterDtoList);
        } catch (Exception e) {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return result;
    }

}
