/* 
- Fernanda G. F. de Souza
- Atividade 07
- BotBarata
- 22/05/2025
*/
package javarico;
import robocode.*;

public class Atividade07_javarico extends Robot
{
    public void run() {
        // Começa andando reto (para frente)
        while(true){
			ahead(400);  
		}
    }

    public void onHitWall(HitWallEvent event) {
		setDebugProperty("Angulo da colisão", String.valueOf(event.getBearing()));
        while(true){
		turnLeft(1);
		ahead(800);
		}
	}
}
