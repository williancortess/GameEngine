package com.willian.cortes.simplegameenginev1;

import android.util.Log;

/**
 * Created by Willian on 23/02/2017.
 */

public class SGTimer {
    protected float		mAccumulator = 0; //Armazena o tempo acumulado
    protected boolean	mHasStarted = false; //Indica se o timer foi ativado
    protected float		mInterval = 0; //Guarda o intervalo estipulado para que o timer dispare

    public SGTimer(float interval)
    {
        if(interval > 0) {
            mInterval = interval;
        }
        else {
            Log.d("SimpleGameEngine", "SGTimer: intervalo inválido.");
        }
    }

    //callback invcado quando o intervalo estipulado e atingido - Uso opcional
    public void onInterval() { }

    public void start() { mHasStarted = true; }

    public void stop() { mHasStarted = false; }

    public boolean step(float elapsedTimeInSeconds)
    {
        if(mHasStarted)
        {
            mAccumulator += elapsedTimeInSeconds;
            if(mAccumulator >= mInterval)
            {
                mAccumulator -= mInterval;
                onInterval();
                return true;
            }
        }
        return false;
    }

    public void reset() { mAccumulator = 0; }

    public void stopAndReset()
    {
        mHasStarted = false;
        mAccumulator = 0;
    }

    public float getInterval() { return mInterval; }

    public boolean hasStarted() { return mHasStarted; }
}
