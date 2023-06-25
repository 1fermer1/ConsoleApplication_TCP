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
        serverExecuteManager.put("clear", this::clearSettings);
        serverExecuteManager.put("count_less_than_distance", this::countLessThanDistanceSettings);
        serverExecuteManager.put("execute_script", this::executeScriptSettings);
        serverExecuteManager.put("help", this::helpSettings);
        serverExecuteManager.put("history", this::historySettings);
        serverExecuteManager.put("info", this::infoSettings);
        serverExecuteManager.put("insert_at", this::insertAtSettings);
        serverExecuteManager.put("print_ascending", this::printAscendingSettings);
        serverExecuteManager.put("print_descending", this::printDescendingSettings);
        serverExecuteManager.put("remove_by_id", this::removeByIdSettings);
        serverExecuteManager.put("reorder", this::reorderSettings);
        serverExecuteManager.put("show", this::showSettings);
        serverExecuteManager.put("update", this::updateSettings);
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

    private Message clearSettings(Object args, Route route) {

        setLastCommand("clear");
        return new Message(null, null, null);
    }

    private Message countLessThanDistanceSettings(Object args, Route route) {

        setLastCommand("count_less_than_distance");
        return new Message(null, null, null);
    }

    private Message executeScriptSettings(Object args, Route route) {

        setLastCommand("execute_script");
        return new Message(null, null, null);
    }

    private Message helpSettings(Object args, Route route) {

        setLastCommand("help");
        return new Message(null, null, null);
    }

    private Message historySettings(Object args, Route route) {

        setLastCommand("history");
        return new Message(null, null, null);
    }

    private Message infoSettings(Object args, Route route) {

        setLastCommand("info");
        return new Message(null, null, null);
    }

    private Message insertAtSettings(Object args, Route route) {

        setLastCommand("insert_at");
        return new Message(null, null, null);
    }

    private Message printAscendingSettings(Object args, Route route) {

        setLastCommand("print_ascending");
        return new Message(null, null, null);
    }

    private Message printDescendingSettings(Object args, Route route) {

        setLastCommand("print_descending");
        return new Message(null, null, null);
    }

    private Message removeByIdSettings(Object args, Route route) {

        setLastCommand("remove_by_id");
        return new Message(null, null, null);
    }

    private Message reorderSettings(Object args, Route route) {

        setLastCommand("reorder");
        return new Message(null, null, null);
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

    private Message updateSettings(Object args, Route route) {

        setLastCommand("update");
        return new Message(null, null, null);
    }
}
