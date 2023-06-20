package org.example.commandManager.commands;

import org.example.data.Route;

public class ClearCommand implements ICommandable {
    public String getName() {
        return "clear";
    }

    public String getDescr() {
        return "очистить коллекцию";
    }
}
