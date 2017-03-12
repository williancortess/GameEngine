package com.willian.cortes.simplegameenginev1;

import android.graphics.PointF;

/**
 * Created by Willian on 12/03/2017.
 */

public class SGWidgetLabel extends SGWidget {
    protected SGFont 	mFont;
    protected SGText 	mText;

    public SGWidgetLabel(Alignment alignment, PointF position, SGFont font, String text)
    {
        super("label", alignment, position, new PointF(0, 0));

        mFont = font;
        mText = new SGText(text);
        mDimensions = mFont.measureText(mText);//Calcula as dimensoes do texto
    }

    @Override
    public void render(SGRenderer renderer)
    {
        renderer.drawText(mText, mFont, mAbsolutePosition);//Desenha o texto
    }

    @Override
    public void update()
    {
        //Obtem as dimensoes do texto inserido em mText
        PointF textDimensions = mFont.measureText(mText);

        if(mAlignment == Alignment.Left)
        {
            mAbsolutePosition.x = mRelativePosition.x;
        }
        else if(mAlignment == Alignment.Center)
        {
            float halfOffset = (mSceneDimensions.x / 2) - (textDimensions.x / 2);
            mAbsolutePosition.x = halfOffset + mRelativePosition.x;
        }
        else // mAlignment == Alignment.Right
        {
            mAbsolutePosition.x = (mSceneDimensions.x - textDimensions.x) + mRelativePosition.x;
        }

        mAbsolutePosition.y = mRelativePosition.y;

        mArea.left = mAbsolutePosition.x;
        mArea.top = mAbsolutePosition.y;
        mArea.right = mArea.left + mDimensions.x;
        mArea.bottom = mArea.top + mDimensions.y;
    }

    public SGFont   getFont() { return mFont; }
    public String   getString() { return mText.getString(); }

    public void     setString(String label) { mText.setString(label); }
}
