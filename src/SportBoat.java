import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class SportBoat extends Boat {
	private float _startPosX;
    private float _startPosY;
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

    public void SetPosition(int x, int y, int width, int height) {
        _startPosX = x;
        _startPosY = y;
        _pictureWidth = width;
        _pictureHeight = height;
       
    }

    public void MoveTransport(Direction direction) {
        float step = MaxSpeed * 100 / Weight;
        switch (direction) {
        		// вправо
            case Right:
                if (_startPosX + step < _pictureWidth - planeWidth - 85) {
                    _startPosX += step;
                }
                break;
                // влево
            case Left:
                if (_startPosX - step > 0) {
                    _startPosX -= step;
                }
                break;
                // вверх
            case Up:
                if (_startPosY - step > 5) {

                    _startPosY -= step;
                }
                break;
                // вниз
            case Down:
                if (_startPosY + step < _pictureHeight - planeHeight - 100) {
                    _startPosY += step;
                }
                break;
        }
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
