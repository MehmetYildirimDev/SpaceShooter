package com.my.spaceshooter;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class SpaceShooterGame extends Game {

	GameScreen gameScreen;

	@Override
	public void create () {
	gameScreen = new GameScreen();
	setScreen(gameScreen);//oyun ekranimizi olusturacak
	}

	@Override
	public void dispose() {//ekrani kapamak icin
		gameScreen.dispose();
	}

	@Override
	public void render() {//cizimleri yapar
		super.render();
	}

	@Override
	public void resize(int width, int height) {//ekranin boyutlarini ayarlar
		gameScreen.resize(width, height);
	}


}
