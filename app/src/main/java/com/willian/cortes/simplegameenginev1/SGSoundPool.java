package com.willian.cortes.simplegameenginev1;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

import java.io.IOException;

/**
 * Created by Willian on 05/03/2017.
 */

public class SGSoundPool {
    private static final int MAX_SOUNDS = 16;
    private Context mContext = null;
    private SoundPool	mSoundPool = new SoundPool(MAX_SOUNDS, AudioManager.STREAM_MUSIC, 0);

    public SGSoundPool(Context context)
    {
        mContext = context;
    }

    public int loadSound(int resourceId)
    {
        return mSoundPool.load(mContext, resourceId, 1);
    }

    //Carrega arquivo de som da pasta assets
    public int loadSound(String filename)
    {
        try
        {
            AssetFileDescriptor descriptor;
            descriptor = mContext.getAssets().openFd(filename);

            return mSoundPool.load(descriptor, 1);
        }
        catch (IOException e)
        {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("SGSoundPool.loadSound(): arquivo ");
            stringBuilder.append(filename);
            stringBuilder.append(" não encontrado!");
            Log.d("SimpleGameEngine", stringBuilder.toString());

            return -1;
        }
    }

    //Metodo de reproducao do som
    public void playSound(int soundId, float volumeLeft, float volumeRight, int loop, float rate)
    {
        //rate muda a frequencia vai de 0.5 ate 2
        mSoundPool.play(soundId, volumeLeft, volumeRight, 0, loop, rate);
    }

    //Descarrega o ssom para liberar memoria
    public void unloadSound(int soundId)
    {
        mSoundPool.unload(soundId);
    }
}
