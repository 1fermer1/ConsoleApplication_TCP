package org.example.commandManager.commands;

import org.example.data.Route;

public class ShowCommand implements ICommandable {
    public String getName() {
        return "show";
    }

    public String getDescr() {
        return "вывести в стандартный поток ввода все элементы коллекции в строковом представлении";
    }
}
