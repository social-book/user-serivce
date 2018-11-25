package com.socialbook.users.services;

import com.socialbook.users.entities.User;

import java.util.ArrayList;
import java.util.List;

public class Mapper {

    public static UserDto convertToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setUserId(user.getId());
        userDto.setName(user.getName());
        userDto.setSurname(user.getSurname());
        userDto.setImgref(user.getProfileImg());
        userDto.setPassword(user.getPassword());
        userDto.setUsername(user.getUsername());
        userDto.setFriends(convertToUserDtosFriends(user.getFriends()));
        return userDto;
    }

    private static UserDto convertToDtoFriend(User user) {
        UserDto userDto = new UserDto();
        userDto.setUserId(user.getId());
        userDto.setImgref(user.getProfileImg());
        userDto.setName(user.getName());
        userDto.setSurname(user.getSurname());
//        userDto.setPassword(user.getPassword());
        userDto.setUsername(user.getUsername());
        return userDto;
    }

    private static List<UserDto> convertToUserDtosFriends(List<User> friends) {
        ArrayList<UserDto> userDtos = new ArrayList<>();
        for (User user : friends) {
            userDtos.add(convertToDtoFriend(user));
        }
        return userDtos;
    }

    public static List<UserDto> convertToUserDtos(List<User> users) {
        ArrayList<UserDto> userDtos = new ArrayList<>();
        for (User user : users) {
            userDtos.add(convertToDto(user));
        }
        return userDtos;
    }

    public static User convertToDao(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getUserId());
        user.setUsername(userDto.getUsername());
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setProfileImg(userDto.getImgref());
        user.setPassword(userDto.getPassword());
        user.setUsername(userDto.getUsername());
        user.setFriends(convertToUserDaoFriends(userDto.getFriends()));
        return user;
    }

    private static User convertToDaoFriend(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getUserId());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setProfileImg(userDto.getImgref());
        user.setSurname(userDto.getSurname());
        user.setName(userDto.getName());
        return user;
    }

    private static List<User> convertToUserDaoFriends(List<UserDto> userDtos) {
        ArrayList<User> users = new ArrayList<>();
        for (UserDto userDto : userDtos) {
            users.add(convertToDaoFriend(userDto));
        }
        return users;
    }

    public static List<User> convertoToUserDaos(List<UserDto> userDtos) {
        ArrayList<User> users = new ArrayList<>();
        for (UserDto userDto : userDtos) {
            users.add(convertToDao(userDto));
        }
        return users;
    }
}
