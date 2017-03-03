package com.willian.cortes.simplegameenginev1;

import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.RectF;

/**
 * Created by Willian on 20/02/2017.
 *
 * Cria as entidades
 */

public class SGEntity {

    public enum DebugDrawingStyle
    {
        //Retangulo preenchido
        FILLED,
        OUTLINE
        //Somente as bordas do retangulo
    };

    private int                 mFlags;
    protected RectF             mBBoxPadding = new RectF();//BB == BoundingBox - Armazena o padding
    protected RectF             mBoundingBox = new RectF();//Caixa delimitadora - Area de colisao
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

        _updateBoundingBox();

    }

    public void addFlags(int flags)
    {
        mFlags |= flags;
    }

    public boolean hasFlag(int flag)
    {
        return (mFlags & flag) != 0;
    }

    public void removeFlags(int flags)
    {
        flags = ~flags;
        mFlags &= flags;
    }

    //Movimenta a entidade de maneira relativa a sua posicao atual.
    public void move(float offsetX, float offsetY) {
        mPosition.x += offsetX;
        mPosition.y += offsetY;

        _updateBoundingBox();
    }

    public void step(float elapsedTimeInSeconds) {}

    public RectF                getBoundingBox() {	return mBoundingBox; }
    public RectF                getBBoxPadding() {	return mBBoxPadding; }
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
    public void setPosition(float x, float y)
    {
        mPosition.set(x, y);
        _updateBoundingBox();
    }

    public void setPosition(PointF position)
    {
        mPosition.set(position);
        _updateBoundingBox();
    }


    //Atualiza a area de colisao sempre que a entidade se movimentar
    private void _updateBoundingBox()
    {
        mBoundingBox.set(mPosition.x + mBBoxPadding.left,
                mPosition.y + mBBoxPadding.top,
                (mPosition.x + mDimensions.x) - mBBoxPadding.right,
                (mPosition.y + mDimensions.y) - mBBoxPadding.bottom);
    }

    //Padding da area de colisao
    public void setBBoxPadding(RectF padding) {
        mBBoxPadding.set(padding);
        _updateBoundingBox();
    }


}
