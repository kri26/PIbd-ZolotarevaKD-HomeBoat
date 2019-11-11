import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class Boat implements ITransport {
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
    DrawMotor drawMotor;

    public Boat(int maxSpeed, float weight, Color mainColor) {
        MaxSpeed = maxSpeed;
        Weight = weight;
        MainColor = mainColor;
    }

    public void SetPosition(int x, int y, int width, int height) {
        _startPosX = x;
        _startPosY = y;
        _pictureWidth = width;
        _pictureHeight = height;
        drawMotor = new DrawMotor();
    }
    
    public void MoveTransport(Direction direction) {
        float step = MaxSpeed * 100 / Weight;
        switch (direction) {
        		// ������
            case Right:
                if (_startPosX + step < _pictureWidth - planeWidth - 85) {
                    _startPosX += step;
                }
                break;
                // �����
            case Left:
                if (_startPosX - step > 0) {
                    _startPosX -= step;
                }
                break;
                // �����
            case Up:
                if (_startPosY - step > 3) {

                    _startPosY -= step;
                }
                break;
                // ����
            case Down:
                if (_startPosY + step < _pictureHeight - planeHeight - 100) {
                    _startPosY += step;
                }
                break;
        }
    }

    public void DrawBoat(Graphics g) {
    	
        Graphics2D g2 = (Graphics2D) g;
        Ellipse2D oval = new Ellipse2D.Double(_startPosX, _startPosY + 30, 90, 20);
        g2.setPaint(Color.BLUE);
        g2.fill(oval);
        }
}