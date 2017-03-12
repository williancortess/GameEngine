package com.willian.cortes.simplegameenginev1;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.util.Log;

/**
 * Created by Willian on 15/02/2017.
 *
 * RENDERIZA AS IMAGENS
 */

public class SGRenderer {
    private Canvas mTempCanvas;
    private Paint mTempPaint = new Paint();
    private RectF mTempDstRect = new RectF();
    private PointF	mTempPosition = new PointF();
    private SGViewport  mViewport;

    public SGRenderer() {	}

    public void beginDrawing(Canvas canvas, int screenColor, int viewportColor)
    {
        mTempCanvas = canvas;
        mTempCanvas.drawColor(screenColor);

        if(mViewport != null)
        {
            canvas.clipRect(mViewport.getDrawingArea(), Region.Op.REPLACE);
            canvas.drawColor(viewportColor);
        }
    }

    public void endDrawing()
    {
    }

//    public void drawRect(Rect source, int color)
//    {
//        mTempPaint.setColor(color);
//        mTempPaint.setStyle(Paint.Style.FILL);
//        mTempCanvas.drawRect(source, mTempPaint);
//    }
//
//    public void drawRect(RectF source, int color) {
//        mTempPaint.setColor(color);
//        mTempPaint.setStyle(Paint.Style.FILL);//MOSTRA O DESENHO INTEIRO
//        mTempCanvas.drawRect(source, mTempPaint);
//    }
//
//    public void drawRect(PointF position, PointF dimensions, int color)
//    {
//        mTempPaint.setColor(color);
//        mTempPaint.setStyle(Paint.Style.FILL);
//        mTempDstRect.set(position.x, position.y, position.x + dimensions.x, position.y + dimensions.y);
//        mTempCanvas.drawRect(mTempDstRect, mTempPaint);
//    }

    public void drawRect(Rect worldDestination, int color)
    {
        if(mViewport != null)
        {
            Point offsetFromOrigin = mViewport.getOffsetFromOrigin();
            PointF scalingFactor = mViewport.getScalingFactor();

            mTempDstRect.left = (worldDestination.left * scalingFactor.x) + offsetFromOrigin.x;
            mTempDstRect.top = (worldDestination.top * scalingFactor.y) + offsetFromOrigin.y;
            mTempDstRect.right = (worldDestination.right * scalingFactor.x) + offsetFromOrigin.x;
            mTempDstRect.bottom = (worldDestination.bottom * scalingFactor.y) + offsetFromOrigin.y;

            mTempPaint.setColor(color);
            mTempPaint.setStyle(Paint.Style.FILL);
            mTempCanvas.drawRect(mTempDstRect, mTempPaint);
        }
        else
        {
            Log.d("SimpleGameEngine", "SGRenderer.drawImage(): viewport não foi definida!");
            System.exit(1);
        }
    }

    public void drawRect(RectF worldDestination, int color)
    {
        if(mViewport != null)
        {
            Point offsetFromOrigin = mViewport.getOffsetFromOrigin();
            PointF scalingFactor = mViewport.getScalingFactor();

            mTempDstRect.left = (worldDestination.left * scalingFactor.x) + offsetFromOrigin.x;
            mTempDstRect.top = (worldDestination.top * scalingFactor.y) + offsetFromOrigin.y;
            mTempDstRect.right = (worldDestination.right * scalingFactor.x) + offsetFromOrigin.x;
            mTempDstRect.bottom = (worldDestination.bottom * scalingFactor.y) + offsetFromOrigin.y;

            mTempPaint.setColor(color);
            mTempPaint.setStyle(Paint.Style.FILL);
            mTempCanvas.drawRect(mTempDstRect, mTempPaint);
        }
        else
        {
            Log.d("SimpleGameEngine", "SGRenderer.drawImage(): viewport não foi definida!");
            System.exit(1);
        }
    }

    public void drawRect(PointF worldDestination, PointF dstDimensions, int color)
    {
        if(mViewport != null)
        {
            Point offsetFromOrigin = mViewport.getOffsetFromOrigin();
            PointF scalingFactor = mViewport.getScalingFactor();

            mTempDstRect.left = (worldDestination.x * scalingFactor.x) + offsetFromOrigin.x;
            mTempDstRect.top = (worldDestination.y * scalingFactor.y) + offsetFromOrigin.y;
            mTempDstRect.right = ((worldDestination.x + dstDimensions.x) * scalingFactor.x) + offsetFromOrigin.x;
            mTempDstRect.bottom = ((worldDestination.y + dstDimensions.y) * scalingFactor.y) + offsetFromOrigin.y;

            mTempPaint.setColor(color);
            mTempPaint.setStyle(Paint.Style.FILL);
            mTempCanvas.drawRect(mTempDstRect, mTempPaint);
        }
        else
        {
            Log.d("SimpleGameEngine", "SGRenderer.drawImage(): viewport não foi definida!");
            System.exit(1);
        }
    }

//    public void drawOutlineRect(Rect worldSpaceDestination, int color)
//    {
//        mTempPaint.setColor(color);
//        mTempPaint.setStyle(Paint.Style.STROKE);//MOSTRA APENAS AS BORDAS
//        mTempPaint.setStrokeWidth(0); //BORDA
//
//        mTempDstRect.set(worldSpaceDestination);
//        mTempDstRect.right -= 1;
//        mTempDstRect.bottom -= 1;
//        mTempCanvas.drawRect(mTempDstRect, mTempPaint);
//    }
//
//    public void drawOutlineRect(RectF worldSpaceDestination, int color)
//    {
//        mTempPaint.setColor(color);
//        mTempPaint.setStyle(Paint.Style.STROKE);
//        mTempPaint.setStrokeWidth(0);
//
//        mTempDstRect.set(worldSpaceDestination);
//        mTempDstRect.right -= 1;
//        mTempDstRect.bottom -= 1;
//        mTempCanvas.drawRect(mTempDstRect, mTempPaint);
//    }
//
//    public void drawOutlineRect(PointF position, PointF dimensions, int color)
//    {
//        mTempPaint.setColor(color);
//        mTempPaint.setStyle(Paint.Style.STROKE);
//        mTempPaint.setStrokeWidth(0);
//
//        mTempDstRect.set(position.x, position.y, position.x + dimensions.x, position.y + dimensions.y);
//        mTempDstRect.right -= 1;
//        mTempDstRect.bottom -= 1;
//        mTempCanvas.drawRect(mTempDstRect, mTempPaint);
//    }

    public void drawOutlineRect(Rect worldDestination, int color)
    {
        if(mViewport != null)
        {
            Point offsetFromOrigin = mViewport.getOffsetFromOrigin();
            PointF scalingFactor = mViewport.getScalingFactor();

            mTempDstRect.left = (worldDestination.left * scalingFactor.x) + offsetFromOrigin.x;
            mTempDstRect.top = (worldDestination.top * scalingFactor.y) + offsetFromOrigin.y;
            mTempDstRect.right = (worldDestination.right * scalingFactor.x) + offsetFromOrigin.x;
            mTempDstRect.bottom = (worldDestination.bottom * scalingFactor.y) + offsetFromOrigin.y;

            mTempDstRect.right -= 1;
            mTempDstRect.bottom -= 1;

            mTempPaint.setColor(color);
            mTempPaint.setStyle(Paint.Style.STROKE);
            mTempPaint.setStrokeWidth(0);

            mTempCanvas.drawRect(mTempDstRect, mTempPaint);
        }
        else
        {
            Log.d("SimpleGameEngine", "SGRenderer.drawImage(): viewport não foi definida!");
            System.exit(1);
        }
    }

    public void drawOutlineRect(RectF worldDestination, int color)
    {
        if(mViewport != null)
        {
            Point offsetFromOrigin = mViewport.getOffsetFromOrigin();
            PointF scalingFactor = mViewport.getScalingFactor();

            mTempDstRect.left = (worldDestination.left * scalingFactor.x) + offsetFromOrigin.x;
            mTempDstRect.top = (worldDestination.top * scalingFactor.y) + offsetFromOrigin.y;
            mTempDstRect.right = (worldDestination.right * scalingFactor.x) + offsetFromOrigin.x;
            mTempDstRect.bottom = (worldDestination.bottom * scalingFactor.y) + offsetFromOrigin.y;

            mTempDstRect.right -= 1;
            mTempDstRect.bottom -= 1;

            mTempPaint.setColor(color);
            mTempPaint.setStyle(Paint.Style.STROKE);
            mTempPaint.setStrokeWidth(0);

            mTempCanvas.drawRect(mTempDstRect, mTempPaint);
        }
        else
        {
            Log.d("SimpleGameEngine", "SGRenderer.drawImage(): viewport não foi definida!");
            System.exit(1);
        }
    }
    public void drawOutlineRect(PointF worldDestination, PointF dstDimensions, int color)
    {
        if(mViewport != null)
        {
            Point offsetFromOrigin = mViewport.getOffsetFromOrigin();
            PointF scalingFactor = mViewport.getScalingFactor();

            mTempDstRect.left = (worldDestination.x * scalingFactor.x) + offsetFromOrigin.x;
            mTempDstRect.top = (worldDestination.y * scalingFactor.y) + offsetFromOrigin.y;
            mTempDstRect.right = ((worldDestination.x + dstDimensions.x) * scalingFactor.x) + offsetFromOrigin.x;
            mTempDstRect.bottom = ((worldDestination.y + dstDimensions.y) * scalingFactor.y) + offsetFromOrigin.y;

            mTempDstRect.right -= 1;
            mTempDstRect.bottom -= 1;

            mTempPaint.setColor(color);
            mTempPaint.setStyle(Paint.Style.STROKE);
            mTempPaint.setStrokeWidth(0);

            mTempCanvas.drawRect(mTempDstRect, mTempPaint);
        }
        else
        {
            Log.d("SimpleGameEngine",
                    "SGRenderer.drawImage(): viewport não foi definida!");
            System.exit(1);
        }
    }

//    public void drawImage(SGImage image, Rect source, RectF destination)
//    {
//        if(image != null)
//        {
//            Bitmap bitmap = image.getBitmap();
//            mTempCanvas.drawBitmap(bitmap, source, destination, mTempPaint);
//        }
//        else
//        {
//            drawRect(destination, Color.RED);
//        }
//    }

    public void drawImage(SGImage image, Rect objSource, RectF worldDestination)
    {
        if(mViewport != null)
        {
            Point offsetFromOrigin = mViewport.getOffsetFromOrigin();
            PointF scalingFactor = mViewport.getScalingFactor();

            mTempDstRect.left = (worldDestination.left * scalingFactor.x) + offsetFromOrigin.x;
            mTempDstRect.top = (worldDestination.top * scalingFactor.y) + offsetFromOrigin.y;
            mTempDstRect.right = (worldDestination.right * scalingFactor.x) + offsetFromOrigin.x;
            mTempDstRect.bottom = (worldDestination.bottom * scalingFactor.y) + offsetFromOrigin.y;

            if(image != null)
            {
                Bitmap bitmap = image.getBitmap();
                mTempCanvas.drawBitmap(bitmap, objSource, mTempDstRect, mTempPaint);
            }
            else
            {
                drawRect(mTempDstRect, Color.RED);
            }
        }
        else
        {
            Log.d("SimpleGameEngine", "SGRenderer.drawImage(): viewport não foi definida!");
            System.exit(1);
        }
    }


//    public void drawImage(SGImage image, Rect source, PointF dstPosition, PointF dstDimensions)
//    {
//        mTempDstRect.set(dstPosition.x,
//                dstPosition.y,
//                dstPosition.x + dstDimensions.x,
//                dstPosition.y + dstDimensions.y);
//
//        if(image != null)
//        {
//            Bitmap bitmap = image.getBitmap();
//            mTempCanvas.drawBitmap(bitmap, source, mTempDstRect, mTempPaint);
//        }
//        else
//        {
//            drawRect(mTempDstRect, Color.RED);
//        }
//    }

    public void drawImage(SGImage image, Rect objSource, PointF worldDestination, PointF dstDimensions)
    {
        if(mViewport != null)
        {
            Point offsetFromOrigin = mViewport.getOffsetFromOrigin();
            PointF scalingFactor = mViewport.getScalingFactor();

            mTempDstRect.left = (worldDestination.x * scalingFactor.x) + offsetFromOrigin.x;
            mTempDstRect.top = (worldDestination.y * scalingFactor.y) + offsetFromOrigin.y;
            mTempDstRect.right = ((worldDestination.x + dstDimensions.x) * scalingFactor.x) + offsetFromOrigin.x;
            mTempDstRect.bottom = ((worldDestination.y + dstDimensions.y) * scalingFactor.y) + offsetFromOrigin.y;

            if(image != null)
            {
                Bitmap bitmap = image.getBitmap();
                mTempCanvas.drawBitmap(bitmap, objSource, mTempDstRect, mTempPaint);//Desenha a imagem
            }
            else
            {
                drawRect(mTempDstRect, Color.RED);
            }
        }
        else
        {
            Log.d("SimpleGameEngine", "SGRenderer.drawImage(): viewport não foi definida!");
            System.exit(1);//1 indica erro - 0 indica sucesso
        }
    }

    //Desenho de texto na tela
    public void drawText(SGText text, SGFont font, PointF worldDestination)
    {
        SGTileset fontTileSet = font.getTileSet();
        char[] textChars = text.getCharacters();

        mTempPosition.set(worldDestination);

        for(int i = 0; i < textChars.length; i++)
        {
            Rect character = fontTileSet.getTile(textChars[i]);
            drawImage(fontTileSet.getImage(), character, mTempPosition, font.getFontDimensions());
            mTempPosition.x += font.getFontDimensions().x;
        }
    }

    public SGViewport   getViewport() { return mViewport; }
    public void         setViewport(SGViewport viewport) { mViewport = viewport; }
}
