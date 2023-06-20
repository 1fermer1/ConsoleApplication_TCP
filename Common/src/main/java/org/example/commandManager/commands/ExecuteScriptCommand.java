package org.example.commandManager.commands;

import org.example.data.Route;

public class ExecuteScriptCommand implements ICommandable {
    private String args;

    public String getName() {
        return "execute_script";
    }

    public String getDescr() {
        return "считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же ыиде, в котором их вводит пользователь в интерактивном режиме";
    }

    public String getDescrArgs() {
        return "file_name";
    }

    public String getArgs() {
        return args;
    }
    public boolean setArgs(String value) {
        args = value;
        return true;
    }
}
