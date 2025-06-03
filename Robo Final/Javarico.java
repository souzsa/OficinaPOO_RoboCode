/* 
- Fernanda G. F. de Souza
- Atividade Final
- BotFinal Javarico
- 30/05/2025
*/
package javarico;
import robocode.*;
import java.awt.Color;

public class Javarico extends Robot {
    
    
    public void setColors() {
        setBodyColor(Color.blue);
        setGunColor(Color.black);
        setRadarColor(Color.yellow);
        setBulletColor(Color.red);
        setScanColor(Color.green);
    }
    
    public void run() {
        setColors();
        
        while(true) {
           
            ahead(200);
            turnGunRight(360);
            turnRight(90);
            
          
            scan();
        }
    }
    
    // Quando detectar um robô inimigo
    public void onScannedRobot(ScannedRobotEvent e) {
        // Calcula a posição do inimigo
        double absoluteBearing = getHeading() + e.getBearing();
        
        // Vira o canhão para o inimigo
        turnGunRight(normalizeBearing(absoluteBearing - getGunHeading()));
        
        // Atira com potência proporcional à distância
        double firePower = Math.min(500 / e.getDistance(), 3);
        fire(firePower);
        
        // Movimento evasivo
        if (e.getDistance() < 150) {
            turnRight(normalizeBearing(e.getBearing() + 90));
            ahead(100);
        }
    }
    
    public void onHitWall(HitWallEvent e) {
       
        turnRight(normalizeBearing(e.getBearing() + 90));
        back(150);
    }
    
    public void onHitByBullet(HitByBulletEvent e) {
      
        turnRight(normalizeBearing(e.getBearing() + 90));
        ahead(100);
    }
    
    // Normaliza ângulos para ficarem entre -180 e 180
    private double normalizeBearing(double angle) {
        while (angle >  180) angle -= 360;
        while (angle < -180) angle += 360;
        return angle;
    }
}
