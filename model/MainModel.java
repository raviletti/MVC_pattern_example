package com.javarush.task.task36.task3608.model;

import com.javarush.task.task36.task3608.bean.User;
import com.javarush.task.task36.task3608.model.service.UserService;
import com.javarush.task.task36.task3608.model.service.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class MainModel implements Model {
    private ModelData modelData = new ModelData();
    private UserService userService = new UserServiceImpl();

    @Override
    public ModelData getModelData() {
        return modelData;
    }

    @Override
    public void loadUsers() {
        modelData.setUsers(getAllUsers());
        modelData.setDisplayDeletedUserList(false);
    }
    public void loadDeletedUsers() {
        List<User> users = userService.getAllDeletedUsers();
        modelData.setUsers(users);
        modelData.setDisplayDeletedUserList(true);


    }
    public void loadUserById(long userId) {
        User user;
        user = userService.getUsersById(userId);
        for(User allusers : getAllUsers()){
            modelData.setActiveUser(allusers);
        }

    }

    @Override
    public void deleteUserById(long id) {
             userService.deleteUser(id);
        modelData.setUsers(getAllUsers());
    }
    public void changeUserData(String name, long id, int level){
        userService.createOrUpdateUser(name, id, level);
        modelData.setUsers(getAllUsers());
    }
    private List<User> getAllUsers(){
        List<User> users = new ArrayList<>(userService.getUsersBetweenLevels(1, 100));
        return userService.filterOnlyActiveUsers(users);
    }

}
//В классе MainModel в методе deleteUserById(long)
// ты не вызвал метод deleteUser(long) у объекта UserService.
