package com.willian.cortes.simplegameenginev1;

import android.graphics.Point;
import android.graphics.RectF;

/**
 * Created by Willian on 20/02/2017.
 */

public class SGWorld {

    private Point mDimensions; //Define as dimencoes do mundo

    public SGWorld(Point worldDimensions)
    {
        mDimensions = new Point(worldDimensions);
    }

    public boolean collisionTest(RectF R1, RectF R2)
    {
        return R1.left < R2.right &&
                R1.right > R2.left &&
                R1.top < R2.bottom &&
                R1.bottom > R2.top;
    }

    public void step(float elapsedTimeInSeconds) { }

    public void setup() { }

    public Point getDimensions() { return mDimensions; }
}
