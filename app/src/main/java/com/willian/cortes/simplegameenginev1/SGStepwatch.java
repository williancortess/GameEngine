package com.willian.cortes.simplegameenginev1;

import android.os.SystemClock;

/**
 * Created by Willian on 14/02/2017.
 *
 * MARCAR O TEMPO TRANSCORRIDO DO JOGO
 */

public class SGStepwatch {
    protected long		mCurrentTime;//Tempo corrente
    protected long		mLastTime;//Ultimo tempo
    protected float		mElapsedTime;//Tempo transcorrido

    protected float tick()
    {
        if(mCurrentTime == 0)
        {
            mLastTime = mCurrentTime = SystemClock.uptimeMillis();
        }
        else
        {
            mCurrentTime = SystemClock.uptimeMillis(); //Marca o tempo corrido desde o boot do android
        }


        mElapsedTime = (mCurrentTime - mLastTime) / 1000.0f; //tranforma de milisegundos em segundos
        mLastTime = mCurrentTime;

        return mElapsedTime;
    }
}
