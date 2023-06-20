package org.example.commandManager;

import org.example.collection.CollectionManager;
import org.example.commandManager.commands.IExecutable;
import org.example.data.Route;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class ServerExecuteManager {
    private LinkedHashMap<String, IExecutable> serverExecuteManager;

    public LinkedHashMap<String, IExecutable> getServerExecuteManager() {
        return serverExecuteManager;
    }

    public ServerExecuteManager() {
        serverExecuteManager = new LinkedHashMap<>();

        serverExecuteManager.put("add", this::addSettings);
        serverExecuteManager.put("execute_script", this::executionScriptSettings);
        serverExecuteManager.put("show", this::showSettings);
    }

    private CollectionManager collectionManager = new CollectionManager();

    private String addSettings(Object args, Route route) {
        collectionManager.addRoute(route);
        return "> ";
    }

    private String executionScriptSettings(Object args, Route route) {
        //TODO: execution script settings
        return "> ";
    }

    //TODO: StreamAPI

//    private String showSettings(Object args, Route route) {
//        String show = "";
//
//        int i = 1;
//        ArrayList<Route> routesArray = collectionManager.getRoutesCollection();
//        for (Route element : routesArray) {
//            show += i + "_" + element + "\n";
//            i++;
//        }
//
//        show += "> ";
//        return show;
//    }

    private String showSettings(Object args, Route route) {
        return "типа collections";
    }
}
