package com.willian.cortes.simplegameenginev1;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import static com.willian.cortes.simplegameenginev1.SGActivity.SGOrientation.LANDSCAPE;

/**
 * Created by Willian on 10/02/2017.
 */

public class SGActivity extends Activity {
    protected SGInputPublisher mInputPublisher = null;
    public static  final String TAG = "SimpleGameEngine";
    private SGPreferences mPreferences;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(mInputPublisher != null){
            return mInputPublisher.onTouchEvent(event);
        }else{
            return false;
        }
    }

    public void setInputPublisher(SGInputPublisher inputPublisher) {
        mInputPublisher = inputPublisher;
    }

    public SGInputPublisher getmInputPublisher(){return mInputPublisher;}

    public enum SGOrientation {
        LANDSCAPE,
        PORTRAIT
    }

    public void setOrientation(SGOrientation orientation)
    {
        switch(orientation)
        {
            case LANDSCAPE:
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                break;
            case PORTRAIT:
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                break;
        }
    }

    //ATIVA MODO TELA CHEIA!!!
    public void enableFullScreen()
    {
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        window.requestFeature(Window.FEATURE_NO_TITLE);

        if(android.os.Build.VERSION.SDK_INT >= 19)
        {
            window.getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

    //DEIXA A TELA SEMPRE ACORDADA!!!
    public void enableKeepScreenOn(){
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPreferences = new SGPreferences(this);
    }

    public SGPreferences getPreferences(){return mPreferences;}

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.d(TAG, "onDestroy() chamado");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.d(TAG, "onPause() chamado");
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        Log.d(TAG, "onRestart() chamado");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d(TAG, "onResume() chamado");
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.d(TAG, "onStart() chamado");
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.d(TAG, "onStop() chamado");
    }


}
