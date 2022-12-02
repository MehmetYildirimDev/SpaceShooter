package com.my.spaceshooter;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

class Laser {

    //position and dimensions // konum ve boyut
    float xPosition, yPosition;  //bottom centre of the laser//alt kisminin merkezinin konumu
    float width, height;

    //laser pyscial characteristics //laser ozellikleri
    float movementSpeed;

    //graphics
    TextureRegion textureRegion;

    public Laser(float xCentre, float yBottom, float width, float height, float movementSpeed, TextureRegion textureRegion) {
        this.xPosition = xCentre - width/2;
        this.yPosition = yBottom;
        this.width = width;
        this.height = height;
        this.movementSpeed = movementSpeed;
        this.textureRegion = textureRegion;
    }



    public void draw(Batch batch) {
        batch.draw(textureRegion, xPosition, yPosition, width, height);
    }
}
