package org.example.commandManager;

import org.example.commandManager.commands.IExecutable;
import org.example.data.Route;

import java.util.LinkedHashMap;
import java.util.Random;

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

    private String addSettings(Object args, Route route) {
        return "> ";
    }

    private String showSettings(Object args, Route route) {
        return "server answer: ";
    }
}
