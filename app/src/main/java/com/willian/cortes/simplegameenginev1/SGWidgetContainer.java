package com.willian.cortes.simplegameenginev1;

import android.graphics.PointF;
import android.graphics.RectF;

import java.util.HashMap;

/**
 * Created by Willian on 12/03/2017.
 */

public class SGWidgetContainer extends SGWidget {
    //Guarda os elementos filhos
    protected HashMap<String, SGWidget> mChildren = new HashMap<String, SGWidget>();

    public SGWidgetContainer(Alignment alignment, PointF position, PointF dimensions)
    {
        super("container", alignment, position, dimensions);

        if(dimensions.x == 0 || dimensions.y == 0)
        {
            mArea.set(Float.NaN, Float.NaN, Float.NaN, Float.NaN); // NaN = Not a Number
        }
    }

    public void addChild(String name, SGWidget widget)
    {
        mChildren.put(name, widget);
        widget.setSceneDimensions(mSceneDimensions);//Calcula a posicao absoluta
        widget.setParent(this);//Define o elemento pai

        _updateArea();
    }

    public SGWidget removeChild(String name)
    {
        SGWidget widget = mChildren.remove(name);
        widget.setParent(null);

        _updateArea();

        return widget;
    }

    private void _updateArea()
    {
        if(!mChildren.isEmpty())
        {
            for(HashMap.Entry<String, SGWidget> entry : mChildren.entrySet())
            {
                SGWidget widget = entry.getValue();
                RectF childArea = widget.getArea();

                if(Float.isNaN(mArea.left) || childArea.left < mArea.left)
                {
                    mArea.left = childArea.left;
                }
                if(Float.isNaN(mArea.right) || childArea.right > mArea.right)
                {
                    mArea.right = childArea.right;
                }
                if(Float.isNaN(mArea.bottom) || childArea.bottom > mArea.bottom)
                {
                    mArea.bottom = childArea.bottom;
                }
                if(Float.isNaN(mArea.top) || childArea.top < mArea.top)
                {
                    mArea.top = childArea.top;
                }
            }
        }
        else
        {
            mArea.set(Float.NaN, Float.NaN, Float.NaN, Float.NaN);
        }
    }
}
