package com.willian.cortes.simplegameenginev1;

import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;

/**
 * Created by Willian on 10/03/2017.
 */

public class SGViewport {
    //Indica o modo de redimensionamento
    public enum ScalingMode {
        FULL_SCREEN,//Tela cheia
        FULL_SCREEN_KEEP_ORIGINAL_ASPECT,//Tela cheia mantendo o aspecto original
        INTEGER_RATIO,//Razao inteira
        ORIGINAL//Tamanho original
    }

    private Rect 		mDrawingArea = new Rect();//Area de desenho da viewpor dentro da tela
    private Point 		mOffsetFromOrigin = new Point();//Deslocamento horizontal e vertical
    private PointF 	    mScalingFactor = new PointF();//Fator de escala horizontal e vertical

    public SGViewport(Point sceneDimensions, Point screenDimensions, ScalingMode scalingMode) {

        //Fatores de escala
        if (scalingMode == ScalingMode.FULL_SCREEN) {
            mScalingFactor.set((float) screenDimensions.x / (float) sceneDimensions.x,
                    (float) screenDimensions.y / (float) sceneDimensions.y);
        }
        else if(scalingMode == ScalingMode.FULL_SCREEN_KEEP_ORIGINAL_ASPECT)
        {
            mScalingFactor.set((float)screenDimensions.x / (float)sceneDimensions.x,
                    (float)screenDimensions.y / (float)sceneDimensions.y);
            if(mScalingFactor.x > mScalingFactor.y)
            {
                mScalingFactor.x = mScalingFactor.y;
            }
            else
            {
                mScalingFactor.y = mScalingFactor.x;
            }
        }
        else if(scalingMode == ScalingMode.INTEGER_RATIO)
        {
            mScalingFactor.set((float)screenDimensions.x / (float)sceneDimensions.x,
                    (float)screenDimensions.y / (float)sceneDimensions.y);

            if(mScalingFactor.x >= 1 && mScalingFactor.y >= 1)
            {
                if(mScalingFactor.x > mScalingFactor.y)
                {
                    mScalingFactor.y = (int)(mScalingFactor.y);
                    mScalingFactor.x = mScalingFactor.y;
                }
                else
                {
                    mScalingFactor.x = (int)(mScalingFactor.x);
                    mScalingFactor.y = mScalingFactor.x;
                }
            }
        }
        else // ScalingMode.ORIGINAL
        {
            mScalingFactor.x = 1;
            mScalingFactor.y = 1;
        }


        //Valores de deslocamento horizontal e vertical da viewport em relacao a origem do sistema de coordenadas da tela
        mOffsetFromOrigin.x = (int) ((screenDimensions.x - (sceneDimensions.x * mScalingFactor.x)) / 2);
        mOffsetFromOrigin.y = (int) ((screenDimensions.y - (sceneDimensions.y * mScalingFactor.y)) / 2);

        //Descobre onde a viewport ficara na tela
        mDrawingArea.left = mOffsetFromOrigin.x;
        mDrawingArea.top = mOffsetFromOrigin.y;
        mDrawingArea.right = (int) ((sceneDimensions.x * mScalingFactor.x) + mOffsetFromOrigin.x);
        mDrawingArea.bottom = (int) ((sceneDimensions.y * mScalingFactor.y) + mOffsetFromOrigin.y);

    }

    public Rect	    getDrawingArea() { return mDrawingArea; }
    public Point 	getOffsetFromOrigin() { return mOffsetFromOrigin; }
    public PointF 	getScalingFactor() { return mScalingFactor; }
}
