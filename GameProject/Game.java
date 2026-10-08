
package GameProject;

/**
 *
 * @author com
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.Vector;

public class Game extends JPanel{
    //Game Screen
    public static final int WINDOW_WIDTH = 1300;
    public static final int WINDOW_HEIGHT = 900;
    URL bgURL= getClass().getResource("/image/Background2.jpg");
    Image bgImage = new ImageIcon(bgURL).getImage();
    //Timers
    Timer timer = new Timer(10, new TimerListener());
    Timer MonGentime = new Timer(3000, new Generate(1));
    Timer BombGentime = new Timer(5000, new Generate(2));
    Timer MedGentime = new Timer(7000, new Generate(3));
    Timer MeteoGentime = new Timer(4000 , new Generate(4));
    Timer SheildGentime = new Timer(10000 , new Generate(5));
    Timer PoisonGentime = new Timer(5000 , new Generate(6));
    //Game Info
    public static Player player;
    public static Score score = new Score();
    //Objects Amount
    public static int moneyAmount ,bombAmount, medAmount, meteoAmount,sheildAmount,poisonAmount;
    public static int Health,MaxHealth=5;
    
    //Creates a Money object Array
    Vector<Square> monArray = new Vector<>();
    Vector<Square> bombArray = new Vector<>();
    Vector<Square> medArray = new Vector<>();
    Vector<Square> meteoArray = new Vector<>();
    Vector<Square> sheildArray = new Vector<>();
    Vector<Square> poisonArray = new Vector<>();
    Health[] hearts = new Health[MaxHealth];
    private class TimerListener implements ActionListener {
        @Override /** Handle the action event */
        public void actionPerformed(ActionEvent e) {
          update();
          repaint();
        }
    }
    private class Generate implements ActionListener {   
        int toAdd;

        public Generate(int toAdd) {
            this.toAdd = toAdd;
        }
        
        @Override //Handle the action event
        public void actionPerformed(ActionEvent e) {
          switch(toAdd){
              case 1:monArray.add(new Money());break;
              case 2:bombArray.add(new Bomb());break;
              case 3:medArray.add(new Medkit());break;
              case 4:meteoArray.add(new Meteorite());break;
              case 5:sheildArray.add(new Sheild());break;
              case 6:poisonArray.add(new Poison());break;
          }
        }
    }
    public static void gameSetting(int mon,int bomb,int med,int meteo,int sheild,int poison){
        Game.moneyAmount = mon; 
        Game.bombAmount = bomb; 
        Game.medAmount = med; 
        Game.meteoAmount = meteo;
        Game.sheildAmount = sheild;
        Game.poisonAmount = poison;
    }
    public Game() {
        player = new Player();
        Health = MaxHealth;
        for(int i=0;i<MaxHealth;i++){
            hearts[i] = new Health(i);
        }
        addKeyListener(new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            player.keyPressed(e.getKeyCode());
        }

        @Override
        public void keyReleased(KeyEvent e) {
            player.keyReleased(e.getKeyCode());
        }
    });
        //initializes square objects
        timer.start();
        if(moneyAmount!=0)MonGentime.start();
        if(bombAmount!=0)BombGentime.start();
        if(medAmount!=0)MedGentime.start();
        if(meteoAmount!=0)MeteoGentime.start();
        if(sheildAmount!=0)SheildGentime.start();
        if(poisonAmount!=0)PoisonGentime.start();
    }
    
    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.drawImage(bgImage, 0, 0, getWidth(),getHeight(),this);
        if(Score.score>=Score.MaxScore){
            stopAllTimers();
            WealthFromAbove.EndGame("Stage Cleared");
            return;
        }
        if(Health==0){
            stopAllTimers();
            WealthFromAbove.EndGame("Game Over");
            return;
        }
        //paints objects to the screen
        paintEach(monArray,graphics);
        paintEach(bombArray,graphics);
        paintEach(medArray,graphics);
        paintEach(meteoArray,graphics);
        paintEach(sheildArray,graphics);
        paintEach(poisonArray,graphics);
        //paints all hearts objects to the screen
        for (Health aHeart : hearts) {
            if(Health<MaxHealth){
                if(Health>0)hearts[Health-1].isRed=true;
                hearts[Health].isRed=false;
            }
            if(Health==MaxHealth)hearts[Health-1].isRed=true;
            aHeart.paint(graphics);
        }
        player.paint(graphics);
        score.paintComponent(graphics);
    }
    public void update() {
        player.update();
        //Money
        updateEach(monArray);
        if(monArray.size()==moneyAmount&&MonGentime.isRunning()) MonGentime.stop();
        //Bomb
        updateEach(bombArray);
        if(bombArray.size()==bombAmount&&BombGentime.isRunning()) BombGentime.stop();
        //Medkit
        updateEach(medArray);
        if(medArray.size()==medAmount&&MedGentime.isRunning()) MedGentime.stop();
        //Meteorite
        updateEach(meteoArray);
        if(meteoArray.size()==meteoAmount&&MeteoGentime.isRunning()) MeteoGentime.stop();
        //Sheild
        updateEach(sheildArray);
        if(sheildArray.size()==sheildAmount&&SheildGentime.isRunning()) SheildGentime.stop();
        //Sheild
        updateEach(poisonArray);
        if(poisonArray.size()==poisonAmount&&PoisonGentime.isRunning()) PoisonGentime.stop();
    }
    void updateEach(Vector<Square> Array){
        for (Square obj : Array) {
            obj.update();
        }
    }
    void paintEach(Vector<Square> Array,Graphics graphics){
        for (Square obj : Array) {
            obj.paint(graphics);
        }
    }
    void stopAllTimers() {
        timer.stop();
        MonGentime.stop();
        BombGentime.stop();
        MedGentime.stop();
        MeteoGentime.stop();
        SheildGentime.stop();
        PoisonGentime.stop();
    }
}
