package org.example.commandManager;

import org.example.collection.CollectionManager;
import org.example.commandManager.commands.IExecutable;
import org.example.data.Route;
import org.example.messages.Message;

import java.util.LinkedHashMap;

public class ServerClientExecuteManager {
    private LinkedHashMap<String, IExecutable> serverClientExecuteManager;

    public LinkedHashMap<String, IExecutable> getServerClientExecuteManager() {
        return serverClientExecuteManager;
    }

    public ServerClientExecuteManager() {
        serverClientExecuteManager = new LinkedHashMap<>();

        serverClientExecuteManager.put("add", this::addSettings);
        serverClientExecuteManager.put("show", this::showSettings);
    }

    private CollectionManager collectionManager = new CollectionManager();

    private Message addSettings(Object args, Route route) {
        collectionManager.addRoute(route);
        return new Message(null, "> ", null);
    }

    private Message showSettings(Object args, Route route) {
        String showCollection = null;
        //TODO: StreamAPI]
        showCollection += "\n> ";
        return new Message(null, showCollection, null);
    }
}
