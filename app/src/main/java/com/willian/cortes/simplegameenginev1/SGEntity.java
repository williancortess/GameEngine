package com.willian.cortes.simplegameenginev1;

import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.RectF;

/**
 * Created by Willian on 20/02/2017.
 */

public class SGEntity {

    public enum DebugDrawingStyle
    {
        FILLED,
        OUTLINE
    };

    protected RectF             mBBoxPadding = new RectF();
    protected RectF             mBoundingBox = new RectF();
    private String				mCategory;
    protected int 				mDebugColor = Color.RED;
    private DebugDrawingStyle   mDebugDrawingStyle = DebugDrawingStyle.FILLED;
    protected PointF 			mDimensions = new PointF();
    private int 				mId;
    private boolean				mIsActive = true;
    protected PointF 			mPosition = new PointF();
    private SGWorld 			mWorld;

    public SGEntity(SGWorld world, int id, String category, PointF position, PointF dimensions)
    {
        mWorld = world;
        mId = id;
        mCategory = category;
        mPosition.set(position);
        mDimensions.set(dimensions);

    }

    public void move(float offsetX, float offsetY) {
        mPosition.x += offsetX;
        mPosition.y += offsetY;
    }

    public void step(float elapsedTimeInSeconds) {}

    public String	            getCategory() { return mCategory; }
    public int 	                getDebugColor() { return mDebugColor; }
    public DebugDrawingStyle    getDebugDrawingStyle() { return mDebugDrawingStyle; }
    public PointF               getDimensions() { return mDimensions; }
    public int		            getId() { return mId; }
    public PointF 	            getPosition() { return mPosition; }
    public SGWorld	            getWorld() { return mWorld; }

    public boolean	            isActive() { return mIsActive; }

    public void                 setDebugColor(int color) { mDebugColor = color; }
    public void                 setDebugDrawingStyle(DebugDrawingStyle drawingStyle) { mDebugDrawingStyle = drawingStyle; }
    public void                 setDimensions(float x, float y) { mDimensions.set(x, y); }
    public void                 setDimensions(PointF dimensions) { mDimensions.set(dimensions); }
    public void                 setIsActive(boolean isActive) { mIsActive = isActive; }

    public void                 setPosition(float x, float y)
    {
        mPosition.set(x, y);
    }

    public void                 setPosition(PointF position)
    {
        mPosition.set(position);
    }
}
