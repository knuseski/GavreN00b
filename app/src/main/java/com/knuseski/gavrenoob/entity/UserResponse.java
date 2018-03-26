package com.knuseski.gavrenoob.entity;

import com.google.gson.annotations.SerializedName;


public class UserResponse {

    @SerializedName("args")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public static class User {

        @SerializedName("name")
        private String name;

        @SerializedName("nickname")
        private String nickName;

        @SerializedName("img")
        private String imgUrl;

        public User() {
        }

        public User(String name, String nickName, String imgUrl) {
            this.name = name;
            this.nickName = nickName;
            this.imgUrl = imgUrl;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", nickName='" + nickName + '\'' +
                    '}';
        }
    }
}
