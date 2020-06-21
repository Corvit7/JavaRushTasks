package com.javarush.task.task36.task3608.model;

import com.javarush.task.task36.task3608.bean.User;
import com.javarush.task.task36.task3608.model.service.UserService;
import com.javarush.task.task36.task3608.model.service.UserServiceImpl;

import java.util.List;

public class MainModel implements Model{
    private UserService userService = new UserServiceImpl();
    private ModelData modelData = new ModelData();

    @Override
    public void loadUsers() {
        this.modelData.setDisplayDeletedUserList(false);
        this.modelData.setActiveUser((new User("Ivanov", 123l, 1)));
        this.modelData.setUsers(this.userService.getUsersBetweenLevels(1, 100));
    }

    @Override
    public ModelData getModelData() {
        return this.modelData;
    }

    public void loadDeletedUsers() {
        this.modelData.setDisplayDeletedUserList(true);
        List<User> users = userService.getAllDeletedUsers();
        this.modelData.setUsers(users);
    }
}
