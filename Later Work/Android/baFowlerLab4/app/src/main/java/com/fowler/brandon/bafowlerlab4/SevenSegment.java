//Name: Brandon Fowler
//Assignment: Lab4
//Class: CSCD372
//Quarter: Fall 2015

package com.fowler.brandon.bafowlerlab4;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;

public class SevenSegment extends View{

    int displayVal;
    boolean segmentStates[][];
    int canvasWidth;
    int canvasHeight;
    final float width = 20;
    final float height = 32;
    final float aspectRatio = width/height;
    final float segmentPoints[] = {3,3,4,4,16,4,17,3,16,2,4,2};
    final float segmentPoints2[] = {2,3,3,4,14,4,15,3,14,2,3,2};
    final int onColor = Color.rgb(255,0,0);
    final int offColor = Color.rgb(76,0,0);

    public SevenSegment(Context context){
        super(context);
        initialize();
    }

    public SevenSegment(Context context, AttributeSet attrs){
        super(context, attrs);
        initialize();
    }

    public SevenSegment(Context context,AttributeSet attrs, int defStyle){
        super(context, attrs, defStyle);
        initialize();
    }

    private void initialize(){
        displayVal = 10;
        segmentStates = new boolean[11][];
        for(int i = 0; i < segmentStates.length; i++){
            segmentStates[i] = new boolean[7];
        }

        //0
        segmentStates[0][0] = true; segmentStates[0][1] = false; segmentStates[0][2] = true;
        segmentStates[0][3] = true; segmentStates[0][4] = true; segmentStates[0][5] = true;
        segmentStates[0][6] = true;

        //1
        segmentStates[1][0] = false; segmentStates[1][1] = false; segmentStates[1][2] = false;
        segmentStates[1][3] = false; segmentStates[1][4] = true; segmentStates[1][5] = false;
        segmentStates[1][6] = true;

        //2
        segmentStates[2][0] = true; segmentStates[2][1] = true; segmentStates[2][2] = true;
        segmentStates[2][3] = false; segmentStates[2][4] = true; segmentStates[2][5] = true;
        segmentStates[2][6] = false;

        //3
        segmentStates[3][0] = true; segmentStates[3][1] = true; segmentStates[3][2] = true;
        segmentStates[3][3] = false; segmentStates[3][4] = true; segmentStates[3][5] = false;
        segmentStates[3][6] = true;

        //4
        segmentStates[4][0] = false; segmentStates[4][1] = true; segmentStates[4][2] = false;
        segmentStates[4][3] = true; segmentStates[4][4] = true; segmentStates[4][5] = false;
        segmentStates[4][6] = true;

        //5
        segmentStates[5][0] = true; segmentStates[5][1] = true; segmentStates[5][2] = true;
        segmentStates[5][3] = true; segmentStates[5][4] = false; segmentStates[5][5] = false;
        segmentStates[5][6] = true;

        //6
        segmentStates[6][0] = true; segmentStates[6][1] = true; segmentStates[6][2] = true;
        segmentStates[6][3] = true; segmentStates[6][4] = false; segmentStates[6][5] = true;
        segmentStates[6][6] = true;

        //7
        segmentStates[7][0] = true; segmentStates[7][1] = false; segmentStates[7][2] = false;
        segmentStates[7][3] = false; segmentStates[7][4] = true; segmentStates[7][5] = false;
        segmentStates[7][6] = true;

        //8
        segmentStates[8][0] = true; segmentStates[8][1] = true; segmentStates[8][2] = true;
        segmentStates[8][3] = true; segmentStates[8][4] = true; segmentStates[8][5] = true;
        segmentStates[8][6] = true;

        //9
        segmentStates[9][0] = true; segmentStates[9][1] = true; segmentStates[9][2] = true;
        segmentStates[9][3] = true; segmentStates[9][4] = true; segmentStates[9][5] = false;
        segmentStates[9][6] = true;

        //10(ALL OFF)
        segmentStates[10][0] = false; segmentStates[10][1] = false; segmentStates[10][2] = false;
        segmentStates[10][3] = false; segmentStates[10][4] = false; segmentStates[10][5] = false;
        segmentStates[10][6] = false;
    }

    public void setDisplayVal(int displayVal){
        if(displayVal > 10 || displayVal < 0){
            this.displayVal = 10;
        }
        else{
            this.displayVal = displayVal;
        }
    }

    public int getDisplayVal(){
        return this.displayVal;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.BLACK);
        Path path;
        float width = canvasWidth;
        float height = canvasHeight;
        width = width / this.width;
        height = height / this.height;
        canvas.scale(width, height);

        //Top(0)
        canvas.save();
        makePath(canvas, 0, segmentPoints);

        //Mid(1)
        canvas.restore();
        canvas.save();
        canvas.translate(0, 13);
        makePath(canvas, 1, segmentPoints);

        //Bottom(2)
        canvas.restore();
        canvas.save();
        canvas.translate(0, 26);
        makePath(canvas, 2, segmentPoints);

        //Top Left(3)
        canvas.restore();
        canvas.save();
        canvas.translate(6, 1);
        canvas.rotate(90);
        makePath(canvas, 3, segmentPoints2);

        //Top Right(4)
        canvas.restore();
        canvas.save();
        canvas.translate(20, 1);
        canvas.rotate(90);
        makePath(canvas, 4, segmentPoints2);

        //Bottom Left(5)
        canvas.restore();
        canvas.save();
        canvas.translate(6, 14);
        canvas.rotate(90);
        makePath(canvas, 5, segmentPoints2);

        //Bottom Right(6)
        canvas.restore();
        canvas.save();
        canvas.translate(20, 14);
        canvas.rotate(90);
        makePath(canvas, 6, segmentPoints2);
    }

    public void makePath(Canvas canvas, int seg, float[] segPoints) {
        Path path = new Path();
        setLayerType(this.LAYER_TYPE_SOFTWARE, null);
        path.moveTo(segPoints[0], segPoints[1]);
        path.lineTo(segPoints[2], segPoints[3]);
        path.lineTo(segPoints[4], segPoints[5]);
        path.lineTo(segPoints[6], segPoints[7]);
        path.lineTo(segPoints[8], segPoints[9]);
        path.lineTo(segPoints[10], segPoints[11]);
        path.close();
        canvas.clipPath(path);
        if(segmentStates[displayVal][seg]) {
            canvas.drawColor(onColor);
        }
        else{
            canvas.drawColor(offColor);
        }
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

        setMeasuredDimension((int)finalWidth, (int)finalHeight);
    }

    @Override
    public Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("instanceState", super.onSaveInstanceState());
        bundle.putInt("displayVal", displayVal);
        return bundle;
    }
    @Override
    public void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            this.displayVal = bundle.getInt("displayVal");
            state = bundle.getParcelable("instanceState");
        }
        super.onRestoreInstanceState(state);
    }
}
