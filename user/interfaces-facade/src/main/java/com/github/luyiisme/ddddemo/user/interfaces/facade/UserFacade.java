package com.github.luyiisme.ddddemo.user.interfaces.facade;

import com.github.luyiisme.ddddemo.user.interfaces.facade.dto.UserDTO;

import java.util.List;

/**
 * @author: kevin.luy@antfin.com
 * @create: 2019-08-19 14:52
 **/
public interface UserFacade {
    List<UserDTO> getAllUsers();
    UserDTO getUserByName(String name);
}
