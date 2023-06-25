package org.example.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.example.commandManager.ClientExecuteManager;
import org.example.commandManager.CommandManager;
import org.example.commandManager.commands.ICommandable;
import org.example.commandManager.commands.IExecutable;
import org.example.jsonLogic.IntegerArrayAdapter;
import org.example.jsonLogic.ZonedDateTimeAdapter;
import org.example.messages.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.time.ZonedDateTime;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

    }
}
