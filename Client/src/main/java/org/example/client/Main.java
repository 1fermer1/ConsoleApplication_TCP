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
        final Gson gson = new GsonBuilder().registerTypeAdapter(ZonedDateTime.class, new ZonedDateTimeAdapter()).registerTypeAdapter(ArrayList.class, new IntegerArrayAdapter()).create();

        try (
                BufferedReader consoleReader =
                        new BufferedReader(
                                new InputStreamReader(
                                        System.in));
        ) {
            while (true) {
                try (
                        SocketChannel socketChannel = SocketChannel.open();
                ) {
                    // Пользователь вводит строку
                    System.out.print("> ");
                    String consoleInput = consoleReader.readLine();

                    // Обработка строки в команду с аргументами
                    Message message = new Message(null, consoleInput, null);

                    // Отправка команды на сервер
                    socketChannel.connect(new InetSocketAddress("127.0.0.1", 8000));
                    String jsonMessage = gson.toJson(message, Message.class);
                    ByteBuffer buffer = ByteBuffer.wrap(jsonMessage.getBytes());
                    socketChannel.write(buffer);

                    // Получение ответа от сервера
                    StringBuilder stringBuilder = new StringBuilder();
                    socketChannel.read(buffer);
                    buffer.flip();
                    byte[] bytes = new byte[buffer.remaining()];
                    buffer.get(bytes);
                    stringBuilder.append(new String(bytes));

                    // Обработка ответа сервера в читаемый объект
                    String jsonAnswer = stringBuilder.toString();
                    JsonObject jsonObject = JsonParser.parseString(jsonAnswer).getAsJsonObject();
                    message = gson.fromJson(jsonObject, Message.class);

                    // Выводим ответ ответ от сервера в консоль
                    System.out.println(message.getAnswer());
                }
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
