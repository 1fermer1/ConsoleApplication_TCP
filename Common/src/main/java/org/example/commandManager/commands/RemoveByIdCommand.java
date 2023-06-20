package org.example.commandManager.commands;

import org.example.data.Route;

public class RemoveByIdCommand implements ICommandable {
    private int args;

    public String getName() {
        return "remove_by_id";
    }

    public String getDescr() {
        return "удалить элемент из коллекции по его id";
    }

    public String getDescrArgs() {
        return "id";
    }

    public Integer getArgs() {
        return args;
    }
    public boolean setArgs(Integer value) {
        args = value;
        return true;
    }
}
