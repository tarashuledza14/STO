package org.sto.mapper;

import org.sto.dto.UserChatDTO;
import org.sto.dto.UserDTO;
import org.sto.entity.User;

public class UserMapper {
    public static User fromUserDTOToEntity(final UserDTO userDTO) {
        return User.builder().firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .phoneNumber(userDTO.getPhoneNumber())
                .build();
    }
}
