package org.example.commandManager.commands;

import org.example.data.Route;

public class HistoryCommand implements ICommandable {
    public String getName() {
        return "history";
    }

    public String getDescr() {
        return "вывести последние 5 команд (без их аргументов)";
    }
}
