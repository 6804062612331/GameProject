/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GameProject;
import java.awt.*;
import java.net.URL;
import javax.swing.*;
/**
 *
 * @author com
 */
public class Meteorite extends Square{
    URL URL;
    boolean isFallen = false,isHit = false;
    private int damageCooldown = 0;
    @Override
    public void paint(Graphics g){
        if(!isFallen){
            URL= getClass().getResource("/image/objects/Meteorite.png");
            Image Img = new ImageIcon(URL).getImage();
            g.drawImage(Img, squareXLocation, squareYLocation,squareSize,squareSize, this);
        }
        else {
            URL= getClass().getResource("/image/objects/DirtHole.png");
            Image Img = new ImageIcon(URL).getImage();
            g.drawImage(Img, squareXLocation, squareYLocation,squareSize+50,squareSize, this);
        }
        
        
    }
    @Override
    public int generateRandomXLocation(){
        return squareXLocation = rand.nextInt(200, Game.WINDOW_WIDTH+200 - squareSize-200);
    }
    @Override
    public int generateRespawnDelay(){
       return 2000;
    } 
    @Override
    public int generateRandomSquareSize(){
        return squareSize = rand.nextInt(50, 100);
    }
    @Override
    public int generateRandomFallSpeed(){
        return fallSpeed = 3;
        //return fallSpeed = rand.ints(1, 2, 5).findFirst().getAsInt();
    }
    @Override
    boolean playerTouched(){
        Rectangle squareHitbox = new Rectangle(
        squareXLocation+80,
        squareYLocation,
        squareSize-50,
        squareSize-50
        );

        Rectangle playerHitbox = new Rectangle(
            Game.player.xActor+30,
            Game.player.yActor+30,
            Player.WIDTH-30,
            Player.HEIGHT-30
        );

        return squareHitbox.intersects(playerHitbox);
    }
    @Override
    void onPlayerTouched() {
       if(Game.Health>0&&!isHit&&Game.player.protectionTime==0&&Game.player.recoverTime==0){
           Game.Health--;        
           Game.player.isFallen = true;
           isHit = true;
           damageCooldown = 200;
       }
    }
    @Override
    void onFell() {
        isFallen=true;
    }
    @Override
    void update() {
         boolean touched = playerTouched();
         boolean fell = groundTouched();
         if(damageCooldown>0){
             damageCooldown--;
         }
         else{
             isHit=false;
         }
        if ((fell)&&!waitingToRespawn) {
            if (fell) onFell();
            squareYLocation +=10;
            waitingToRespawn = true;
            respawnDelay = generateRespawnDelay();
        }
        
        if (waitingToRespawn) {
            if (touched&&isFallen) onPlayerTouched();
            respawnDelay--;
            if (respawnDelay <= 0) {
                generateRandomXLocation();
                generateRandomFallSpeed();
                generateRandomSquareSize();
                squareYLocation = -squareSize;
                waitingToRespawn = false;
                isHit =false;
                isFallen=false;
            }

            return;
    }
        squareYLocation += fallSpeed;
        squareXLocation -= 2;
    }
}
