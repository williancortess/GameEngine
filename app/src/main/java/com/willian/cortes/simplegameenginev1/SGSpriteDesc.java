package com.willian.cortes.simplegameenginev1;

import android.graphics.Point;
import android.graphics.Rect;

import java.util.HashMap;

/**
 * Created by Willian on 04/03/2017.
 *
 * Descritor de sprite
 */

public class SGSpriteDesc {
    private HashMap<String, SGAnimation> mAnimations = new HashMap<String, SGAnimation>(); //Tabela Hash de animacoes
    private SGTileset	mTileset; //Tileset que sera usado pelo sprite

    public SGSpriteDesc(SGTileset tileset)
    {
        mTileset = tileset;

        _createDefaultAnimation();
    }

    public SGSpriteDesc(SGImage image, Rect drawableArea)
    {
        //Transforma uma imagem em um tile
        Point tilesNum = new Point(1, 1);
        mTileset = new SGTileset(image, tilesNum, drawableArea);

        _createDefaultAnimation();
    }

    public SGSpriteDesc(SGImage image)
    {
        //Encapsula com o metodo acima - area ocupada = area total da imagem
        this(image, new Rect(0, 0, image.getDimensions().x, image.getDimensions().y));
    }

    public SGSpriteDesc addAnimation(String name, SGAnimation animation)
    {
        mAnimations.put(name, animation);
        return this;
    }

    public HashMap<String, SGAnimation> getAnimations()
    {
        return mAnimations;
    }

    public SGTileset getTileset() { return mTileset; }

    private void _createDefaultAnimation()
    {
        int tiles[] = new int[1];
        tiles[0] = 0;
        mAnimations.put("default", new SGAnimation(tiles, 0.0f));
    }
}
