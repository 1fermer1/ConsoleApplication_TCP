package org.example.data;

import java.time.ZonedDateTime;

public class Route implements Comparable<Route> {
    private int id; // Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; // Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; // Поле не может быть null
    private ZonedDateTime creationDate; // Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Location from; // Поле может быть null
    private Location to; // Поле может быть null
    private int distance; // Значение поля должно быть больше 1

    private Route() {

    }
    private Route(String name, Coordinates coordinates, Location from, Location to, int distance) {
        this.name = name;
        this.coordinates = coordinates;
        creationDate = ZonedDateTime.now();
        this.from = from;
        this.to = to;
        this.distance = distance;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(ZonedDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Location getFrom() {
        return from;
    }
    public void setFrom(Location from) {
        this.from = from;
    }

    public Location getTo() {
        return to;
    }
    public void setTo(Location to) {
        this.to = to;
    }

    public Integer getDistance() {
        return distance;
    }
    public void setDistance(int distance) {
        this.distance = distance;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || this.getClass() == obj.getClass()) {
            return false;
        }
        Route route = (Route) obj;
        return route.name.equals(this.name) && route.coordinates.equals(this.coordinates) && route.from.equals(this.from) && route.to.equals(this.to) && route.distance == this.distance;
    }
    @Override
    public int hashCode() {
        return id + name.hashCode() + coordinates.hashCode() + creationDate.hashCode() + from.hashCode() + to.hashCode() + distance;
    }
    @Override
    public String toString() {
        return "Route: " +
                "\n\tid" + id +
                "\n\tname: " + name +
                "\n\tcoordinates: " + coordinates +
                "\n\tcreationDate: " + creationDate +
                "\n\tfrom: " + from +
                "\n\tto: " + to +
                "\n\tdistance: " + distance;
    }

    @Override
    public int compareTo(Route route) {
        return this.coordinates.compareTo(route.coordinates);
    }
}
