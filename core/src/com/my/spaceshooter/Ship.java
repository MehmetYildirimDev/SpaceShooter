package com.my.spaceshooter;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Ship {
    //ship characteristics
    float movementSpeed;  //world units per second
    int shield;

    //position & dimension
    float xPosition, yPosition; //lower-left corner
    float width, height;

    //graphics
    TextureRegion shipTexture, shieldTexture;

    public Ship(float movementSpeed, int shield,
                float width, float height,
                float xCentre, float yCentre,
                TextureRegion shipTexture, TextureRegion shieldTexture) {
        this.movementSpeed = movementSpeed;
        this.shield = shield;
        this.xPosition = xCentre - width / 2;
        this.yPosition = yCentre - height / 2;
        this.width = width;
        this.height = height;
        this.shipTexture = shipTexture;
        this.shieldTexture = shieldTexture;
    }


    public void draw(Batch batch) {
        batch.draw(shipTexture, xPosition, yPosition, width, height);
        if (shield > 0) {
            batch.draw(shieldTexture, xPosition, yPosition, width, height);
        }
    }

}
