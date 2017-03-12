package com.willian.cortes.simplegameenginev1;

import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.RectF;

/**
 * Created by Willian on 12/03/2017.
 */

public class SGWidget {
    protected PointF		mAbsolutePosition = new PointF(); //Posicao em relacao a cena
    protected Alignment	    mAlignment = Alignment.Left; //Alinhamento horizontal
    protected RectF 		mArea = new RectF(); //Area de desenho
    protected PointF 		mDimensions = new PointF(); //Largura e altura
    protected boolean		mIsEnabled = true; //Ativo
    protected boolean		mIsVisible = true; //Visivel
    protected SGWidget	    mParent = null; //Armazena o widget pai
    protected PointF		mRelativePosition = new PointF(); //Posicao do widget relativa ao seu alinhamento
    protected Point		    mSceneDimensions = new Point(); //Dimencao da cena
    protected String 		mType; //Tipo de widget

    public enum Alignment
    {
        Center,
        Left,
        Right
    }

    public SGWidget(String type, Alignment alignment, PointF relativePosition, PointF dimensions)
    {
        mType = type;
        mAlignment = alignment;
        mRelativePosition.set(relativePosition.x, relativePosition.y);
        mDimensions.set(dimensions.x, dimensions.y);
    }

    public void setSceneDimensions(Point sceneDimensions)
    {
        mSceneDimensions = sceneDimensions;
        update();
    }

    //Transforma as coordenadas relativas em coordenadas absolutas
    public void update()
    {
        if(mAlignment == Alignment.Left)
        {
            mAbsolutePosition.x = mRelativePosition.x;
        }
        else if(mAlignment == Alignment.Center)
        {
            float halfOffset = (mSceneDimensions.x / 2) - (mDimensions.x / 2);
            mAbsolutePosition.x = halfOffset + mRelativePosition.x;
        }
        else // mAlignment == Alignment.Right
        {
            mAbsolutePosition.x = mSceneDimensions.x + mRelativePosition.x;
        }

        mAbsolutePosition.y = mRelativePosition.y;

        mArea.left = mAbsolutePosition.x;
        mArea.top = mAbsolutePosition.y;
        mArea.right = mArea.left + mDimensions.x;
        mArea.bottom = mArea.top + mDimensions.y;
    }

    public boolean injectDown(PointF position)
    {
        return onDown(position);
    }

    public boolean injectUp(PointF position)
    {
        return onUp(position);
    }

    public boolean onDown(PointF position)
    {
        return false;
    }

    public boolean onUp(PointF position)
    {
        return false;
    }

    public void render(SGRenderer renderer)
    {
    }

    public PointF 		getAbsolutePosition() { return mAbsolutePosition; }
    public Alignment	getAlignment() { return mAlignment; }
    public RectF 		getArea() { return mArea; }
    public PointF 		getDimensions() { return mDimensions; }
    public SGWidget	    getParent() { return mParent; }
    public PointF 		getRelativePosition() { return mAbsolutePosition; }
    public String 		getType() { return mType; }
    public boolean		isEnabled() { return mIsEnabled; }
    public boolean		isVisible() { return mIsVisible; }

    public void	        setAlignment(Alignment alignment)
    {
        mAlignment = alignment;
        update();
    }

    public void 	    setArea(float left, float top, float right, float bottom)
    {
        mArea.set(left, top, right, bottom);
    }

    public void	        setIsEnabled(boolean isEnabled) { mIsEnabled = isEnabled; }
    public void	        setIsVisible(boolean isVisible) { mIsVisible = isVisible; }
    public void 	    setParent(SGWidget parent) { mParent = parent; }
}
