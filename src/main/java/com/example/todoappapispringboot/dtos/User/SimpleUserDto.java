package com.example.todoappapispringboot.dtos.User;

import com.example.todoappapispringboot.models.User;

public class SimpleUserDto {
        private String id;
        private String userName;


        public SimpleUserDto(User user){
            this.id = user.getId().toString();
            this.userName = user.getUsername();
        }
        public void setId(String id) {
            this.id = id;
        }
        public void setUserName(String userName) {
            this.userName = userName;
        }
        public String getId() {
            return id;
        }
        public String getUserName() {
            return userName;
        }
    }


