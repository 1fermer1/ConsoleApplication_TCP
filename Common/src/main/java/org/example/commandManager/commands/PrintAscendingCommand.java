package org.example.commandManager.commands;

import org.example.data.Route;

public class PrintAscendingCommand implements ICommandable {
    public String getName() {
        return "print_ascending";
    }

    public String getDescr() {
        return "вывести элементы в порядке возрастания";
    }
}
