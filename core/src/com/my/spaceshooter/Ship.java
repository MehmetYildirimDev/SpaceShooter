package com.my.spaceshooter;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

abstract class Ship {
    //ship characteristics //Gemi ozellikleri
    float movementSpeed;  //world units per second //saniye basina haraket
    int shield;//kalkan

    //position & dimension
    Rectangle boundingBox;

    //graphics
    TextureRegion shipTextureRegion, shieldTextureRegion, laserTextureRegion;

    //laser information
    float laserWidth, laserHeight;
    float laserMovementSpeed;
    float timeBetweenShots;
    float timeSinceLastShot = 0;

    public Ship(float xCentre, float yCentre,
                float width, float height,
                float movementSpeed, int shield,
                float laserWidth, float laserHeight, float laserMovementSpeed,
                float timeBetweenShots,
                TextureRegion shipTextureRegion, TextureRegion shieldTextureRegion,
                TextureRegion laserTextureRegion) {
        this.movementSpeed = movementSpeed;
        this.shield = shield;

        this.laserWidth = laserWidth;
        this.boundingBox = new Rectangle(xCentre - width / 2,yCentre - height / 2,width,height);

        this.laserHeight = laserHeight;
        this.laserMovementSpeed = laserMovementSpeed;
        this.timeBetweenShots = timeBetweenShots;
        this.shipTextureRegion = shipTextureRegion;
        this.shieldTextureRegion = shieldTextureRegion;
        this.laserTextureRegion = laserTextureRegion;
    }

    public void update(float deltaTime) {
        timeSinceLastShot += deltaTime;
    }

    public boolean canFireLaser() {
        return (timeSinceLastShot - timeBetweenShots >= 0);
    }

    public abstract Laser[] fireLasers();

    public boolean intersects(Rectangle otherRectangle) {
        return boundingBox.overlaps(otherRectangle);
    }//carpisma kontrolu

    public void hit(Laser laser) {
        if (shield > 0) {
            shield--;
        }
    }

    public void draw(Batch batch) {//cizdiriyoruz
        batch.draw(shipTextureRegion,boundingBox.x,boundingBox.y,boundingBox.width,boundingBox.height);
        if (shield > 0) {//kirilinca gozukmeyecek
            batch.draw(shieldTextureRegion, boundingBox.x,boundingBox.y,boundingBox.width,boundingBox.height);
        }
    }

}
