package org.example.commandManager.commands;

import org.example.data.Route;

public class InsertAtCommand implements ICommandable {
    private Route route;
    private int args;

    public String getName() {
        return "insert_at";
    }

    public String getDescr() {
        return "добавить новый элемент в заданную позицию";
    }

    public String getDescrArgs() {
        return "index {element}";
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
