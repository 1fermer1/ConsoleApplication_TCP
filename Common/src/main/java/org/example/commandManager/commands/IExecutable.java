package org.example.commandManager.commands;

import org.example.data.Route;

public interface IExecutable {
    String execute(Object args, Route route);
}
