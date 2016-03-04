package de.canitzp.vismin.util;

import java.io.Serializable;

/**
 * @author canitzp
 */
public class Position implements Serializable{

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
