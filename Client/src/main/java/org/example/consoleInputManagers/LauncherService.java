package org.example.consoleInputManagers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.example.commandManager.CommandManager;
import org.example.commandManager.commands.ICommandable;
import org.example.jsonLogic.IntegerArrayAdapter;
import org.example.jsonLogic.ZonedDateTimeAdapter;
import org.example.messages.Message;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class LauncherService {
    final static Gson gson = new GsonBuilder().registerTypeAdapter(ZonedDateTime.class, new ZonedDateTimeAdapter()).registerTypeAdapter(ArrayList.class, new IntegerArrayAdapter()).create();
    private static LinkedHashMap<String, ICommandable> commands;

    public static void init() {
        commands = new CommandManager().getCommands();
        System.out.println("Hello! And welcome to the Los Pollos Hermanos family! My name is Gustavo, but you can call me Gus :)");
        launcher();
    }

    public static void launcher() {
        while (true) {
            try {
                UserInputService.setBufferedReader(new BufferedReader(new InputStreamReader(System.in)));
                String commandName = UserInputService.getBufferedReader().readLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public static void fileCommandExecute() {
        //TODO: сделать чтобы скрипт мог вызывать другие скрипты
        // (крч изи тупа нужно перед тем как выполнять команды из экзекьюта
        // сначала спарсить их в список команд, ток нужно сделать отлов стекОверФлов)
    }

    public static void defaultCommandExecute() {
        //TODO: настроить message и execute
    }

    // файлообменник
    // data sharing
    public static Message mega(Message message) {
        try (
                SocketChannel socketChannel = SocketChannel.open();
        ) {
            while (true) {
                try {
                    socketChannel.connect(new InetSocketAddress("127.0.0.1", 8000));
                    break;
                } catch (IOException ex) {
                }
            }

            // Отправка количества байт сообщения, а после и команды на сервер
            String jsonMessage = gson.toJson(message, Message.class);
            int cap = jsonMessage.length();
            socketChannel.write(ByteBuffer.wrap(Integer.toString(cap).getBytes()));
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            for (int i = 0; i < cap; i += 1024) {
                buffer = ByteBuffer.wrap(jsonMessage.substring(0, Math.min(1024, cap - i)).getBytes());
                jsonMessage = jsonMessage.substring(Math.min(1024, cap - i));
                socketChannel.write(buffer);
                buffer.clear();
                buffer.flip();
            }

            // Сколько байтов отправил сервер
            buffer = ByteBuffer.allocate(1024);
            socketChannel.read(buffer);
            buffer.flip();
            byte[] bytes = new byte[buffer.remaining()];
            buffer.get(bytes);
            cap = Integer.parseInt(new String(bytes));
            buffer.clear();

            // Получение ответа от сервера
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < cap; i += 1024) {
                socketChannel.read(buffer);
                buffer.flip();
                bytes = new byte[buffer.remaining()];
                buffer.get(bytes);
                stringBuilder.append(new String(bytes));
                buffer.clear();
            }

            // Обработка ответа сервера в читаемый объект
            String jsonAnswer = stringBuilder.toString();
            JsonObject jsonObject = JsonParser.parseString(jsonAnswer).getAsJsonObject();
            message = gson.fromJson(jsonObject, Message.class);

            // Выводим ответ ответ от сервера в консоль
            System.out.println(message.getAnswer().length());
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        return message;
    }
}
