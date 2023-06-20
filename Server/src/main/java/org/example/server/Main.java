package org.example.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.example.commandManager.ServerExecuteManager;
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
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //TODO: load collection from json

        final Gson gson = new GsonBuilder().registerTypeAdapter(ZonedDateTime.class, new ZonedDateTimeAdapter()).registerTypeAdapter(ArrayList.class, new IntegerArrayAdapter()).create();

        //TODO: чат, конечно, молодец но консоль читается только после клиента, а это дичь а не асинхронный ввод
        try (
                Selector selector = Selector.open();
                ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        ) {
            serverSocketChannel.bind(new InetSocketAddress("127.0.0.1", 8000));
            serverSocketChannel.configureBlocking(false);
            //serverSocketChannel.register(selector, SelectionKey.);

            while (true) {
                try (
                        SocketChannel socketChannel = serverSocketChannel.accept();
                ) {
                    // Сколько байтов отправил клиент
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    socketChannel.read(buffer);
                    buffer.flip();
                    byte[] bytes = new byte[buffer.remaining()];
                    buffer.get(bytes);
                    int cap = Integer.parseInt(new String(bytes));
                    buffer.clear();

                    // Читаем запрос клиента
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int i = 0; i < cap; i += 1024) {
                        socketChannel.read(buffer);
                        buffer.flip();
                        bytes = new byte[buffer.remaining()];
                        buffer.get(bytes);
                        stringBuilder.append(new String(bytes));
                        buffer.clear();
                    }

                    // Переводим запрос в читаемый вид
                    String request = stringBuilder.toString();
                    JsonObject jsonObject = JsonParser.parseString(request).getAsJsonObject();
                    Message message = gson.fromJson(jsonObject, Message.class);

                    // Выполняем запрос
                    message = new Message(null, ("length of your request: " + message.getAnswer().length()), null);

                    // Пишем ответ клиенту
                    String jsonMessage = gson.toJson(message, Message.class);
                    buffer = ByteBuffer.wrap(jsonMessage.getBytes());
                    cap = buffer.capacity();
                    socketChannel.write(ByteBuffer.wrap(Integer.toString(cap).getBytes()));
                    socketChannel.write(buffer);
                }
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}

