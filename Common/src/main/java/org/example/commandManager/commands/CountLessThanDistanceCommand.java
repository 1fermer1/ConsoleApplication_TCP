package org.example.commandManager.commands;

import org.example.data.Route;

public class CountLessThanDistanceCommand implements ICommandable {
    private int args;

    public String getName() {
        return "count_less_than_distance";
    }

    public String getDescr() {
        return "вывести количество элементов, значение поля distance которых меньше заданного";
    }

    public String getDescrArgs() {
        return "distance";
    }

    public Integer getArgs() {
        return args;
    }
    public boolean setArgs(Integer value) {
        args = value;
        return true;
    }
}
