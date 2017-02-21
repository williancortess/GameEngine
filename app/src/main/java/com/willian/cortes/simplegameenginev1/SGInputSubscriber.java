package com.willian.cortes.simplegameenginev1;

import android.view.MotionEvent;

/**
 * Created by Willian on 16/02/2017.
 *
 * INTERFACE ASSINANTE
 */

public interface SGInputSubscriber {
    public void onDown(MotionEvent event);

    public void onScroll(MotionEvent downEvent, MotionEvent moveEvention, float distanceX, float distanceY);

    public void onUp(MotionEvent event);
}
