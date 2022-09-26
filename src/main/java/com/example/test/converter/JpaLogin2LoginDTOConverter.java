package com.example.test.converter;

import com.example.test.dto.LoginDTO;
import com.example.test.dto.UserDTO;
import com.example.test.jpaentity.jpaLogin;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class JpaLogin2LoginDTOConverter {

    public static LoginDTO convert(jpaLogin jpalogin) {

        LoginDTO loginDTO = new LoginDTO();
        BeanUtils.copyProperties(jpalogin, loginDTO);
        return loginDTO;
    }

    public static List<LoginDTO> convert(List<jpaLogin> jpaloginList) {
        return jpaloginList.stream().map(e ->
                convert(e)
        ).collect(Collectors.toList());
    }
}
