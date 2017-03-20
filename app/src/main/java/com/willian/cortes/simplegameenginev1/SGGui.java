package com.willian.cortes.simplegameenginev1;

import android.graphics.Point;
import android.graphics.PointF;

/**
 * Created by Willian on 18/03/2017.
 *
 * Armazena a arvore de widgets
 */

public class SGGui {
    private SGWidgetButton      mCurrentButton = null;
    private SGRenderer			mRenderer;
    private SGWidgetContainer	mRoot;
    private PointF				mTempPosition = new PointF();

    public SGGui(SGRenderer renderer, Point sceneDimensions)
    {
        mRenderer = renderer;
        PointF position = new PointF(0, 0);
        PointF dimensions = new PointF(sceneDimensions.x, sceneDimensions.y);
        mRoot = new SGWidgetContainer(SGWidget.Alignment.Left, position, dimensions);
        mRoot.setSceneDimensions(sceneDimensions);
    }

    public boolean injectDown(PointF position)
    {
        return mRoot.injectDown(_screenToScene(position));
    }

    public boolean injectUp(PointF position)
    {
        boolean result = mRoot.injectUp(_screenToScene(position));

        if(!result && mCurrentButton != null)
        {
            mCurrentButton.reset();
            mCurrentButton = null;
        }

        return result;

//        return mRoot.injectUp(_screenToScene(position));
    }

    public void render()
    {
        mRoot.render(mRenderer);
    }

    public void update()
    {
        mRoot.update();
    }

    public SGWidgetButton       getCurrentButton() { return mCurrentButton; }
    public SGWidgetContainer    getRoot() { return mRoot; }

    public void                 setCurrentButton(SGWidgetButton currentButton) { mCurrentButton = currentButton; }

    //Transforma o sistema de coordenadas da tela para o mundo
    private PointF _screenToScene(PointF position)
    {
        Point offsetFromOrigin = mRenderer.getViewport().getOffsetFromOrigin();
        PointF scalingFactor = mRenderer.getViewport().getScalingFactor();

        mTempPosition.x = (position.x - offsetFromOrigin.x) / scalingFactor.x;
        mTempPosition.y = (position.y - offsetFromOrigin.y) / scalingFactor.y;

        return mTempPosition;
    }
}
