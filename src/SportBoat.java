import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class SportBoat extends Boat {
	private int [] _startPosX = new int [20];
    private int [] _startPosY = new int [20];
    private int _pictureWidth;
    private int _pictureHeight;
    private int planeWidth = 90;
    private int planeHeight = 50;
    public int MaxSpeed;
    public float Weight;
    public Color MainColor;
    public Color DopColor;
    IMotors drawMotor;

    public SportBoat(int maxSpeed, float weight, Color mainColor, Color dopColor) {
    	super(maxSpeed, weight, mainColor);
    	MaxSpeed = maxSpeed;
        Weight = weight;
        MainColor = mainColor;
        DopColor = dopColor;
        drawMotor = new DrawMotor();
        drawMotor = new DrawMotors2Poit0();
        drawMotor = new DrawMotorsVersionThree();
    }

    public void SetPosition(int [] x, int [] y, int size , int width, int height) {
    	_startPosX = new int [size];
    	_startPosY = new int [size];
    	System.arraycopy(x, 0, _startPosX, 0, x.length);
    	System.arraycopy(y, 0, _startPosY, 0, x.length);
        //_startPosX = x;
        //_startPosY = y;
        _pictureWidth = width;
        _pictureHeight = height;
       
    }

    public void DrawBoat(Graphics g) {
    	for(int j = 0; j < _startPosX.length; j++) {
	        Graphics2D g2 = (Graphics2D) g;
	        Rectangle2D rectangle = new Rectangle2D.Double( _startPosX[j] + 20, _startPosY[j] + 10, 50, 30);
	        Rectangle2D rectangleSmall = new Rectangle2D.Double( _startPosX[j] + 30, _startPosY[j], 10, 10);
	        Ellipse2D oval = new Ellipse2D.Double(_startPosX[j], _startPosY[j] + 30, 90, 20);
	        g2.setPaint(Color.GRAY);
	        g2.fill(rectangle);
	        g2.setPaint(Color.BLUE);
	        g2.fill(oval);
	        g2.fill(rectangleSmall);
	        drawMotor.DrawM(g, Color.black, (int)_startPosX[j], (int)_startPosY[j]);
    	}
    }
}
