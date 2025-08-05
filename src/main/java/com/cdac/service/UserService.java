package com.cdac.service;

import com.cdac.dto.UserReqDTO;
import com.cdac.dto.UserRespDTO;

import java.util.List;

public interface UserService {

    UserRespDTO createUser(UserReqDTO userReqDTO);

    UserRespDTO getUserById(Long userId);

    List<UserRespDTO> getAllUsers();

    void deleteUser(Long userId);

    UserRespDTO updateUser(Long userId, UserReqDTO userReqDTO);

    boolean existsByEmail(String email);

    UserRespDTO getUserByEmail(String email);
}
