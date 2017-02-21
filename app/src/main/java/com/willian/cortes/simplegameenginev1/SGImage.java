package com.willian.cortes.simplegameenginev1;

import android.graphics.Bitmap;
import android.graphics.Point;

/**
 * Created by Willian on 14/02/2017.
 */

public class SGImage {
    private Bitmap mBitmap = null;
    private Point mDimensions = new Point();

    public SGImage(Bitmap bitmap)
    {
        mBitmap = bitmap;
        mDimensions.set(mBitmap.getWidth(), mBitmap.getHeight());
    }

    public Bitmap 	getBitmap() { return mBitmap; }
    public Point 	getDimensions() { return mDimensions; }
}
