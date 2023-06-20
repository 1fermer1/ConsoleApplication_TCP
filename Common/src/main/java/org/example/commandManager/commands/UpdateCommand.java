package org.example.commandManager.commands;

import org.example.data.Route;

public class UpdateCommand implements ICommandable {
    private Route route;
    private int args;

    public String getName() {
        return "update";
    }

    public String getDescr() {
        return "обновить значение элемента коллекции, id которого равен заданному";
    }

    public String getDescrArgs() {
        return "id {element}";
    }

    public Integer getArgs() {
        return args;
    }
    public boolean setArgs(Integer value) {
        args = value;
        return true;
    }

    public Route getRoute() {
        return route;
    }
    public void setRoute(Route route) {
        this.route = route;
    }
}
