package javarico;
import robocode.*;
import java.awt.Color;

public class Javarico extends Robot {
    
    // Cores do robô
    public void setColors() {
        setBodyColor(Color.blue);
        setGunColor(Color.black);
        setRadarColor(Color.yellow);
        setBulletColor(Color.red);
        setScanColor(Color.green);
    }
    
    public void run() {
        setColors(); // Define as cores
        
        // Comportamento principal do robô
        while(true) {
            // Movimento padrão: anda em quadrados
            ahead(200);
            turnGunRight(360); // Varre o radar enquanto se move
            turnRight(90);
            
            // Verifica se há inimigos próximos periodicamente
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
    
    // Quando bater na parede
    public void onHitWall(HitWallEvent e) {
        // Vira 90 graus e recua
        turnRight(normalizeBearing(e.getBearing() + 90));
        back(150);
    }
    
    // Quando for atingido por um tiro
    public void onHitByBullet(HitByBulletEvent e) {
        // Movimento evasivo
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
