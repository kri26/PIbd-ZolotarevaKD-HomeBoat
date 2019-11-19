import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class DrawMotor {
	DirectionMotor directionMotor;
	DirectionMotor rand;
	
	public DrawMotor() {
			rand = DirectionMotor.getRandom();
		
	}
	
	public void DrawM(int _startPosX, int _startPosY, Graphics g) {
        
        g.setColor(Color.black);
        switch (rand)
        {
            case One:
                g.fillOval(_startPosX, _startPosY + 35, 10, 10);
                break;
            case Two:
                g.fillOval(_startPosX-10, _startPosY + 30, 15, 10);
                g.fillOval(_startPosX-10, _startPosY + 40, 15, 10);
                break;
            case Three:
                g.fillOval(_startPosX, _startPosY + 25, 11, 10);
                g.fillOval(_startPosX, _startPosY + 35, 11, 10);
                g.fillOval(_startPosX, _startPosY + 45, 11, 10);
                break;
        }
    }
}
