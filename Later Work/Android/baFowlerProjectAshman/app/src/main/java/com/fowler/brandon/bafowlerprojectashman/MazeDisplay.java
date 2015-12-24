//Name: Brandon Fowler
//Class: CSCD372
//Quarter: Fall 2015
//Assignment: Ashman Project

package com.fowler.brandon.bafowlerprojectashman;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MazeDisplay extends View{

    final float width = 14;
    final float height = 14;
    final float aspectRatio = width/height;
    int canvasWidth;
    int canvasHeight;
    final int wallColor = Color.BLACK;
    final int ghostColor = Color.rgb(255,0,0);
    final int ashColor = Color.rgb(0,255,0);
    final int cellColor = Color.argb(150, 0, 0, 255);
    final int cakeColor = Color.WHITE;
    TextView status;
    Maze maze;
    boolean altTick = false;
    boolean paused = true;
    boolean end = false;
    Handler clockHandler = new Handler() ;
    Runnable clockTimer = new Runnable() {
        @Override
        public void run() {
            handleTick();
            clockHandler.postDelayed(this, 70);
            checkForGameEnd();
        }
    } ;
    MediaPlayer mp;
    MediaPlayer.OnCompletionListener releaseOnFinishListener = new MediaPlayer.OnCompletionListener() {
        public void onCompletion(MediaPlayer mp) {
            mp.release();
            mp = null;
        }
    };

    public MazeDisplay(Context context){
        super(context);
        this.maze = new Maze(1);
    }

    public MazeDisplay(Context context, AttributeSet attrs){
        super(context, attrs);
        this.maze = new Maze(1);
    }

    public MazeDisplay(Context context,AttributeSet attrs, int defStyle){
        super(context,attrs, defStyle);
        this.maze = new Maze(1);
    }

    public void setMaze(Maze maze){
        this.maze = maze;
    }

    public Maze getMaze(){
        return this.maze;
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
        Float transCenter = new Float(0.5);
        Paint p = new Paint();
        drawMaze(p, canvas, transCenter);
        drawActors(p, canvas, transCenter);
        status.setText("Level = "+maze.getLevel()+"           Cakes Left = "+maze.getCakes());
    }

    public void drawMaze(Paint p, Canvas canvas, Float transCenter){
        Path path;
        Float cakeRad = new Float(0.2);
        for(int i = 0; i < maze.getGrid().length; i++) {
            for (int j = 0; j < maze.getGrid()[i].length; j++) {
                if(maze.getGrid()[i][j] == 1){
                    p.setColor(wallColor);
                    p.setStyle(Paint.Style.FILL);
                    path = new Path();
                    path.moveTo(j, i);
                    path.lineTo(j, i+1);
                    path.lineTo(j+1, i+1);
                    path.lineTo(j+1, i);
                    path.lineTo(j,i);
                    canvas.drawPath(path, p);
                }
                else {
                    p.setColor(cellColor);
                    p.setStyle(Paint.Style.FILL);
                    path = new Path();
                    path.moveTo(j, i);
                    path.lineTo(j, i+1);
                    path.lineTo(j+1, i+1);
                    path.lineTo(j+1, i);
                    path.lineTo(j,i);
                    canvas.drawPath(path, p);
                    if(maze.getGrid()[i][j] == 2){
                        canvas.save();
                        canvas.translate(transCenter, transCenter);
                        p.setColor(cakeColor);
                        p.setStyle(Paint.Style.FILL);
                        canvas.drawCircle(j, i, cakeRad, p);
                        canvas.restore();
                    }
                }
            }
        }
    }

    public void drawActors(Paint p, Canvas canvas, Float transCenter){
        Float ashRad = new Float(0.4);

        canvas.save();
        canvas.translate(transCenter, transCenter);
        p.setColor(ashColor);
        p.setStyle(Paint.Style.FILL);
        canvas.drawCircle(maze.getAsh().getSubCol(), maze.getAsh().getSubRow(), ashRad, p);
        canvas.restore();

        if(altTick){
            drawMouth(p, canvas, transCenter, ashRad, maze.getAsh().getSubRow(), maze.getAsh().getSubCol());
        }

        canvas.save();
        canvas.translate(transCenter, transCenter);
        p.setColor(ghostColor);
        p.setStyle(Paint.Style.FILL);
        canvas.drawCircle(maze.getG1().getSubCol(), maze.getG1().getSubRow(), ashRad, p);
        canvas.restore();

        canvas.save();
        canvas.translate(transCenter, transCenter);
        p.setColor(ghostColor);
        p.setStyle(Paint.Style.FILL);
        canvas.drawCircle(maze.getG2().getSubCol(), maze.getG2().getSubRow(), ashRad, p);
        canvas.restore();

        canvas.save();
        canvas.translate(transCenter, transCenter);
        p.setColor(ghostColor);
        p.setStyle(Paint.Style.FILL);
        canvas.drawCircle(maze.getG3().getSubCol(), maze.getG3().getSubRow(), ashRad, p);
        canvas.restore();

        if(maze.getLevel() == 2){
            canvas.save();
            canvas.translate(transCenter, transCenter);
            p.setColor(ghostColor);
            p.setStyle(Paint.Style.FILL);
            canvas.drawCircle(maze.getG4().getSubCol(), maze.getG4().getSubRow(), ashRad, p);
            canvas.restore();

            canvas.save();
            canvas.translate(transCenter, transCenter);
            p.setColor(ghostColor);
            p.setStyle(Paint.Style.FILL);
            canvas.drawCircle(maze.getG5().getSubCol(), maze.getG5().getSubRow(), ashRad, p);
            canvas.restore();
        }
    }

    public void drawMouth(Paint p, Canvas canvas, float transCenter, float rad, float row, float col){
        Path path;
        int color = Color.argb(150, 0, 0, 255);
        if(maze.getAsh().getDirection() == 1) {
            canvas.save();
            canvas.translate(transCenter, transCenter);
            p.setColor(Color.WHITE);
            p.setStyle(Paint.Style.FILL);
            path = new Path();
            path.moveTo(col, row);
            path.lineTo(col - rad, row - rad);
            path.lineTo(col + rad, row - rad);
            path.lineTo(col, row);
            canvas.drawPath(path, p);
            canvas.restore();

            canvas.save();
            canvas.translate(transCenter, transCenter);
            p.setColor(cellColor);
            p.setStyle(Paint.Style.FILL);
            path = new Path();
            path.moveTo(col, row);
            path.lineTo(col - rad, row - rad);
            path.lineTo(col + rad, row - rad);
            path.lineTo(col, row);
            canvas.drawPath(path, p);
            canvas.restore();
        }
        else if(maze.getAsh().getDirection() == 2) {
            canvas.save();
            canvas.translate(transCenter, transCenter);
            p.setColor(Color.WHITE);
            p.setStyle(Paint.Style.FILL);
            path = new Path();
            path.moveTo(col, row);
            path.lineTo(col + rad, row + rad);
            path.lineTo(col + rad, row - rad);
            path.lineTo(col, row);
            canvas.drawPath(path, p);
            canvas.restore();

            canvas.save();
            canvas.translate(transCenter, transCenter);
            p.setColor(cellColor);
            p.setStyle(Paint.Style.FILL);
            path = new Path();
            path.moveTo(col, row);
            path.lineTo(col + rad, row + rad);
            path.lineTo(col + rad, row - rad);
            path.lineTo(col, row);
            canvas.drawPath(path, p);
            canvas.restore();
        }
        else if(maze.getAsh().getDirection() == 3) {
            canvas.save();
            canvas.translate(transCenter, transCenter);
            p.setColor(Color.WHITE);
            p.setStyle(Paint.Style.FILL);
            path = new Path();
            path.moveTo(col, row);
            path.lineTo(col + rad, row + rad);
            path.lineTo(col - rad, row + rad);
            path.lineTo(col, row);
            canvas.drawPath(path, p);
            canvas.restore();

            canvas.save();
            canvas.translate(transCenter, transCenter);
            p.setColor(cellColor);
            p.setStyle(Paint.Style.FILL);
            path = new Path();
            path.moveTo(col, row);
            path.lineTo(col + rad, row + rad);
            path.lineTo(col - rad, row + rad);
            path.lineTo(col, row);
            canvas.drawPath(path, p);
            canvas.restore();
        }
        else if(maze.getAsh().getDirection() == 4) {
            canvas.save();
            canvas.translate(transCenter, transCenter);
            p.setColor(Color.WHITE);
            p.setStyle(Paint.Style.FILL);
            path = new Path();
            path.moveTo(col, row);
            path.lineTo(col - rad, row - rad);
            path.lineTo(col - rad, row + rad);
            path.lineTo(col, row);
            canvas.drawPath(path, p);
            canvas.restore();

            canvas.save();
            canvas.translate(transCenter, transCenter);
            p.setColor(cellColor);
            p.setStyle(Paint.Style.FILL);
            path = new Path();
            path.moveTo(col, row);
            path.lineTo(col - rad, row - rad);
            path.lineTo(col - rad, row + rad);
            path.lineTo(col, row);
            canvas.drawPath(path, p);
            canvas.restore();
        }
    }

    public int getLevel(){
        return maze.getLevel();
    }

    public int getCakes(){
        return maze.getCakes();
    }

    public void setStatus(TextView status){
        this.status = status;
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
        String[][] grid = new String[14][14];
        Bundle bundle = new Bundle();
        bundle.putParcelable("instanceState", super.onSaveInstanceState());
        bundle.putBoolean("altTick", altTick);
        bundle.putBoolean("end", end);
        bundle.putInt("level", maze.getLevel());
        for(int i = 0; i < maze.getGrid().length; i++){
            for(int j = 0; j < maze.getGrid()[i].length; j++){
                grid[i][j] = ""+maze.getGrid()[i][j];
            }
        }
        bundle.putSerializable("grid", grid);
        bundle.putInt("cakes", maze.getCakes());
        bundle.putInt("ashd", maze.getAsh().getDirection());
        bundle.putInt("ashr", maze.getAsh().getRow());
        bundle.putInt("ashc", maze.getAsh().getCol());
        bundle.putFloat("ashsr", maze.getAsh().getSubRow());
        bundle.putFloat("ashsc", maze.getAsh().getSubCol());
        bundle.putInt("ashwd", maze.getAsh().getWaitingDirection());
        bundle.putBoolean("ashds", maze.getAsh().getDirectionSet());
        bundle.putInt("g1d", maze.getG1().getDirection());
        bundle.putInt("g1r", maze.getG1().getRow());
        bundle.putInt("g1c", maze.getG1().getCol());
        bundle.putFloat("g1sr", maze.getG1().getSubRow());
        bundle.putFloat("g1sc", maze.getG1().getSubCol());
        bundle.putInt("g2d", maze.getG2().getDirection());
        bundle.putInt("g2r", maze.getG2().getRow());
        bundle.putInt("g2c", maze.getG2().getCol());
        bundle.putFloat("g2sr", maze.getG2().getSubRow());
        bundle.putFloat("g2sc", maze.getG2().getSubCol());
        bundle.putInt("g3d", maze.getG3().getDirection());
        bundle.putInt("g3r", maze.getG3().getRow());
        bundle.putInt("g3c", maze.getG3().getCol());
        bundle.putFloat("g3sr", maze.getG3().getSubRow());
        bundle.putFloat("g3sc", maze.getG3().getSubCol());
        if(maze.getLevel() == 2){
            bundle.putInt("g4d", maze.getG4().getDirection());
            bundle.putInt("g4r", maze.getG4().getRow());
            bundle.putInt("g4c", maze.getG4().getCol());
            bundle.putFloat("g4sr", maze.getG4().getSubRow());
            bundle.putFloat("g4sc", maze.getG4().getSubCol());
            bundle.putInt("g5d", maze.getG5().getDirection());
            bundle.putInt("g5r", maze.getG5().getRow());
            bundle.putInt("g5c", maze.getG5().getCol());
            bundle.putFloat("g5sr", maze.getG5().getSubRow());
            bundle.putFloat("g5sc", maze.getG5().getSubCol());
        }
        return bundle;
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            String[][] Sgrid;
            int[][] Igrid = new int[14][14];
            Bundle bundle = (Bundle) state;
            state = bundle.getParcelable("instanceState");
            maze = new Maze(bundle.getInt("level"));
            altTick = bundle.getBoolean("altTick");
            end = bundle.getBoolean("end");
            maze.setLevel(bundle.getInt("level"));
            Sgrid = (String[][])bundle.getSerializable("grid");
            for(int i = 0; i < Sgrid.length; i++){
                for(int j = 0; j < Sgrid[i].length; j++){
                    Igrid[i][j] = Integer.parseInt(Sgrid[i][j]);
                }
            }
            maze.setGrid(Igrid);
            maze.setCakes(bundle.getInt("cakes"));
            maze.getAsh().setDirection(bundle.getInt("ashd"));
            maze.getAsh().setRow(bundle.getInt("ashr"));
            maze.getAsh().setCol(bundle.getInt("ashc"));
            maze.getAsh().setSubRow(bundle.getFloat("ashsr"));
            maze.getAsh().setSubCol(bundle.getFloat("ashsc"));
            maze.getAsh().setWaitingDirection(bundle.getInt("ashwd"));
            maze.getAsh().setDirectionSet(bundle.getBoolean("ashds"));
            maze.getG1().setDirection(bundle.getInt("g1d"));
            maze.getG1().setRow(bundle.getInt("g1r"));
            maze.getG1().setCol(bundle.getInt("g1c"));
            maze.getG1().setSubRow(bundle.getFloat("g1sr"));
            maze.getG1().setSubCol(bundle.getFloat("g1sc"));
            maze.getG2().setDirection(bundle.getInt("g2d"));
            maze.getG2().setRow(bundle.getInt("g2r"));
            maze.getG2().setCol(bundle.getInt("g2c"));
            maze.getG2().setSubRow(bundle.getFloat("g2sr"));
            maze.getG2().setSubCol(bundle.getFloat("g2sc"));
            maze.getG3().setDirection(bundle.getInt("g3d"));
            maze.getG3().setRow(bundle.getInt("g3r"));
            maze.getG3().setCol(bundle.getInt("g3c"));
            maze.getG3().setSubRow(bundle.getFloat("g3sr"));
            maze.getG3().setSubCol(bundle.getFloat("g3sc"));
            if(maze.getLevel() == 2) {
                maze.getG4().setDirection(bundle.getInt("g4d"));
                maze.getG4().setRow(bundle.getInt("g4r"));
                maze.getG4().setCol(bundle.getInt("g4c"));
                maze.getG4().setSubRow(bundle.getFloat("g4sr"));
                maze.getG4().setSubCol(bundle.getFloat("g4sc"));
                maze.getG5().setDirection(bundle.getInt("g5d"));
                maze.getG5().setRow(bundle.getInt("g5r"));
                maze.getG5().setCol(bundle.getInt("g5c"));
                maze.getG5().setSubRow(bundle.getFloat("g5sr"));
                maze.getG5().setSubCol(bundle.getFloat("g5sc"));
            }
            state = bundle.getParcelable("instanceState");
            status.setText("Level = "+maze.getLevel()+"           Cakes Left = "+maze.getCakes());
            this.invalidate();
        }

        super.onRestoreInstanceState(state);
    }

    public void handleTick(){
        if(maze.getLevel() == 2 || altTick){
            maze.moveGhosts();
            if(altTick) {
                altTick = false;
            }
            else{
                altTick = true;
            }
        }
        else{
            altTick = true;
        }
        maze.moveAsh();
        boolean eaten = maze.eatCake();
        if(eaten){
            AudioManager am = ((AudioManager)(getContext().getSystemService(getContext().AUDIO_SERVICE)));
            mp = MediaPlayer.create(getContext().getApplicationContext(), R.raw.eatcake);
            mp.setOnCompletionListener(releaseOnFinishListener);
            mp.start();
        }
        status.setText("Level = " + maze.getLevel() + "           Cakes Left = "+maze.getCakes());
        this.invalidate();
    }

    public void pauseOrGo(){
        if(paused){
            go();
        }
        else{
            pause();
        }
    }

    public void pause(){
        if(!paused){
            clockHandler.removeCallbacks(clockTimer);
            paused = true;
        }
    }

    public void go(){
        clockHandler.postDelayed(clockTimer, 70);
        paused = false;
    }

    public void cheat() {
        maze.cheat();
        status.setText("Level = " + maze.getLevel() + "           Cakes Left = " + maze.getCakes());
        this.invalidate();
    }

    public void checkForGameEnd(){
        if(maze.getCakes() == 0){
            pause();
            if(maze.level == 1) {
                AudioManager am = ((AudioManager)(getContext().getSystemService(getContext().AUDIO_SERVICE)));
                mp = MediaPlayer.create(getContext().getApplicationContext(), R.raw.intermission);
                mp.setOnCompletionListener(releaseOnFinishListener);
                mp.start();

                Toast.makeText(getContext().getApplicationContext(), "Great job! You have completed level one! Prepare for level two!", Toast.LENGTH_LONG).show();
                maze = new Maze(2);
                status.setText("Level = " +maze.getLevel()+"           Cakes Left = "+maze.getCakes());
                this.invalidate();
            }
            else if(maze.level == 2){
                AudioManager am = ((AudioManager)(getContext().getSystemService(getContext().AUDIO_SERVICE)));
                mp = MediaPlayer.create(getContext().getApplicationContext(), R.raw.won);
                mp.setOnCompletionListener(releaseOnFinishListener);
                mp.start();

                Toast.makeText(getContext().getApplicationContext(), "Great job! You Win!", Toast.LENGTH_LONG).show();
                end = true;
            }
        }
        else{
            checkForLostGame();
        }
    }

    public void checkForLostGame(){
        if(maze.getAsh().getRow() == maze.G1.getRow() && maze.getAsh().getCol() == maze.G1.getCol()){
            pause();
            Toast.makeText(getContext().getApplicationContext(), "Oops! You Lost!", Toast.LENGTH_LONG).show();
            end = true;

            AudioManager am = ((AudioManager)(getContext().getSystemService(getContext().AUDIO_SERVICE)));
            mp = MediaPlayer.create(getContext().getApplicationContext(), R.raw.death);
            mp.setOnCompletionListener(releaseOnFinishListener);
            mp.start();
        }
        else if(maze.getAsh().getRow() == maze.G2.getRow() && maze.getAsh().getCol() == maze.G2.getCol()){
            pause();
            Toast.makeText(getContext().getApplicationContext(), "Oops! You Lost!", Toast.LENGTH_LONG).show();
            end = true;

            AudioManager am = ((AudioManager)(getContext().getSystemService(getContext().AUDIO_SERVICE)));
            mp = MediaPlayer.create(getContext().getApplicationContext(), R.raw.death);
            mp.setOnCompletionListener(releaseOnFinishListener);
            mp.start();
        }
        else if(maze.getAsh().getRow() == maze.G3.getRow() && maze.getAsh().getCol() == maze.G3.getCol()){
            pause();
            Toast.makeText(getContext().getApplicationContext(), "Oops! You Lost!", Toast.LENGTH_LONG).show();
            end = true;

            AudioManager am = ((AudioManager)(getContext().getSystemService(getContext().AUDIO_SERVICE)));
            mp = MediaPlayer.create(getContext().getApplicationContext(), R.raw.death);
            mp.setOnCompletionListener(releaseOnFinishListener);
            mp.start();
        }
        else if(maze.getLevel() == 2){
            if(maze.getAsh().getRow() == maze.G4.getRow() && maze.getAsh().getCol() == maze.G4.getCol()){
                pause();
                Toast.makeText(getContext().getApplicationContext(), "Oops! You Lost!", Toast.LENGTH_LONG).show();
                end = true;

                AudioManager am = ((AudioManager)(getContext().getSystemService(getContext().AUDIO_SERVICE)));
                mp = MediaPlayer.create(getContext().getApplicationContext(), R.raw.death);
                mp.setOnCompletionListener(releaseOnFinishListener);
                mp.start();
            }
            else if(maze.getAsh().getRow() == maze.G5.getRow() && maze.getAsh().getCol() == maze.G5.getCol()){
                pause();
                Toast.makeText(getContext().getApplicationContext(), "Oops! You Lost!", Toast.LENGTH_LONG).show();
                end = true;

                AudioManager am = ((AudioManager)(getContext().getSystemService(getContext().AUDIO_SERVICE)));
                mp = MediaPlayer.create(getContext().getApplicationContext(), R.raw.death);
                mp.setOnCompletionListener(releaseOnFinishListener);
                mp.start();
            }
        }
    }

    public boolean getEnd(){
        return this.end;
    }

    public void reset(){
        end = false;
        maze = new Maze(1);
        status.setText("Level = " +maze.getLevel()+"           Cakes Left = " + maze.getCakes());
        this.invalidate();
        go();
    }

}
