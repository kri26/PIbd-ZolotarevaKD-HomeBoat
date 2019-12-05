import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class Boat implements ITransport {
	private int _startPosX [] = new int[20];
    private int _startPosY [] =  new int [20];
    private int _pictureWidth;
    private int _pictureHeight;
    private int planeWidth = 90;
    private int planeHeight = 50;
    public int MaxSpeed;
    public float Weight;
    public Color MainColor;
    public Color DopColor;
    DrawMotor drawMotor;

    public Boat(int maxSpeed, float weight, Color mainColor) {
        MaxSpeed = maxSpeed;
        Weight = weight;
        MainColor = mainColor;
    }

    public void SetPosition(int []x, int []y, int size, int width, int height) {
    	_startPosX = new int [size];
    	_startPosY = new int [size];
    	System.arraycopy(x, 0, _startPosX, 0, x.length);
    	System.arraycopy(y, 0, _startPosY, 0, x.length);
        _pictureWidth = width;
        _pictureHeight = height;
        drawMotor = new DrawMotor();
    }

    public void DrawBoat(Graphics g) {
	    	  for(int j = 0; j < _startPosX.length; j++) {
			        Graphics2D g2 = (Graphics2D) g;
			        Ellipse2D oval = new Ellipse2D.Double(_startPosX[j], _startPosY[j] + 30, 90, 20);
			        g2.setPaint(Color.BLUE);
			        g2.fill(oval);
	    	  }
        }
}