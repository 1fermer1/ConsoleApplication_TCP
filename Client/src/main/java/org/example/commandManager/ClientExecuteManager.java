package org.example.commandManager;

import org.example.commandManager.commands.AddCommand;
import org.example.commandManager.commands.IExecutable;
import org.example.commandManager.commands.ShowCommand;
import org.example.data.Route;
import org.example.messages.Message;

import java.util.LinkedHashMap;

public class ClientExecuteManager {
    private LinkedHashMap<String, IExecutable> clientExecuteManager;

    public LinkedHashMap<String, IExecutable> getClientExecuteManager() {
        return clientExecuteManager;
    }

    public ClientExecuteManager() {
        clientExecuteManager = new LinkedHashMap<>();

        clientExecuteManager.put("add", this::addSettings);
        clientExecuteManager.put("show", this::showSettings);
    }

    private Message addSettings(Object args, Route route) {
        AddCommand addCommand = new AddCommand();
        //TODO: make route and push in add
        return new Message(addCommand, null, null);
    }

    private Message showSettings(Object args, Route route) {
        return new Message(new ShowCommand(), null, null);
    }
}
