package org.example.commandManager;

import org.example.collection.CollectionManager;
import org.example.commandManager.commands.IExecutable;
import org.example.data.Route;
import org.example.messages.Message;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.LinkedHashMap;

public class ServerExecuteManager {
    private LinkedHashMap<String, IExecutable> serverExecuteManager;
    private String[] historyList = new String[5];
//TODO: сделать все команды
    public LinkedHashMap<String, IExecutable> getServerExecuteManager() {
        return serverExecuteManager;
    }

    public ServerExecuteManager() {
        Arrays.fill(historyList, "");
        serverExecuteManager = new LinkedHashMap<>();

        serverExecuteManager.put("add", this::addSettings);
        serverExecuteManager.put("show", this::showSettings);
    }

    private void setLastCommand(String commandName) {
        for (int i = historyList.length - 1; i > 0; i--) {
            historyList[i] = historyList[i - 1];
        }
        historyList[0] = commandName;
    }

    private CollectionManager collectionManager = new CollectionManager();

    private Message addSettings(Object args, Route route) {
        collectionManager.addRoute(route);

        setLastCommand("add");
        return new Message(null, "> ", null);
    }

    private Message showSettings(Object args, Route route) {
        String showCollection = "";
        int i = 0;
        for (Route element : collectionManager.getRoutesCollection()) {
            showCollection += ++i + "_" + element + "\n";
        }
        showCollection += "\n> ";

        setLastCommand("show");
        return new Message(null, showCollection, null);
    }
}
