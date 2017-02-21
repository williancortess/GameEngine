package com.willian.cortes.simplegameenginev1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.view.View;

/**
 * Created by Willian on 11/02/2017.
 */

public class SGView extends View{
    private Point           mDimensions = new Point(); //ARMAZENA AS DIMENCOES DA VIEW
    private boolean         mHasStarted;
    private SGStepwatch mStepwatch = new SGStepwatch(); //MARCA O TEMPO DO JOGO
    private SGImageFactory mImageFactory;
    private SGRenderer mRenderer;

    public SGView(Context context) {
        super(context);
        mImageFactory = new SGImageFactory(context);
        mRenderer = new SGRenderer();
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        canvas.drawColor(Color.LTGRAY);   //SUBSTITUIDO PELO RENDERER

        step(canvas, mStepwatch.tick());

        //FAZ O ANDROID REDESENHAR O onDRAW
        //OU SEJA, CRIA O LOOP DE JOGO SEMPRE RECONSTRUINDO A TELA
        invalidate();
    }

    //PROCESSA UM PASSO DA ANIMACAO
    public void step(Canvas canvas, float elapsedTimeInSeconds){

    }

    @Override
    protected void onSizeChanged(int width, int height, int oldWidth, int oldHeight)
    {
        mDimensions.set(width, height);

        if(!mHasStarted)
        {
            setup();
            mHasStarted = true;
        }
    }

    protected void setup()
    {
    }

    public Point getDimensions() { return mDimensions; }
    public SGImageFactory getImageFactory(){return mImageFactory;}
    public SGRenderer getRenderer(){return mRenderer;}
}
