package org.example.commandManager.commands;

import org.example.data.Route;

public class PrintDescendingCommand implements ICommandable {
    public String getName() {
        return "print_descending";
    }

    public String getDescr() {
        return "вывести элементы в порядке убывания";
    }
}
