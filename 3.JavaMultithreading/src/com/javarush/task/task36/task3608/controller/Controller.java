package com.javarush.task.task36.task3608.controller;

import com.javarush.task.task36.task3608.bean.User;
import com.javarush.task.task36.task3608.model.Model;
import com.javarush.task.task36.task3608.view.EditUserView;
import com.javarush.task.task36.task3608.view.UsersView;
import com.javarush.task.task36.task3608.view.View;

public class Controller {
    private Model model;
    private UsersView usersView;
    private EditUserView editUserView;

    public void setEditUserView(EditUserView editUserView) {
        this.editUserView = editUserView;
    }

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
    }

    public void onShowAllDeletedUsers()
    {
        this.model.loadDeletedUsers();
        this.usersView.refresh(this.model.getModelData());
    }

    public void showEditedUser()
    {
        this.model.loadUsers();
        this.editUserView.refresh(this.model.getModelData());
    }

    public void onOpenUserEditForm(long userId) {
        this.model.loadUserById(userId);
        this.editUserView.refresh(model.getModelData());
    }

    public void onUserDelete(long id) {
        this.model.deleteUserById(id);
        this.usersView.refresh(model.getModelData());
    }



}
