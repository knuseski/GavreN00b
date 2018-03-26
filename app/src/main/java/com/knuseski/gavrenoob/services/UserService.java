package com.knuseski.gavrenoob.services;

import com.knuseski.gavrenoob.entity.UserResponse;

import java.util.ArrayList;
import java.util.List;

public class UserService {

    public static List<UserResponse.User> getUsers() {
        return getDataFromSomeServer();
    }

    private static List<UserResponse.User> getDataFromSomeServer() {
        List<UserResponse.User> result = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            result.add(new UserResponse.User("Gavre " + i, "N00b " + i,
                    "http://www.sundaynews.co.zw/wp-content/uploads/2017/09/laughing_donkey.jpg"));
        }
        return result;
    }
}
