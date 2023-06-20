package org.example.commandManager.commands;

import org.example.data.Route;

public class HelpCommand implements ICommandable {
    public String getName() {
        return "help";
    }

    public String getDescr() {
        return "вывести справку по доступным командам";
    }
}
