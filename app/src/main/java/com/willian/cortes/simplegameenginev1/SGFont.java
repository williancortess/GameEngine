package com.willian.cortes.simplegameenginev1;

import android.graphics.PointF;

/**
 * Created by Willian on 04/03/2017.
 */

public class SGFont {
    private PointF		mFontDimensions = new PointF();//Largura e altura de um caractere na cena
    private SGTileset	mFontTileSet;
    private PointF		mTempDimensions = new PointF();

    public SGFont(SGTileset tileset, PointF fontDimensions)
    {
        mFontTileSet = tileset;
        mFontDimensions.set(fontDimensions);
    }

    //LArgura total de desenho dos caracteres
    public PointF measureText(SGText text)
    {
        mTempDimensions.x = text.getLength() * mFontDimensions.x;
        mTempDimensions.y = mFontDimensions.y;

        return mTempDimensions;
    }

    public PointF getFontDimensions() { return mFontDimensions; }
    public SGTileset getTileSet() { return mFontTileSet; }
}
