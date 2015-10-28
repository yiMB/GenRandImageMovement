package com.yi.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

/**
 */
public class TextureCookie {
    Texture textureCookie;
    Array<Rectangle> arrRectCookieBorders;
    float screenWidth, screenHeight, textureWidth, textureHeight;
    /**
     * keep track when did last Rectangle was added to Rectangle array
     */
    long lastRectCreateTime;

    Vector2 vector2CookieXY;

    public TextureCookie(){
        textureCookie = new Texture(Gdx.files.internal("cookie.png"));
        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();
        textureWidth = textureCookie.getWidth();
        textureHeight = textureCookie.getHeight();
        arrRectCookieBorders = new Array<Rectangle>();
        addRectangle();
        vector2CookieXY = new Vector2(0, 0);
        vector2CookieXY.y = 200f;
    }

    /**
     * Rectangle
     * create rectangles of size of texture size on random x-axis position from 0 to screen width - texture width, on y-axis screen height
     * store to rectangles Array
     * keep a timestamp when rectangle create
     * in this game each rectangle supply cookie image x and y position
	 */
//    public void addRectangle() {
//        Rectangle rectangle = new Rectangle();
//        rectangle.x = MathUtils.random(0, screenWidth - textureWidth);
//        rectangle.y = screenHeight;
//        rectangle.width = textureWidth;
//        rectangle.height = textureHeight;
//        arrRectCookieBorders.add(rectangle);
//        lastRectCreateTime = TimeUtils.nanoTime();
//    }
    public void addRectangle() {
        Rectangle rectangle = new Rectangle();
        rectangle.x = MathUtils.random(0, screenWidth - textureWidth);
        rectangle.y = screenHeight;
        rectangle.width = MathUtils.random(textureWidth / 2, textureWidth * 2);
        rectangle.height = MathUtils.random(textureHeight / 2, textureHeight * 2);
        arrRectCookieBorders.add(rectangle);
        lastRectCreateTime = TimeUtils.nanoTime();
    }

    /**
     * called at render
     * wait 3 second be for add new Rectangle to Rectangle array
     * 3000000000l is a long last character is letter l
     * longer it wait less image generate to screen
     *
     * move Rectangle from the Rectangle array down as time pass,
     * when a Rectangle reach bottom of screen remove Rectangle from the Rectangle array
     *
     * DeltaTime is time spend between 2 frame, so it is differ from every phone and emulator,
     * we use it to set the speed of the down movement
     *
     * can use Array or Iterator to do the job
     */
    public void update(){
        if(TimeUtils.nanoTime() - lastRectCreateTime > 1000000000l){
            addRectangle();
        }
        Gdx.app.log("MyTag", "rectangles.size = " + arrRectCookieBorders.size);
        for(int i=0; i < arrRectCookieBorders.size; i++){
            arrRectCookieBorders.get(i).y -= vector2CookieXY.y * Gdx.graphics.getDeltaTime();
            if(arrRectCookieBorders.get(i).y + textureHeight < 0){
                arrRectCookieBorders.removeIndex(i);
            }
        }
    }
}
