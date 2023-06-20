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
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        //TODO: load collection from json

        final Gson gson = new GsonBuilder().registerTypeAdapter(ZonedDateTime.class, new ZonedDateTimeAdapter()).registerTypeAdapter(ArrayList.class, new IntegerArrayAdapter()).create();

        //TODO: чат, конечно, молодец но консоль читается только после клиента, а это дичь а не асинхронный ввод
        try (
                Selector selector = Selector.open();
                ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        ) {
            serverSocketChannel.bind(new InetSocketAddress(8000));
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            Pipe pipe = Pipe.open();
            Pipe.SinkChannel consoleSinkChannel = pipe.sink(); //TODO: close
            Pipe.SourceChannel consoleSourceChannel = pipe.source(); //TODO: close
            consoleSourceChannel.configureBlocking(false);
            SelectionKey consoleKey = consoleSourceChannel.register(selector, SelectionKey.OP_READ);

            Scanner scanner = new Scanner(System.in);

            while (true) {
//                try (SocketChannel socketChannel = serverSocketChannel.accept()) {
//                    // Читаем запрос клиента
//                    ByteBuffer buffer = ByteBuffer.allocate(1024);
//                    StringBuilder stringBuilder = new StringBuilder();
//                    socketChannel.read(buffer);
//                    buffer.flip();
//                    byte[] bytes = new byte[buffer.remaining()];
//                    buffer.get(bytes);
//                    stringBuilder.append(new String(bytes));
//
//                    // Переводим запрос в читаемый вид
//                    String request = stringBuilder.toString();
//                    JsonObject jsonObject = JsonParser.parseString(request).getAsJsonObject();
//                    Message message = gson.fromJson(jsonObject, Message.class);
//
//                    // Выполняем запрос
//                    message = new Message(null, ("length of your request: " + message.getAnswer().length()), null);
//
//                    // Пишем ответ клиенту
//                    String jsonMessage = gson.toJson(message, Message.class);
//                    buffer = ByteBuffer.wrap(jsonMessage.getBytes());
//                    socketChannel.write(buffer);
//                }
                int readyChannels = selector.select();

                if (readyChannels == 0) {
                    continue;
                }

                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> keyIterator = selectedKeys.iterator();

                while (keyIterator.hasNext()) {
                    SelectionKey key = keyIterator.next();

                    if (key.isAcceptable()) {
                        ServerSocketChannel server = (ServerSocketChannel) key.channel();
                        SocketChannel clientChannel = server.accept();
                        clientChannel.configureBlocking(false);
                        clientChannel.register(selector, SelectionKey.OP_READ);
                    } else if (key == consoleKey && key.isReadable()) {
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        int bytesRead = consoleSourceChannel.read(buffer);
                        if (bytesRead == -1) {
                            consoleSourceChannel.close();
                            consoleSinkChannel.close();
                            scanner.close();
                            return;
                        } else if (bytesRead > 0) {
                            buffer.flip();
                            byte[] data = new byte[buffer.limit()];
                            buffer.get(data);
                            String message = new String(data);
                            System.out.println("Console input: " + message);
                        }
                    } else if (key.isReadable()) {
                        SocketChannel clientChannel = (SocketChannel) key.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        int bytesRead = clientChannel.read(buffer);
                        if (bytesRead == -1) {
                            clientChannel.close();
                        } else if (bytesRead > 0) {
                            buffer.flip();
                            byte[] data = new byte[buffer.limit()];
                            buffer.get(data);
                            String message = new String(data);
                            System.out.println("Received from client: " + message);
                        }
                    }

                    keyIterator.remove();
                }

                if (System.in.available() > 0) {
                    String input = scanner.nextLine();
                    byte[] inputData = input.getBytes();
                    ByteBuffer buffer = ByteBuffer.wrap(inputData);
                    consoleSinkChannel.write(buffer);
                }
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}

