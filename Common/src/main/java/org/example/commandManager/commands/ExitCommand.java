package org.example.commandManager.commands;

import org.example.data.Route;

public class ExitCommand implements ICommandable {
    public String getName() {
        return "exit";
    }

    public String getDescr() {
        return "завершить программу";
    }
}
