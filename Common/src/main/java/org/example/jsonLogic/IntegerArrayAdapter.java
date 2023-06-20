package org.example.jsonLogic;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.ArrayList;

public class IntegerArrayAdapter extends TypeAdapter<ArrayList<Integer>> {
    @Override
    public void write(JsonWriter out, ArrayList<Integer> list) throws IOException {
        out.beginArray();
        for(Integer value : list) {
            out.value(value);
        }
        out.endArray();
    }

    @Override
    public ArrayList<Integer> read(JsonReader in) throws IOException {
        ArrayList<Integer> list = new ArrayList<>();

        in.beginArray();
        while (in.hasNext()) {
            JsonToken token = in.peek();
            if (token == JsonToken.NUMBER) {
                int value = in.nextInt();
                list.add(value);
            } else {
                in.skipValue();
            }
        }
        in.endArray();

        return list;
    }
}
