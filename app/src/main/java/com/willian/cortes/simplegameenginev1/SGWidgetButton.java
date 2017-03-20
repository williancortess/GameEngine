package com.willian.cortes.simplegameenginev1;

import android.graphics.PointF;
import android.util.Log;

/**
 * Created by Willian on 18/03/2017.
 */

public class SGWidgetButton extends SGWidget {
    protected int		mCurrentTile = 0;
    protected SGGui		mGui;
    protected SGTileset	mTileset = null;

    public SGWidgetButton(Alignment alignment, PointF position, PointF dimensions, SGTileset tileset, SGGui gui)
    {
        super("button", alignment, position, dimensions);

        mTileset = tileset;
        mGui = gui;
    }

    @Override
    public void render(SGRenderer renderer)
    {
        renderer.drawImage(mTileset.getImage(), mTileset.getTile(mCurrentTile), mArea);
    }

    @Override
    public boolean injectDown(PointF position)
    {
        if(mIsEnabled)
        {
            Log.d("SimpleGameEngineV4", "injectDown");

            mGui.setCurrentButton(this);
            mCurrentTile = 1;

            return onDown(position);
        }
        else
        {
            return false;
        }
    }

    @Override
    public boolean injectUp(PointF position)
    {
        if(mIsEnabled)//caso o botao esteja ativo
        {
            if(mGui.getCurrentButton() == this)//se for o botao atual - caudador do evento down
            {
                mGui.setCurrentButton(null);
                mCurrentTile = 0; //muda para o tile up

                return onUp(position); //trata o evento
            }
            else if(mGui.getCurrentButton() != null)
            {
                mGui.getCurrentButton().reset();
                mGui.setCurrentButton(null);

                return false;
            }
            else
            {
                return false;
            }
        }
        else
        {
            if(mGui.getCurrentButton() != null)
            {
                mGui.getCurrentButton().reset();
                mGui.setCurrentButton(null);
            }

            return false;
        }
    }

    @Override
    public boolean onDown(PointF position)
    {
        return true;
    }

    @Override
    public boolean onUp(PointF position)
    {
        return true;
    }

    //Retorna o botao pause para o estado up = tile 0
    public void reset()
    {
        mCurrentTile = 0;
    }
}
