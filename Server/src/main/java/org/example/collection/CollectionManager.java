package org.example.collection;

import org.example.data.Route;
import org.example.validators.IdValidator;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Random;

public class CollectionManager {
    private static ZonedDateTime collectionInitializationDate = ZonedDateTime.now();
    private static ArrayList<Route> routesCollection = new ArrayList<>();

    public void addRoute(Route route) {
        routesCollection.add(route);
    }

    public void setRoute(int index, Route route) {
        routesCollection.add(index, route);
    }

    public void changeRoute(int index, Route route) {
        routesCollection.set(index, route);
    }

    public ArrayList<Route> getRoutesCollection() {
        return routesCollection;
    }

    public void setRoutesCollection(ArrayList<Route> routesCollection) {
        this.routesCollection = routesCollection;
    }

    public ArrayList<Integer> getIdsCollection() {
        ArrayList<Integer> idsCollection = new ArrayList<>();

        for (Route route : routesCollection) {
            idsCollection.add(route.getId());
        }

        return idsCollection;
    }

    public Integer generateId() {
        Random random = new Random();
        int newID = Math.abs(random.nextInt());
        ArrayList<Integer> arrayIds = getIdsCollection();
        while (arrayIds.contains(newID)) {
            newID = Math.abs(random.nextInt());
        }
        return newID;
    }

    public ZonedDateTime getCollectionInitializationDate() {
        return collectionInitializationDate;
    }
}
