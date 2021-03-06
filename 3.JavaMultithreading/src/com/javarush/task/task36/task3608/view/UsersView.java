package com.javarush.task.task36.task3608.view;

import com.javarush.task.task36.task3608.controller.Controller;
import com.javarush.task.task36.task3608.model.ModelData;

public class UsersView implements View {
    private Controller controller;

    @Override
    public void refresh(ModelData modelData) {

        if(modelData.isDisplayDeletedUserList())
            System.out.println("All deleted users:");
        else
            System.out.println("All users:");
        for (int i = 0; i < modelData.getUsers().size(); i++) {
            System.out.println("\t" + modelData.getUsers().get(i).toString());
        }
        System.out.println("===================================================");
    }

    public void fireEventShowAllUsers()
    {
        this.controller.onShowAllUsers();
    }

    public void fireEventShowDeletedUsers()
    {
        this.controller.onShowAllDeletedUsers();
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void fireEventOpenUserEditForm(long id) {this.controller.onOpenUserEditForm(id);}

}
