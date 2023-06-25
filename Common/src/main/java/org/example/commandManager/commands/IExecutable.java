package org.example.commandManager.commands;

import org.example.data.Route;
import org.example.messages.Message;

public interface IExecutable {
    Message execute(Object args, Route route);
}
