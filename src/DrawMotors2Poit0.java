import java.awt.Color;
import java.awt.Graphics;

public class DrawMotors2Poit0 implements IMotors {

	
	NumderOfMotors directionMotor;
	NumderOfMotors rand;
	
	public DrawMotors2Poit0() {
			rand = NumderOfMotors.getRandom();
	}
	
	@Override
	public void DrawM(Graphics g, Color color, int _startPosX, int _startPosY) {
		
		g.setColor(Color.yellow);
        switch (rand)
        {
            case One:
                g.fillOval(_startPosX, _startPosY + 35, 10, 10);
                break;
            case Two:
                g.fillOval(_startPosX-10, _startPosY + 30, 10, 10);
                g.fillOval(_startPosX-10, _startPosY + 40, 10, 10);
                break;
            case Three:
                g.fillOval(_startPosX, _startPosY + 25, 10, 10);
                g.fillOval(_startPosX, _startPosY + 35, 10, 10);
                g.fillOval(_startPosX, _startPosY + 45, 10, 10);
                break;
        }
	}
}
