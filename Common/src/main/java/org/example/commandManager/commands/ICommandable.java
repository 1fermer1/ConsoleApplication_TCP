package org.example.commandManager.commands;

import org.example.data.Route;

public interface ICommandable<T> {
    String getName();

    String getDescr();

    default String getDescrArgs() {
        return "";
    }

    default T getArgs() {
        return null;
    }
    default boolean setArgs(T value) {
        System.out.println("Команда не принимает аргументы");
        return false;
    }

    default Route getRoute() {
        return null;
    }
    default void setRoute(Route route) {
        return;
    }

    default String execute(IExecutable executeSettings, Object args, Route route) {
        return executeSettings.execute(args, route);
    }
}
