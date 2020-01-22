import java.awt.Color;
import java.awt.Graphics;

public class DrawMotorsVersionThree implements IMotors {
	NumderOfMotors directionMotor;
	NumderOfMotors rand;
	
	public DrawMotorsVersionThree() {
			rand = NumderOfMotors.getRandom();
	}
	
	@Override
	public void DrawM(Graphics g, Color color, int _startPosX, int _startPosY) {
		
		g.setColor(color);
        switch (rand)
        {
            case One:
                g.fillOval(_startPosX, _startPosY + 35, 15, 10);
                break;
            case Two:
                g.fillOval(_startPosX-10, _startPosY + 30, 15, 10);
                g.fillOval(_startPosX-10, _startPosY + 40, 15, 10);
                break;
            case Three:
                g.fillOval(_startPosX, _startPosY + 25, 15, 10);
                g.fillOval(_startPosX, _startPosY + 35, 15, 10);
                g.fillOval(_startPosX, _startPosY + 45, 15, 10);
                break;
        }
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		return true;
	}
}
