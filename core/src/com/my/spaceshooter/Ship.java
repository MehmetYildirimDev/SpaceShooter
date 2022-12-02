package com.my.spaceshooter;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

abstract class Ship {
    //ship characteristics //Gemi ozellikleri
    float movementSpeed;  //world units per second //saniye basina haraket
    int shield;//kalkan

    //position & dimension
    float xPosition, yPosition; //lower-left corner
    float width, height;
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

        this.xPosition = xCentre - width / 2;
        this.yPosition = yCentre - height / 2;
        this.width = width;
        this.height = height;
        this.laserWidth = laserWidth;
        this.boundingBox = new Rectangle(xPosition,yPosition,width,height);

        this.laserHeight = laserHeight;
        this.laserMovementSpeed = laserMovementSpeed;
        this.timeBetweenShots = timeBetweenShots;
        this.shipTextureRegion = shipTextureRegion;
        this.shieldTextureRegion = shieldTextureRegion;
        this.laserTextureRegion = laserTextureRegion;
    }

    public void update(float deltaTime) {
        boundingBox.set(xPosition,yPosition,width,height);//hitboxu guncelliyor
        timeSinceLastShot += deltaTime;
    }

    public boolean canFireLaser() {
        return (timeSinceLastShot - timeBetweenShots >= 0);
    }

    public abstract Laser[] fireLasers();

    public boolean intersects(Rectangle otherRectangle) {
        return boundingBox.overlaps(otherRectangle);
    }//carpism

    public void hit(Laser laser) {
        if (shield > 0) {
            shield--;
        }
    }

    public void draw(Batch batch) {//cizdiriyoruz
        batch.draw(shipTextureRegion, xPosition, yPosition, width, height);
        if (shield > 0) {//kirilinca gozukmeyecek
            batch.draw(shieldTextureRegion, xPosition, yPosition, width, height);
        }
    }

}
