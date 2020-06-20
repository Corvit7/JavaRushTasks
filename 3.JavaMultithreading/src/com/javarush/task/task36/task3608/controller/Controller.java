package com.javarush.task.task36.task3608.controller;

import com.javarush.task.task36.task3608.bean.User;
import com.javarush.task.task36.task3608.model.Model;
import com.javarush.task.task36.task3608.view.UsersView;
import com.javarush.task.task36.task3608.view.View;

public class Controller {
    private Model model;
    private UsersView usersView;

    public void setModel(Model model) {
        this.model = model;
    }

    public void onShowAllUsers()
    {
        this.model.loadUsers();
        this.usersView.refresh(this.model.getModelData());
    }

    public void setUsersView(UsersView usersView) {
        this.usersView = usersView;
//        usersView.refresh(this.model.getModelData());
    }

}
