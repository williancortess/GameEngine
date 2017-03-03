package com.willian.cortes.simplegameenginev1;

import android.util.Log;

/**
 * Created by Willian on 01/03/2017.
 *
 * Armazena uma sequencia de indices de tiles que componham uma animacao
 */

public class SGAnimation {
    protected float 	mAccumulator; //Marca o tempo transcorrido desde o ultimo indice da sequencia de animacao
    protected int		mCurrentIndex; //Indice atual da sequencia de animacao
    protected float		mFrameDuration; //Tempo de apresentacao de um quadro de animacao
    protected boolean	mHasStarted; //Indica se a animacao foi iniciada
    protected boolean	mIsRunning; //Indica se a animacao esta sendo executada
    protected int		mNumberOfRepetitions; //Numero de vezes que a animacao sera repetida
    protected boolean	mResetAfterRunning; //Indica se retorna ao primeiro quadro (true) ou se fica no ultimo (false)
    protected int[]		mTiles; //Array de indices de tiles que compoe a animacao

    public SGAnimation(int[] tiles, float frameDurationInSeconds) //Tempo que vai durar a animacao
    {
        if(frameDurationInSeconds < 0.0f)
        {
            Log.e("SimpleGameEngine", Float.toString(frameDurationInSeconds) +
                    " não é um valor válido para" +
                    " frameDurationInSeconds.");

            throw new Error();
        }

        mTiles = tiles;
        mCurrentIndex = 0;
        mFrameDuration = frameDurationInSeconds;
        mResetAfterRunning = true;
    }

    public void start(int numberOfRepetitions)
    {
        mIsRunning = true;
        mHasStarted = true;
        mNumberOfRepetitions = numberOfRepetitions;
    }

    public void play()
    {
        if(mHasStarted)
        {
            mIsRunning = true;
        }
    }

    public void pause()
    {
        if(mHasStarted)
        {
            mIsRunning = false;
        }
    }

    public void reset() { mCurrentIndex = 0; }

    public void stop()
    {
        mHasStarted = false;
        mIsRunning = false;

        if(mResetAfterRunning)
        {
            mCurrentIndex = 0;
        }
        else
        {
            mCurrentIndex = mTiles.length - 1;
        }
    }

    public int step(float elapsedTimeInSeconds)
    {
        mAccumulator += elapsedTimeInSeconds;

        while(mAccumulator > mFrameDuration)
        {
            if(mIsRunning && mFrameDuration > 0.0f)
            {
                mCurrentIndex++;//Indice do quadro atual da animacao
                if(mCurrentIndex == mTiles.length)//Verifica se passou o ultimo indice do array
                {
                    if(mNumberOfRepetitions > 0)
                    {
                        if(--mNumberOfRepetitions == 0)
                        {
                            stop();
                        }
                        else
                        {
                            reset();
                        }
                    }
                    else
                    {
                        reset();
                    }
                }
            }

            mAccumulator -= mFrameDuration;
        }

        return mTiles[mCurrentIndex];//Retorna o indice atual do metodo
    }

    public int  getCurrentTile() { return mTiles[mCurrentIndex]; }

    public void setResetAfterRunning(boolean reset) { mResetAfterRunning = reset; }
}
