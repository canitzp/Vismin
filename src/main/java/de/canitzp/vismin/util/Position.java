package de.canitzp.vismin.util;

/**
 * @author canitzp
 */
public class Position {

    public double x, y;

    public Position(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Position add(double x, double y){
        return new Position(this.x + x, this.y + y);
    }

    @Override
    public String toString(){
        return "x:" + getX() + " y:" + getY();
    }
}
