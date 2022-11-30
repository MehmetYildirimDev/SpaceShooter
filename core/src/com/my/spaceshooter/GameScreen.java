package com.my.spaceshooter;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

class GameScreen implements Screen {

    //screen
    private Camera camera;
    private Viewport viewport;

    //graphich
    private SpriteBatch batch;//haraketli grafik
    private Texture background;

    //timing
    private  int backgroundOffset;//zamanla degisim icin. Cunku haraket edicek

    //world parameters
    private final int WORLD_WIDTH = 72;
    private final int WORLD_HEIGHT = 128;

    GameScreen(){

        camera = new OrthographicCamera();//2d kameralar ortagrafik olur persfektif degil
        viewport = new StretchViewport(WORLD_WIDTH,WORLD_HEIGHT,camera);//esnek goruntu alani

        background = new Texture("darkPurpleStarscape.png");
        backgroundOffset = 0;

        batch = new SpriteBatch();//goruntuyu olusturur
    }


    @Override
    public void render(float deltaTime) {

        batch.begin();

        //scrolling background
        backgroundOffset++;
        if (backgroundOffset % WORLD_HEIGHT ==0)
            backgroundOffset = 0;


        batch.draw(background,0,-backgroundOffset,WORLD_WIDTH,WORLD_HEIGHT);//arka plani ciziyo
        batch.draw(background,0,-backgroundOffset+WORLD_HEIGHT,WORLD_WIDTH,WORLD_HEIGHT);//arkaplanlar uctan uca kayarak


        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width,height,true);//camera merkezde
        batch.setProjectionMatrix(camera.combined);//ekranda dogru gorunmesi icin

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void show() {

    }

    @Override
    public void dispose() {

    }
}
