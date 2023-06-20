package org.example.messages;

import org.example.commandManager.commands.ICommandable;

import java.util.ArrayList;

public class Message {
    private ICommandable command;
    private String answer;
    private ArrayList<Integer> collectionIds;

    public Message(ICommandable command, String answer, ArrayList<Integer> collectionIds) {
        this.command = command;
        this.answer = answer;
        this.collectionIds = collectionIds;
    }

    public ICommandable getCommand() {
        return command;
    }

    public String getAnswer() {
        return answer;
    }

    public ArrayList<Integer> getCollectionIds() {
        return collectionIds;
    }
}
