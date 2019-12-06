import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class SportBoat extends Boat {
	private int _startPosX;
    private int _startPosY;
    private int _pictureWidth;
    private int _pictureHeight;
    private int planeWidth = 90;
    private int planeHeight = 50;
    public int MaxSpeed;
    public float Weight;
    public Color MainColor;
    public Color DopColor;
    IMotors drawMotor;
    
    public ITransport Clone(){
    	ITransport boat = new SportBoat((int)(Math.random() * 200) + 100, 
					 (int)(Math.random() * 1000) + 1000, 
					 new Color((int)(Math.random() * 256), 
							   (int)(Math.random() * 256), 
							   (int)(Math.random() * 256)),
					 new Color((int)(Math.random() * 256), 
							   (int)(Math.random() * 256), 
							   (int)(Math.random() * 256)));
    	return boat;
    }

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

    public void SetPosition(int x, int y, int width, int height) {
        _startPosX = x;
        _startPosY = y;
        _pictureWidth = width;
        _pictureHeight = height;
       
    }

    public void DrawBoat(Graphics g) {
	        Graphics2D g2 = (Graphics2D) g;
	        Rectangle2D rectangle = new Rectangle2D.Double( _startPosX + 20, _startPosY + 10, 50, 30);
	        Rectangle2D rectangleSmall = new Rectangle2D.Double( _startPosX + 30, _startPosY, 10, 10);
	        Ellipse2D oval = new Ellipse2D.Double(_startPosX, _startPosY + 30, 90, 20);
	        g2.setPaint(Color.GRAY);
	        g2.fill(rectangle);
	        g2.setPaint(Color.BLUE);
	        g2.fill(oval);
	        g2.fill(rectangleSmall);
	        drawMotor.DrawM(g, Color.black, (int)_startPosX, (int)_startPosY);
    }
}
