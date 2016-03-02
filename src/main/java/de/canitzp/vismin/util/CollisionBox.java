package de.canitzp.vismin.util;

/**
 * @author canitzp
 */
public class CollisionBox {

    public Position from;
    public int width, height;

    public CollisionBox(Position from, int width, int height) {
        this.from = from;
        this.width = width;
        this.height = height;
    }

    public Position getFrom() {
        return from;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public CollisionBox add(double x, double y){
        return new CollisionBox(this.from.add(x, y), width, height);
    }

    public boolean collide(CollisionBox box) {
        int tw = this.width;
        int th = this.height;
        int rw = box.getWidth();
        int rh = box.getHeight();
        if (!(rw <= 0 || rh <= 0 || tw <= 0 || th <= 0)) {
            int tx = (int) this.getFrom().getX();
            int ty = (int) this.getFrom().getY();
            int rx = (int) box.getFrom().getX();
            int ry = (int) box.getFrom().getY();
            rw += rx;
            rh += ry;
            tw += tx;
            th += ty;
            return ((rw <= rx || rw >= tx) &&
                    (rh <= ry || rh >= ty) &&
                    (tw <= tx || tw >= rx) &&
                    (th <= ty || th >= ry));

        }
        return false;
    }

}
