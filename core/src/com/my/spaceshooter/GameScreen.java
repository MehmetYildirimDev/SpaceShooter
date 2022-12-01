package com.my.spaceshooter;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

class GameScreen implements Screen {

    //screen
    private Camera camera;
    private Viewport viewport;

    //graphich
    private SpriteBatch batch;//haraketli grafik
    private TextureAtlas textureAtlas;
    private TextureRegion[] backgrounds;
    private float backgroundHeight;

    private TextureRegion playerShipTextureRegion, playerShieldTextureRegion,
            enemyShipTextureRegion, enemyShieldTextureRegion,
            playerLaserTextureRegion, enemyLaserTextureRegion;

    //timing
//    private  int backgroundOffset;//zamanla degisim icin. Cunku haraket edicek
    private float[] backgroundOffsets = {0,0,0,0};
    private float backgroundMaxScrollingSpeed;
    //world parameters
    private final int WORLD_WIDTH = 72;
    private final int WORLD_HEIGHT = 128;

    //game objects
    private Ship playerShip;
    private Ship enemyShip;

    GameScreen(){

        camera = new OrthographicCamera();//2d kameralar ortagrafik olur persfektif degil
        viewport = new StretchViewport(WORLD_WIDTH,WORLD_HEIGHT,camera);//esnek goruntu alani
//set up the texture atlas
        textureAtlas = new TextureAtlas("images.atlas");

        //setting up the background
        backgrounds = new TextureRegion[4];
        backgrounds[0] = textureAtlas.findRegion("Starscape00");
        backgrounds[1] = textureAtlas.findRegion("Starscape01");
        backgrounds[2] = textureAtlas.findRegion("Starscape02");
        backgrounds[3] = textureAtlas.findRegion("Starscape03");

        backgroundHeight = WORLD_HEIGHT * 2;
        backgroundMaxScrollingSpeed = (float) (WORLD_HEIGHT) / 4;

        //initialize texture regions
        playerShipTextureRegion = textureAtlas.findRegion("playerShip2_blue");
        enemyShipTextureRegion = textureAtlas.findRegion("enemyRed3");
        playerShieldTextureRegion = textureAtlas.findRegion("shield2");
        enemyShieldTextureRegion = textureAtlas.findRegion("shield1");
        enemyShieldTextureRegion.flip(false, true);

        playerLaserTextureRegion= textureAtlas.findRegion("laserBlue03");
        enemyLaserTextureRegion= textureAtlas.findRegion("laserRed03");

        //set up game objects
        playerShip = new Ship(2, 3, 10, 10,
                WORLD_WIDTH/2, WORLD_HEIGHT/4,
                playerShipTextureRegion, playerShieldTextureRegion);
        enemyShip = new Ship(2, 1, 10, 10,
                WORLD_WIDTH/2, WORLD_HEIGHT*3/4,
                enemyShipTextureRegion, enemyShieldTextureRegion);

        batch = new SpriteBatch();//goruntuyu olusturur
    }


    @Override
    public void render(float deltaTime) {

        batch.begin();

        //scrolling background
        renderBackground(deltaTime);

        //enemy ships
        enemyShip.draw(batch);

        //player ship
        playerShip.draw(batch);

        //lasers

        //explosions

        batch.end();
    }

    private void renderBackground(float deltaTime) {
//bazi bgler daha yavas ilerliyor daha guzel bir manzara icin
        //update position of background images
        backgroundOffsets[0] += deltaTime * backgroundMaxScrollingSpeed / 8;
        backgroundOffsets[1] += deltaTime * backgroundMaxScrollingSpeed / 4;
        backgroundOffsets[2] += deltaTime * backgroundMaxScrollingSpeed / 2;
        backgroundOffsets[3] += deltaTime * backgroundMaxScrollingSpeed;

        //draw each background layer
        for (int layer = 0; layer < backgroundOffsets.length; layer++) {
            if (backgroundOffsets[layer] > WORLD_HEIGHT) {
                backgroundOffsets[layer] = 0;
            }
            batch.draw(backgrounds[layer], 0, -backgroundOffsets[layer],
                    WORLD_WIDTH, backgroundHeight);
        }
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
        batch.dispose();
    }
}
