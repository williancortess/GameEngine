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
        //Retangulo preenchido
        FILLED,
        OUTLINE
        //Somente as bordas do retangulo
    };

//    protected RectF             mBBoxPadding = new RectF();
//    protected RectF             mBoundingBox = new RectF();
    private String				mCategory;//Define a categoria de entidade
    protected int 				mDebugColor = Color.RED;//Desenha a aarea ocupada de red se executado como depuracao
    private DebugDrawingStyle   mDebugDrawingStyle = DebugDrawingStyle.FILLED;//Estilo de desenho do retangulo
    protected PointF 			mDimensions = new PointF();//LArgura e altura da entidade
    private int 				mId;//Id dentro do jogo
    private boolean				mIsActive = true;//Define se a entidade esta ativa
    protected PointF 			mPosition = new PointF();//Posicao da entidade em relacao ao seu canto superior esquerdo
    private SGWorld 			mWorld;//Referencia ao mundo ao qual a entidade pertence

    public SGEntity(SGWorld world, int id, String category, PointF position, PointF dimensions)
    {
        mWorld = world;
        mId = id;
        mCategory = category;
        mPosition.set(position);
        mDimensions.set(dimensions);

    }

    //Movimenta a entidade de maneira relativa a sua posicao atual.
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

    //Movimenta a entidade de maneira absoluta - Transporta a entidade para a posicao dos parametros
    public void                 setPosition(float x, float y)
    {
        mPosition.set(x, y);
    }
    public void                 setPosition(PointF position)  { mPosition.set(position);
    }
}
