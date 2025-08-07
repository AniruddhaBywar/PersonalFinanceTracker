package com.cdac.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.cdac.custom_exceptions.ResourceNotFoundException;
import com.cdac.dao.UserDao;
import com.cdac.dto.UserReqDTO;
import com.cdac.dto.UserRespDTO;
import com.cdac.entities.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Override
    public UserRespDTO createUser(UserReqDTO userReqDTO) {
        User user = new User();
        BeanUtils.copyProperties(userReqDTO, user);
        userDao.save(user);
        return convertToResp(user);
    }

    @Override
    public UserRespDTO getUserById(Long userId) {
        User user = userDao.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        return convertToResp(user);
    }

    @Override
    public List<UserRespDTO> getAllUsers() {
        return userDao.findAll()
                .stream()
                .map(this::convertToResp)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUser(Long userId) {
        User user = userDao.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        userDao.delete(user);
    }

    @Override
    public UserRespDTO updateUser(Long userId, UserReqDTO userReqDTO) {
        User user = userDao.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        BeanUtils.copyProperties(userReqDTO, user);
        userDao.save(user);
        return convertToResp(user);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userDao.existsByEmail(email);
    }

    @Override
    public UserRespDTO getUserByEmail(String email) {
        User user = userDao.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));
        return convertToResp(user);
    }

    private UserRespDTO convertToResp(User user) {
        UserRespDTO resp = new UserRespDTO();
        BeanUtils.copyProperties(user, resp);
        return resp;
    }
}
