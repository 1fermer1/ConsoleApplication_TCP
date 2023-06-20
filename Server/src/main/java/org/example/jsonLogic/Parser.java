package org.example.jsonLogic;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import org.example.data.Route;
import org.example.validators.*;

import java.io.*;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Properties;

public class Parser {
    private final Gson gson = new GsonBuilder().registerTypeAdapter(ZonedDateTime.class, new ZonedDateTimeAdapter()).create();

    public ArrayList<Route> readJsonFile(String configName) {
        ArrayList <Route> routesArray = new ArrayList<>();

        Properties properties = new Properties();
        try (InputStream inputStream = new FileInputStream(configName)) {
            properties.load(inputStream);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        String fileName = properties.getProperty("FILE_NAME");
        try (JsonReader reader = new JsonReader(new FileReader(fileName))) {
            reader.beginArray();
            while (reader.hasNext()) {
                JsonObject obj = JsonParser.parseReader(reader).getAsJsonObject();
                Route route = gson.fromJson(obj, Route.class);
                IdValidator idValidator = new IdValidator();
                //TODO: idValidator.setArrayIds();
                if (idValidator.validate(route.getId().toString()) &&
                    new NameValidator().validate(route.getName()) &&
                    new CoordinatesXValidator().validate(route.getCoordinates().getX().toString()) &&
                    new CoordinatesYValidator().validate(route.getCoordinates().getY().toString()) &&
                    new CreationDateValidator().validate(route.getCreationDate().toString()) &&
                    (route.getFrom() == null ||
                        new LocationXValidator().validate(route.getFrom().getX().toString()) &&
                        new LocationYValidator().validate(route.getFrom().getX().toString()) &&
                        !route.getFrom().getName().equals("")) &&
                    (route.getTo() == null ||
                        new LocationXValidator().validate(route.getTo().getX().toString()) &&
                        new LocationYValidator().validate(route.getTo().getX().toString()) &&
                        !route.getTo().getName().equals("") &&
                    new DistanceValidator().validate(route.getDistance().toString()))) {
                    //TODO: add in collection
                }
            }
            reader.endArray();
            System.out.println("Import successful");
        } catch (IOException ex) {
            System.out.println("Import error");
            throw new RuntimeException(ex);
        }

        return routesArray;
    }

    public void writeJsonFile(ArrayList<Route> routesArray, String configName) {
        Properties properties = new Properties();
        try (InputStream inputStream = new FileInputStream(configName)) {
            properties.load(inputStream);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        String fileName = properties.getProperty("FILE_NAME");
        try (PrintWriter out = new PrintWriter(new FileWriter(fileName))) {
            JsonWriter writer = gson.newJsonWriter(out);
            writer.setIndent("\t");
            writer.setSerializeNulls(true);
            writer.beginArray();
            for (Route route : routesArray) {
                gson.toJson(route, Route.class, writer);
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
