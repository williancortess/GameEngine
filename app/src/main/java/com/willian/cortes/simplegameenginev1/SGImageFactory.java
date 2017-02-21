package com.willian.cortes.simplegameenginev1;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Willian on 14/02/2017.
 *
 * FABRICA DE IMAGENS
 */

public class SGImageFactory {

    //Necessario para acessar os recursos do projeto
    protected Context mContext = null;

    public SGImageFactory(Context context)
    {
        mContext = context;
    }

    //Carrega imagem da pasta asset
    public SGImage createImage(String filename)
    {
        Bitmap bitmap = null;

        try
        {
            AssetManager assetManager = mContext.getAssets();
            InputStream inputStream = assetManager.open(filename);
            bitmap = BitmapFactory.decodeStream(inputStream);
            inputStream.close();
        }
        catch (IOException e)
        {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("SGImageFactory.createImage(): arquivo ");
            stringBuilder.append(filename);
            stringBuilder.append(" não encontrado!");
            Log.d("SimpleGameEngine", stringBuilder.toString());
            return null;
        }

        SGImage image = new SGImage(bitmap);

        return image;
    }

    //Carrega imagem da pasta draeable
    public SGImage createImage(int resourceId)//Identificador do recurso
    {
        Bitmap bitmap = null;

        try
        {
            Resources resources = mContext.getResources();
            InputStream inputStream = resources.openRawResource(resourceId);

            bitmap = BitmapFactory.decodeStream(inputStream);
            inputStream.close();
        }
        catch (IOException e) { }

        SGImage image = new SGImage (bitmap);

        return image;
    }


    //Retorna o contexto da fabrica
    public Context getContext() { return mContext; }
}
