import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class Boat implements ITransport {
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
    DrawMotor drawMotor;
    
    public ITransport Clone(){
    	ITransport boat = new Boat((int)(Math.random() * 200) + 100, 
				  (int)(Math.random() * 1000) + 1000, 
				  new Color((int)(Math.random() * 256), 
						    (int)(Math.random() * 256), 
						    (int)(Math.random() * 256)));
    	return boat;
    }

    public Boat(int maxSpeed, float weight, Color mainColor) {
        MaxSpeed = maxSpeed;
        Weight = weight;
        MainColor = mainColor;
    }

    public void SetPosition(int x, int y, int width, int height) {
        _pictureWidth = width;
        _pictureHeight = height;
        _startPosX = x;
        _startPosY = y;
        drawMotor = new DrawMotor();
    }

    public void DrawBoat(Graphics g) {
			        Graphics2D g2 = (Graphics2D) g;
			        Ellipse2D oval = new Ellipse2D.Double(_startPosX, _startPosY + 30, 90, 20);
			        g2.setPaint(Color.BLUE);
			        g2.fill(oval);
        }
}