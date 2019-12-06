import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class SportBoat extends Boat {
    private int _pictureWidth;
    private int _pictureHeight;
    private int planeWidth = 90;
    private int planeHeight = 50;
    public int MaxSpeed;
    public float Weight;
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
    }
    
    public void setDopColor(Color dop) {
		DopColor = dop;
	}
    
    public void setMotors(IMotors iMotors) {
		drawMotor = iMotors;
	}

    public void DrawBoat(Graphics g) {
	        Graphics2D g2 = (Graphics2D) g;
	        Rectangle2D rectangle = new Rectangle2D.Double( _startPosX + 20, _startPosY + 10, 50, 30);
	        Rectangle2D rectangleSmall = new Rectangle2D.Double( _startPosX + 30, _startPosY, 10, 10);
	        g2.setPaint(Color.GRAY);
	        g2.fill(rectangle);
	        g2.setPaint(Color.BLACK);
	        g2.fill(rectangleSmall);
    		super.DrawBoat(g);
    		if(drawMotor != null)
    			drawMotor.DrawM(g, DopColor, (int)_startPosX, (int)_startPosY);
    }
}
