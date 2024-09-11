package com.example.marvel_game;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

    public class Thanos_View extends View{

        private Bitmap thanos[]= new Bitmap[2];

        private Bitmap backgroundImage;
        private int thanosX = 10;
        private int thanosY ;
        private int thanosSpeed;

        private int yellowX, yellowY, yellowSpeed = 16;
        private Paint yellowPaint = new Paint();

        private int greenX, greenY, greenSpeed = 20;
        private Paint greenPaint = new Paint();

        private int redX, redY, redSpeed = 25;
        private Paint redPaint = new Paint();

        private boolean touch = false;

        private int score, lifeThanos;
        private int canvasWidth, canvasHeight;
        private Paint scorePaint = new Paint();
        private Bitmap life[] = new Bitmap[2];

        public Thanos_View(Context context){
            super(context);

            thanos[0] = BitmapFactory.decodeResource(getResources(), R.drawable.th);
            thanos[1] = BitmapFactory.decodeResource(getResources(), R.drawable.th);

            backgroundImage = BitmapFactory.decodeResource(getResources(), R.drawable.stones_n);

            yellowPaint.setColor(Color.YELLOW);
            yellowPaint.setAntiAlias(false);

            greenPaint.setColor(Color.GREEN);
            greenPaint.setAntiAlias(false);

            redPaint.setColor(Color.RED);
            redPaint.setAntiAlias(false);

            scorePaint.setColor(Color.GREEN);
            scorePaint.setTextSize(70);
            scorePaint.setTypeface(Typeface.DEFAULT_BOLD);
            scorePaint.setAntiAlias(true);

            life[0] = BitmapFactory.decodeResource(getResources(), R.drawable.hulke);
            life[1] = BitmapFactory.decodeResource(getResources(), R.drawable.sbe);

            thanosY = 660;
            score = 0;
            lifeThanos = 3;
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            canvasWidth = canvas.getWidth();
            canvasHeight = canvas.getHeight();

            canvas.drawBitmap(backgroundImage, 0,0,null);

            int minThanosY = thanos[0].getHeight();
            int maxThanosY = canvasHeight - thanos[0].getHeight() * 3;
            thanosY = thanosY + thanosSpeed;

            if (thanosY < minThanosY){
                thanosY = minThanosY;
            }
            if (thanosY > maxThanosY){
                thanosY = maxThanosY;
            }
            thanosSpeed = thanosSpeed + 2;
            if (touch)
            {
                canvas.drawBitmap(thanos[1], thanosX, thanosY, null);
                touch = false;
            }
            else {
                canvas.drawBitmap(thanos[0], thanosX, thanosY, null);
            }

            yellowX = yellowX - yellowSpeed;
            if (hitBallChecker(yellowX, yellowY)){
                score+=10;
                yellowX = -100;
            }
            if (yellowX < 0){
                yellowX = canvasWidth + 21;
                yellowY = (int) Math.floor(Math.random() * (maxThanosY - minThanosY)) + minThanosY;
            }
            canvas.drawCircle(yellowX, yellowY, 28,yellowPaint);

            //GREEN BALL
            greenX = greenX - greenSpeed;
            if (hitBallChecker(greenX, greenY)){
                score+=15;
                greenX = -100;
            }
            if (greenX < 0){
                greenX = canvasWidth + 21;
                greenY = (int) Math.floor(Math.random() * (maxThanosY - minThanosY)) + minThanosY;
            }
            canvas.drawCircle(greenX, greenY, 25,greenPaint);

            //RED BALL
            redX = redX - redSpeed;
            if (hitBallChecker(redX, redY)){

                redX = -100;
                lifeThanos--;
                if (lifeThanos == 0){
                    Toast.makeText(getContext(), "Game Over", Toast.LENGTH_SHORT).show();
                    Intent gameOverIntent = new Intent(getContext(), GameOver.class);
                    gameOverIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    gameOverIntent.putExtra("score", score);
                    getContext().startActivity(gameOverIntent);
                }
            }
            if (redX < 0){
                redX = canvasWidth + 21;
                redY = (int) Math.floor(Math.random() * (maxThanosY - minThanosY)) + minThanosY;
            }
            canvas.drawCircle(redX, redY, 20,redPaint);

            canvas.drawText("Score : " + score, 20,60, scorePaint);
            for (int i = 0;i<3; i++){
                int x = (int) (580 + life[0].getWidth() * 1.5 * i);
                int y = 30;
                if (i<lifeThanos){
                    canvas.drawBitmap(life[0], x, y, null);
                }
                else {
                    canvas.drawBitmap(life[1], x,y, null);
                }

            }


        }

        public boolean hitBallChecker(int x, int y){
            if (thanosX < x && x<(thanosX + thanos[0].getWidth()) && thanosY<y && y<(thanosY + thanos[0].getHeight()))
            {
                return  true;
            }
            return false;
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN)
            {
                touch = true;
                thanosSpeed = -22;
            }
            return true;
        }
    }

