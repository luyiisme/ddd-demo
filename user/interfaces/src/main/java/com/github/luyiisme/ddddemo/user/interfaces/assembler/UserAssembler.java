package com.github.luyiisme.ddddemo.user.interfaces.assembler;

import com.github.luyiisme.ddddemo.user.domain.User;
import com.github.luyiisme.ddddemo.user.interfaces.facade.dto.UserDTO;
import org.springframework.cglib.beans.BeanCopier;

/**
 * @author: kevin.luy@antfin.com
 * @create: 2019-08-19 15:03
 **/
public final class UserAssembler {
    static final BeanCopier beanCopier = BeanCopier.create(User.class, UserDTO.class, false);


    public static UserDTO toDTO(User user) {
        if (user == null) {
            return null;
        }
        UserDTO dto = new UserDTO();
        beanCopier.copy(user, dto, null);
        return dto;
    }
}
