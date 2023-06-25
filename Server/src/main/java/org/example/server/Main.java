package org.example.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.example.collection.CollectionManager;
import org.example.jsonLogic.IntegerArrayAdapter;
import org.example.jsonLogic.Parser;
import org.example.jsonLogic.ZonedDateTimeAdapter;
import org.example.messages.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.time.ZonedDateTime;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        //Загрузка коллекции из json
        new Parser().readJsonFile("config.env");

        //TODO: настроить message и execute

        final Gson gson = new GsonBuilder().registerTypeAdapter(ZonedDateTime.class, new ZonedDateTimeAdapter()).registerTypeAdapter(ArrayList.class, new IntegerArrayAdapter()).create();

        try (
                ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
                BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in));
        ) {
            serverSocketChannel.bind(new InetSocketAddress("127.0.0.1", 8000));
            //serverSocketChannel.configureBlocking(false);

            while (true) {
                SocketChannel socketChannel = serverSocketChannel.accept();

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
                message = new Message(null, ("length of your request: " + message.getAnswer().length() + message.getAnswer()), null);

                // Пишем ответ клиенту
                String jsonMessage = gson.toJson(message, Message.class);
                cap = jsonMessage.length();
                socketChannel.write(ByteBuffer.wrap(Integer.toString(cap).getBytes()));
                buffer = ByteBuffer.allocate(1024);
                for (int i = 0; i < cap; i += 1024) {
                    buffer = ByteBuffer.wrap(jsonMessage.substring(0, Math.min(1024, cap - i)).getBytes());
                    jsonMessage = jsonMessage.substring(Math.min(1024, cap - i));
                    socketChannel.write(buffer);
                    buffer.clear();
                    buffer.flip();
                }

                if (consoleInput.ready()) {
                    if (consoleInput.readLine().trim().equals("save")) {
                        new Parser().writeJsonFile(new CollectionManager().getRoutesCollection(), "config.env");
                    } else {
                        System.out.println("Через консоль сервер принимает только команду \"save\" без аргументов и прочего");
                    }
                }
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}

