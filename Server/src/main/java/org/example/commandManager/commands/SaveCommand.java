package org.example.commandManager.commands;

public class SaveCommand implements ICommandable {
    public String getName() {
        return "save";
    }

    public String getDescr() {
        return "сохранить коллекцию в файл";
    }
}
