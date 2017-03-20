package com.willian.cortes.simplegameenginev1;

import android.graphics.PointF;
import android.graphics.RectF;

import java.util.HashMap;

/**
 * Created by Willian on 12/03/2017.
 *
 * Elemento que nao possui representacao visual, servindo para agrupar os widgets
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

    @Override
    public boolean injectDown(PointF position)
    {
        for(HashMap.Entry<String, SGWidget> entry : mChildren.entrySet())
        {
            SGWidget widget = entry.getValue();

            if(widget.isEnabled())
            {
                RectF area = widget.getArea();

                if(position.x >= area.left && position.x <= area.right && position.y >= area.top && position.y <= area.bottom)
                {
                    if(entry.getValue().injectDown(position))
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean injectUp(PointF position)
    {
        for(HashMap.Entry<String, SGWidget> entry : mChildren.entrySet())
        {
            SGWidget widget = entry.getValue();

            if(widget.isEnabled())
            {
                RectF area = widget.getArea();

                if(position.x >= area.left && position.x <= area.right && position.y >= area.top && position.y <= area.bottom)
                {
                    if(entry.getValue().injectUp(position))
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public void render(SGRenderer renderer)
    {
        for(HashMap.Entry<String, SGWidget> entry : mChildren.entrySet())
        {
            SGWidget widget = entry.getValue();

            if(widget.isVisible())
            {
                widget.render(renderer);
            }
        }
    }

    @Override
    public void update()
    {
        super.update();

        for(HashMap.Entry<String, SGWidget> entry : mChildren.entrySet())
        {
            SGWidget widget = entry.getValue();
            widget.update();
        }

        _updateArea();
    }

    public SGWidget getChild(String name) { return mChildren.get(name); }
}
