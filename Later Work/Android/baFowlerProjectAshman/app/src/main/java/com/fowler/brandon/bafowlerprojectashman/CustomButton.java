//Name: Brandon Fowler
//Class: CSCD372
//Quarter: Fall 2015
//Assignment: Ashman Project

package com.fowler.brandon.bafowlerprojectashman;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;


public class CustomButton extends View {

    final float width = 1;
    final float height = 1;
    final float aspectRatio = width/height;
    int canvasWidth;
    int canvasHeight;

    public CustomButton(Context context){
        super(context);
    }

    public CustomButton(Context context, AttributeSet attrs){
        super(context, attrs);
    }

    public CustomButton(Context context,AttributeSet attrs, int defStyle){
        super(context,attrs, defStyle);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setLayerType(this.LAYER_TYPE_SOFTWARE, null);
        float width = canvasWidth;
        float height = canvasHeight;
        width = width / this.width;
        height = height / this.height;
        canvas.scale(width, height);
        Float rad = new Float(0.5);
        Paint p = new Paint();
        p.setColor(Color.rgb(0, 0, 255));
        p.setStyle(Paint.Style.FILL);
        canvas.drawCircle(new Float(0.5), new Float(0.5), rad, p);
    }

    @Override
    public void onSizeChanged(int xNew, int yNew, int xOld, int yOld){
        this.canvasWidth = xNew;
        this.canvasHeight = yNew;
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
        float allocWidth = MeasureSpec.getSize(widthMeasureSpec);
        float allocHeight = MeasureSpec.getSize(heightMeasureSpec);
        float width = allocHeight*aspectRatio;
        float height = allocWidth/aspectRatio;
        float finalHeight;
        float finalWidth;

        if(height > allocHeight){
            finalHeight = allocHeight;
            finalWidth = width;
        }
        else{
            finalHeight = height;
            finalWidth = allocWidth;
        }

        setMeasuredDimension((int) finalWidth, (int) finalHeight);
    }

}
