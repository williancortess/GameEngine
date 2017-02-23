package com.willian.cortes.simplegameenginev1;

import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.RectF;

import java.util.ArrayList;

/**
 * Created by Willian on 23/02/2017.
 */

public class SGTrigger extends SGEntity{

    //Armazena as entidades a serem observadas
    private ArrayList<SGEntity> mObservedEntities = new ArrayList<SGEntity>();

    public SGTrigger(SGWorld world, int id, PointF position, PointF dimensions)
    {
        super(world, id, "trigger", position, dimensions);

        setDebugColor(Color.MAGENTA);
        setDebugDrawingStyle(DebugDrawingStyle.OUTLINE);
    }

    public boolean addObservedEntity(SGEntity entity)
    {
        if(mObservedEntities.contains(entity))
        {
            return false;
        }
        else
        {
            mObservedEntities.add(entity);
            return true;
        }
    }

    public boolean removeObservedEntity(SGEntity entity)
    {
        return mObservedEntities.remove(entity);
    }

    //Invocado sempre que detectar uma colisao
    public void onHit(SGEntity entity, float elapsedTimeInSeconds) { }


    //Verifica a colisao com as entidades e invoca o onHit() caso isso ocorra
    @Override
    public void step(float elapsedTimeInSeconds)
    {
        SGEntity currentEntity;
        SGWorld world = getWorld();
        RectF triggerBoundingBox = getBoundingBox();

        int arraySize = mObservedEntities.size();
        for(int i = 0; i < arraySize; i++)
        {
            currentEntity = mObservedEntities.get(i);
            if(world.collisionTest(triggerBoundingBox, currentEntity.getBoundingBox())) {
                onHit(currentEntity, elapsedTimeInSeconds);
            }
        }
    }

}
