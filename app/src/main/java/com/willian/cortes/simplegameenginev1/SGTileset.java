package com.willian.cortes.simplegameenginev1;

import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;

/**
 * Created by Willian on 28/02/2017.
 */

public class SGTileset {
    protected Point	    m2dNumberOfTiles = new Point();//Quantidade de colunas e linhas dentro do tileset
    protected PointF	mDimensions = new PointF();//Dimencoes do tileset em pixels
    protected Rect		mDrawableTileArea = new Rect();//Area desenhavel do tile
    protected SGImage	mImage;//Imagem do tileset
    protected Rect		mTempTileArea = new Rect();//temp calc da area
    protected Point 	mTileDimensions = new Point(0, 0);//Dimencoes do tile em pixel

    //Imagem - Num colunas e linhas de um tile dentro do tileset - Area desenhavel de um tile
    public SGTileset(SGImage image, Point tilesNum, Rect drawableTileArea)
    {
        //Calcula as dimensoes de um tile dividindo as dimensoes do tileset pela qtd de tiles hor. e ver. do tileset.
        mDimensions.set(image.getDimensions().x, image.getDimensions().y);
        mImage = image;
        m2dNumberOfTiles.set(tilesNum.x, tilesNum.y);
        mTileDimensions.x = (int) mDimensions.x / m2dNumberOfTiles.x;
        mTileDimensions.y = (int) mDimensions.y / m2dNumberOfTiles.y;

        //Verifica se foi passado a area de tile desenhavel ou se desenha o tile inteiro
        if(drawableTileArea != null)
        {
            mDrawableTileArea.set(drawableTileArea);
        }
        else
        {
            mDrawableTileArea.set(0, 0, mTileDimensions.x, mTileDimensions.y);
        }
    }

    public PointF	getDimensions() { return mDimensions; }
    public Rect		getDrawableTileArea() { return mDrawableTileArea; }
    public SGImage	getImage() { return mImage; }
    public int		get1dNumberOfTiles() { return m2dNumberOfTiles.x * m2dNumberOfTiles.y; }
    public Point 	get2dNumberOfTiles() { return m2dNumberOfTiles; }
    public Point 	getTileDimensions() { return mTileDimensions; }

    //Recupera a area do tile dentro do tileset
    public Rect getTile(int x, int y)
    {
        int left = mTileDimensions.x * x;
        int top = mTileDimensions.y * y;

        mTempTileArea.set(left + mDrawableTileArea.left,
                top + mDrawableTileArea.top,
                left + mDrawableTileArea.right,
                top + mDrawableTileArea.bottom);

        return mTempTileArea;
    }

    //Recupera a area do tile dentro do tileset pelo indicice linear
    public Rect getTile(int index)
    {
        int x = index % m2dNumberOfTiles.x;
        int y = index / m2dNumberOfTiles.x;
        int left = mTileDimensions.x * x;
        int top = mTileDimensions.y * y;

        mTempTileArea.set(left + mDrawableTileArea.left,
                top + mDrawableTileArea.top,
                left + mDrawableTileArea.right,
                top + mDrawableTileArea.bottom);

        return mTempTileArea;
    }
}
