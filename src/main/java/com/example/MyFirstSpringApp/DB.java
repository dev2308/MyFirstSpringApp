package com.example.MyFirstSpringApp;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DB {

    private ArrayList<User> userTable = new ArrayList<>();

    public DB(){
        userTable.add(new User(1, "Ram", 25));
        userTable.add(new User(2, "Sham", 27));
    }

    public boolean addUser(User user){
        userTable.add(user);
        return true;
    }

    public ArrayList<User> getUsers(){
        return userTable;
    }

    public User searchUser(int id){
        for(User user: userTable)
            if(user.getId() == id) return user;

        System.out.println("Couldnt find the user");
        return null;
    }

    public boolean updateUser(User inputUser){

        for(User user: userTable){
            if(user.getId() == inputUser.getId()){
                user.setAge(inputUser.getAge());
                user.setName(inputUser.getName());
                return true;
            }
        }
        return false;
    }
}
