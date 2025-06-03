/* 
- Fernanda G. F. de Souza
- Atividade 08
- BotMataBarata
- 27/05/2025
*/
package mataBarata;
import robocode.*;

public class MataBarata extends Robot
{
	public void run() {
	 // Gira o radar o tempo todo
        while(true) {
            turnGunRight(10);  // varre lentamente com o radar
        }
    }

    public void onScannedRobot(ScannedRobotEvent event) {
        // Atira com potência dependendo da distância
        double distance = event.getDistance();
        double firePower;

        // Define potência do tiro conforme distância
        if (distance < 100) {
            firePower = 3;
        } else if (distance < 300) {
            firePower = 2;
        } else {
            firePower = 1;
        }

        // Vira o canhão para o inimigo (ajuste fino)
        turnGunRight(event.getBearing());
        fire(firePower);
    }
}
