package org.example.data;

public class Location {
    private Double x; // Поле не может быть null
    private Double y; // Поле не может быть null
    private String name; // Строка не может быть пустой, Поле может быть null

    public Location() {

    }
    public Location(Double x, Double y, String name) {
        this.x = x;
        this.y = y;
        this.name = name;
    }

    public Double getX() {
        return x;
    }
    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }
    public void setY(Double y) {
        this.y = y;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        Location location = (Location) obj;
        return location.x.equals(this.x) && location.y.equals(this.y) &&location.name.equals(this.name);
    }
    @Override
    public int hashCode() {
        return x.hashCode() + y.hashCode() + name.hashCode();
    }
    @Override
    public String toString() {
        return name + " (" + x + "; " + y + ")";
    }
}
