package com.my.spaceshooter;


import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

class EnemyShip extends Ship {

    public EnemyShip(float xCentre, float yCentre,
                     float width, float height,
                     float movementSpeed, int shield,
                     float laserWidth, float laserHeight,
                     float laserMovementSpeed, float timeBetweenShots,
                     TextureRegion shipTextureRegion,
                     TextureRegion shieldTextureRegion,
                     TextureRegion laserTextureRegion) {
        super(xCentre, yCentre, width, height, movementSpeed, shield, laserWidth, laserHeight, laserMovementSpeed, timeBetweenShots, shipTextureRegion, shieldTextureRegion, laserTextureRegion);
    }

    @Override
    public Laser[] fireLasers() {
        Laser[] laser = new Laser[2];
        laser[0] = new Laser(boundingBox.x + boundingBox.width * 0.18f, boundingBox.y - laserHeight,
                laserWidth, laserHeight,
                laserMovementSpeed, laserTextureRegion);
        laser[1] = new Laser(boundingBox.x + boundingBox.width * 0.82f, boundingBox.y  - laserHeight,
                laserWidth, laserHeight,
                laserMovementSpeed, laserTextureRegion);

        timeSinceLastShot = 0;

        return laser;
    }

    @Override
    public void draw(Batch batch) {
        batch.draw(shipTextureRegion, boundingBox.x,boundingBox.y,boundingBox.width,boundingBox.height);
        if (shield > 0) {
            batch.draw(shieldTextureRegion, boundingBox.x, boundingBox.y-boundingBox.height*0.2f,boundingBox.width,boundingBox.height);
        }
    }
}
