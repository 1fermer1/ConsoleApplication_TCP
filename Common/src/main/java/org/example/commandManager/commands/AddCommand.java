package org.example.commandManager.commands;

import org.example.data.Route;

public class AddCommand implements ICommandable {
    private Route route;

    public String getName() {
        return "add";
    }

    public String getDescr() {
        return "добавить новый жлемент в коллекцию";
    }

    public String getDescrArgs() {
        return "{element}";
    }

    public Route getRoute() {
        return route;
    }
    public void setRoute(Route route) {
        this.route = route;
    }
}
