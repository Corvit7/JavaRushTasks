package com.javarush.task.task36.task3608.view;

import com.javarush.task.task36.task3608.controller.Controller;
import com.javarush.task.task36.task3608.model.ModelData;

public class UsersView implements View {
    @Override
    public void refresh(ModelData modelData) {
        System.out.println("All users:");
        for (int i = 0; i < modelData.getUsers().size(); i++) {
            System.out.println("\t" + modelData.getUsers().get(i).toString());
        }
        System.out.println("===================================================\n");
    }

    public void fireEventShowAllUsers()
    {
        this.controller.onShowAllUsers();
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    private Controller controller;

}
