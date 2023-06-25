package org.example.commandManager;

import org.example.commandManager.commands.*;
import org.example.data.Route;
import org.example.messages.Message;

import java.util.LinkedHashMap;

public class ClientExecuteManager {
    private LinkedHashMap<String, IExecutable> clientExecuteManager;
    //TODO: сделать все команды
    public LinkedHashMap<String, IExecutable> getClientExecuteManager() {
        return clientExecuteManager;
    }

    public ClientExecuteManager() {
        clientExecuteManager = new LinkedHashMap<>();

        clientExecuteManager.put("add", this::addSettings);
        clientExecuteManager.put("clear", this::clearSettings);
        clientExecuteManager.put("count_less_than_distance", this::countLessThanDistanceSettings);
        clientExecuteManager.put("execute_script", this::executeScriptSettings);
        clientExecuteManager.put("exit", this::exitSettings);
        clientExecuteManager.put("help", this::helpSettings);
        clientExecuteManager.put("history", this::historySettings);
        clientExecuteManager.put("info", this::infoSettings);
        clientExecuteManager.put("insert_at", this::insertAtSettings);
        clientExecuteManager.put("print_ascending", this::printAscendingSettings);
        clientExecuteManager.put("print_descending", this::printDescendingSettings);
        clientExecuteManager.put("remove_by_id", this::removeByIdSettings);
        clientExecuteManager.put("reorder", this::reorderSettings);
        clientExecuteManager.put("show", this::showSettings);
        clientExecuteManager.put("update", this::updateSettings);
    }

    private Message addSettings(Object args, Route route) {
        AddCommand addCommand = new AddCommand();



        return new Message(addCommand, null, null);
    }

    private Message clearSettings(Object args, Route route) {
        return new Message(new ClearCommand(), null, null);
    }

    private Message countLessThanDistanceSettings(Object args, Route route) {
        //TODO: execute
        return new Message(null, null, null);
    }

    private Message executeScriptSettings(Object args, Route route) {
        //TODO: execute
        return new Message(null, null, null);
    }

    private Message exitSettings(Object args, Route route) {
        System.exit(0);
        return new Message(new ExitCommand(), null, null);
    }

    private Message helpSettings(Object args, Route route) {
        return new Message(new HelpCommand(), null, null);
    }

    private Message historySettings(Object args, Route route) {
        return new Message(new HistoryCommand(), null, null);
    }

    private Message infoSettings(Object args, Route route) {
        return new Message(new InfoCommand(), null, null);
    }

    private Message insertAtSettings(Object args, Route route) {
        //TODO: execute
        return new Message(null, null, null);
    }

    private Message printAscendingSettings(Object args, Route route) {
        return new Message(new PrintAscendingCommand(), null, null);
    }

    private Message printDescendingSettings(Object args, Route route) {
        return new Message(new PrintDescendingCommand(), null, null);
    }

    private Message removeByIdSettings(Object args, Route route) {
        //TODO: execute
        return new Message(null, null, null);
    }

    private Message reorderSettings(Object args, Route route) {
        return new Message(new ReorderCommand(), null, null);
    }

    private Message showSettings(Object args, Route route) {
        return new Message(new ShowCommand(), null, null);
    }

    private Message updateSettings(Object args, Route route) {
        //TODO: execute
        return new Message(null, null, null);
    }
}
