package com.javarush.task.task25.task2515;

public class Ufo extends BaseObject{

    private static int[][] matrix = {
            {0, 0, 1, 0, 0},
            {0, 1, 0, 1, 0},
            {1, 0, 1, 0, 1},
            {0, 1, 0, 1, 0},
            {0, 0, 0, 0, 0},
    };

    public Ufo(double x, double y) {
        super(x, y, 3);
    }

    public void fire() {
        Space.game.getBombs().add(new Bomb(x, y+3));
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawMatrix(x - radius + 1, y - radius + 1, matrix, 'U');
    }

    @Override
    public void move() {

//        double dx=Math.round(Math.random() * 2 - 1);
//        double dy=Math.round(Math.random() * 2 - 1);

//        x = x + dx;
//        y = y + dy;
//
//        if (y <= Space.game.getHeight() / 2)
//            y--;
//
//        checkBorders(radius, Space.game.getWidth() - radius + 1, 1, Space.game.getHeight() + 1);

        double dx = Math.random() * 2 - 1; //-1..1
        double dy = Math.random() * 2 - 1; //-1..1
        x = x + dx;
        y = y + dy;
        checkBorders(radius, Space.game.getWidth() - radius, radius, Space.game.getHeight()/2);

        if(Math.random() >= 0.9)
            fire();
    }
}
