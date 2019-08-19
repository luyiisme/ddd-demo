package com.github.luyiisme.ddddemo.user.interfaces.assembler;

import com.github.luyiisme.ddddemo.user.domain.User;
import com.github.luyiisme.ddddemo.user.interfaces.facade.dto.UserDTO;

/**
 * @author: kevin.luy@antfin.com
 * @create: 2019-08-19 15:03
 **/
public final class UserAssembler {

    public static UserDTO toDTO(User user) {
        if (user == null) {
            return null;
        }
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        return dto;
    }
}
