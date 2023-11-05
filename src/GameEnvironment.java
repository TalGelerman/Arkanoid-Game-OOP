// * Tal Gelerman 322280850

import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Game environment class.
 */
public class GameEnvironment {
    private List<Collidable> collidables = new ArrayList<Collidable>();


    /**
     * adds a given collidable to the environment.
     *
     * @param c the collidabe to add to the collidables list
     */
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }

    /**
     * removes a collidable.
     * @param c collidable
     */
    public void removeCollidable(Collidable c) {
        collidables.remove(c);
    }

    /**
     * going over all collides and check the closest intersecting point.
     *
     * @param trajectory the object moving route
     * @return the closest collision point and the collision object, null if it doesn't collide any collides.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        //setting first collidable as the min
        Point minPoint = null;
        Double minDis = null;
        Collidable minCol = null;
        int index = 0;
        for (int j = 0; j < collidables.size(); j++) {
            Point p = trajectory.closestIntersectionToStartOfLine(collidables.get(j).getCollisionRectangle());
            if (p != null) {
                minPoint = p;
                minDis = trajectory.start().distance(minPoint);
                minCol = collidables.get(j);
                break;
            }
            index++;
        }
        ///if there is a min point for sure
        if (minPoint != null) {
            for (int i = index; i < collidables.size(); i++) {
                //if there is an intersection point, if the ball gonna intersect with collidable object
                Point p = trajectory.closestIntersectionToStartOfLine(collidables.get(i).getCollisionRectangle());
                if (p != null) {
                    //if the starting point and intersecting point's distance is smaller than the current distance.
                    //p- the closest point between trajectory and the collidable object
                    if (p.distance(trajectory.start()) < minDis) {
                        minPoint = p;
                        minDis = p.distance(trajectory.start());
                        minCol = collidables.get(i);
                    }
                }
            }
            return new CollisionInfo(minPoint, minCol);
        }
        return null;
    }

    /**
     * draws the game objects.
     *
     * @param surface a draw surface
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.BLACK);
        for (int i = 0; i < collidables.size(); i++) {
            surface.fillRectangle((int) collidables.get(i).getCollisionRectangle().getUpperLeft().getX(),
                    (int) collidables.get(i).getCollisionRectangle().getUpperLeft().getY(),
                    (int) collidables.get(i).getCollisionRectangle().getWidth(),
                    (int) collidables.get(i).getCollisionRectangle().getHeight());
        }
    }

    /**
     * access to the collidables list.
     *
     * @return collidales list
     */
    public List<Collidable> getCollidables() {
        return this.collidables;
    }
}