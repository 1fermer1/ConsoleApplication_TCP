package org.example.commandManager.commands;

import org.example.data.Route;

public class ReorderCommand implements ICommandable {
    public String getName() {
        return "reorder";
    }

    public String getDescr() {
        return "отсортировать коллекцию в обратном порядке";
    }
}
