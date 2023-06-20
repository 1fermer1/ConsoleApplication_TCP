package org.example.commandManager.commands;

import org.example.data.Route;

public class InfoCommand implements ICommandable {
    public String getName() {
        return "info";
    }

    public String getDescr() {
        return "вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)";
    }
}
