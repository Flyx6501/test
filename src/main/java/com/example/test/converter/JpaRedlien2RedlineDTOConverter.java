package com.example.test.converter;

import com.example.test.dto.LoginDTO;
import com.example.test.dto.RedlineDTO;
import com.example.test.jpaentity.jpaLogin;
import com.example.test.jpaentity.jpaRedline;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class JpaRedlien2RedlineDTOConverter {

    public static RedlineDTO convert(jpaRedline jparedline) {

        RedlineDTO redlineDTO = new RedlineDTO();
        BeanUtils.copyProperties(jparedline, redlineDTO);
        return redlineDTO;
    }

    public static List<RedlineDTO> convert(List<jpaRedline> jparedlineList) {
        return jparedlineList.stream().map(e ->
                convert(e)
        ).collect(Collectors.toList());
    }
}
