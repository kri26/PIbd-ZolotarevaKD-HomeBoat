import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;

public class Parking<T extends ITransport, M extends IMotors> {
	HashMap<Integer, T> _places;
    private int PictureWidth;
    private int PictureHeight;

    private final int _placeSizeWidth = 210;
    private final int _placeSizeHeight = 80;
    int _maxCount;
    
	ArrayList list = new ArrayList();

    public Parking(int sizes, int pictureWidth, int pictureHeight)
    {
    	_places = new HashMap<Integer, T>(sizes);
    	_maxCount = sizes;
        PictureWidth = pictureWidth;
        PictureHeight = pictureHeight;
    }
    
    public int getPictureWidth() {
    	return PictureWidth;
    }
    
    public int getPictureHeight() {
    	return PictureHeight;
    }
    
    public void setPictureWidth(int value) {
    	PictureWidth = value;
    }
    
    public void setPictureHeight(int value) {
    	PictureHeight = value;
    }

    public int addBoat(T boat)
    {
        for (int i = 0; i < _maxCount; i++) {
            if (CheckFreePlace(i)) {
            	_places.put(i, boat);
				_places.get(i).SetPosition(5 + i / 5 * _placeSizeWidth + 5, i % 5 * _placeSizeHeight + 15, PictureWidth, PictureHeight);
                return i;
            }
        }
        return -1;
    }

    public T deletBoat(int index)
    {
        if (!CheckFreePlace(index)) {
            T boat = _places.get(index);
            _places.remove(index);
            return boat;
        }
        return null;
    }
    

    private boolean CheckFreePlace(int index)
    {
        return !_places.containsKey(index);
    }

    public void Draw(Graphics g)
    {
        DrawMarking(g);
        for (int i = 0; i < _maxCount; i++) {
            if (!CheckFreePlace(i)) {
                _places.get(i).DrawBoat(g);
            }
        }
    }

    private void DrawMarking(Graphics g)
    {
    	g.setColor(Color.black);
        g.drawRect(0, 0, (_maxCount / 5) * _placeSizeWidth, 480);
        for (int i = 0; i < _maxCount / 5; i++) {
            for (int j = 0; j < 6; j++){
                g.drawLine(i * _placeSizeWidth, j * _placeSizeHeight,i * _placeSizeWidth + 110, j * _placeSizeHeight);
            }
            g.drawLine(i * _placeSizeWidth, 0, i * _placeSizeWidth, 400);
        }
    }
}