package org.example.data;

public class Coordinates {
    private long x;
    private Integer y; // Значение поля должно быть больше -807, Поле не может быть null

    public Coordinates() {

    }
    public Coordinates(long x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public Long getX() {
        return x;
    }
    public void setX(long x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }
    public void setY(Integer y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        Coordinates coordinates = (Coordinates) obj;
        return coordinates.x == this.x && coordinates.y.equals(this.y);
    }
    @Override
    public int hashCode() {
        return y.hashCode() + (int) x;
    }
    @Override
    public String toString() {
        return "(" + x + "; " + y + ")";
    }
}
