package com.github.luyiisme.ddddemo.user.interfaces;

import com.github.luyiisme.ddddemo.user.domain.User;
import com.github.luyiisme.ddddemo.user.domain.mapper.UserMapper;
import com.github.luyiisme.ddddemo.user.interfaces.assembler.UserAssembler;
import com.github.luyiisme.ddddemo.user.interfaces.facade.UserFacade;
import com.github.luyiisme.ddddemo.user.interfaces.facade.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: kevin.luy@antfin.com
 * @create: 2019-08-19 14:59
 **/
@Service
public class UserFacadeImpl implements UserFacade {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userMapper.getList();
        if (users == null) {
            return null;
        }
        return users.stream().map(UserAssembler::toDTO).collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserByName(String name) {
        return UserAssembler.toDTO(userMapper.getUserByName(name));
    }
}
