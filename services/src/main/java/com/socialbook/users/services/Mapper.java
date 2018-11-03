package com.socialbook.users.services;

import com.socialbook.users.entities.User;

import java.util.ArrayList;
import java.util.List;

public class Mapper {

    public static UserDto convertToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setUserId(user.getId());
        userDto.setGender(user.getGender());
        userDto.setPassword(user.getPassword());
        userDto.setUsername(user.getUsername());
        userDto.setFriends(convertToUserDtosFriends(user.getFriends()));
        return userDto;
    }

    private static UserDto convertToDtoFriend(User user) {
        UserDto userDto = new UserDto();
        userDto.setUserId(user.getId());
        userDto.setGender(user.getGender());
        userDto.setPassword(user.getPassword());
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
        user.setGender(userDto.getGender());
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
        user.setGender(userDto.getGender());
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
