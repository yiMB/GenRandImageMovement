package com.yi.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class GenRandImageMovement extends ApplicationAdapter {
	SpriteBatch batch;
	TextureCookie textureCookie;

	@Override
	public void create () {
		batch = new SpriteBatch();
		textureCookie = new TextureCookie();
	}

	@Override
	public void dispose() {
		batch.dispose();
		textureCookie.textureCookie.dispose();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		for(Rectangle rectangle : textureCookie.arrRectCookieBorders){
			batch.draw(textureCookie.textureCookie, rectangle.x, rectangle.y, rectangle.width, rectangle.height);
		}
		batch.end();
		textureCookie.update();
	}
}
