package com.willian.cortes.simplegameenginev1;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;

import java.io.IOException;

/**
 * Created by Willian on 05/03/2017.
 */

public class SGMusicPlayer {
    private Context 	mContext = null;
    private MediaPlayer mMediaPlayer = null;
    private boolean mHasInitialized = false;
    private boolean mIsPaused = false;
    private boolean mIsPlaying = false;

    public SGMusicPlayer(Context context)
    {
        mContext = context;
        mMediaPlayer = new MediaPlayer();
    }

    public void loadMusic(String filename) {
        if(mHasInitialized)
        {
            mMediaPlayer.reset();
        }

        try
        {
            AssetFileDescriptor descriptor;
            descriptor = mContext.getAssets().openFd(filename);
            mMediaPlayer.setDataSource(descriptor.getFileDescriptor(),
                    descriptor.getStartOffset(),
                    descriptor.getLength());
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        mHasInitialized = true;
    }

    public void play(boolean enableLooping, float leftVolume, float rightVolume)
    {
        if(mHasInitialized)
        {
            try
            {
                mMediaPlayer.prepare();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }

            mMediaPlayer.setVolume(leftVolume, rightVolume);

            if(enableLooping)
            {
                mMediaPlayer.setLooping(true);
            }

            mMediaPlayer.start();
            mIsPaused = false;
            mIsPlaying = true;
        }
    }

    public void pause()
    {
        mMediaPlayer.pause();
        mIsPaused = true;
        mIsPlaying = false;
    }

    public void stop()
    {
        if(mHasInitialized)
        {
            mMediaPlayer.seekTo(0);//Tempo de reproducao em milissegundos ao qual queremos avancar ou retroceder
            mMediaPlayer.stop();
            mIsPaused = false;
            mIsPlaying = false;
        }
    }

    public void resume()
    {
        if(mIsPaused)
        {
            mMediaPlayer.start();
            mIsPaused = false;
            mIsPlaying = true;
        }
    }

    //Libera o arquivo de musica carregado no MediaPlayer para carregar outro
    public void reset()
    {
        mMediaPlayer.reset();
        mHasInitialized = false;
        mIsPaused = false;
        mIsPlaying = false;
    }

    //Mata o processo ao sair da aplicacao
    public void release()
    {
        mMediaPlayer.release();
        mMediaPlayer = null;
        mHasInitialized = false;
        mIsPaused = false;
        mIsPlaying = false;
    }

    public boolean hasInitialized() { return mHasInitialized; }

    public boolean isPaused() { return mIsPaused; }
    public boolean isPlaying() { return mIsPlaying; }

    public void setVolume(float leftVolume, float rightVolume)
    {
        mMediaPlayer.setVolume(leftVolume, rightVolume);
    }
}
