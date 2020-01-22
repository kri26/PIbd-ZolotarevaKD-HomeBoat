import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Parking<T extends ITransport, M extends IMotors>  implements Comparable<Parking<T, M>>, Iterable<T>, Iterator<T> {
	HashMap<Integer, T> _places;
	private HashMap<Integer, M> _placesWithMotor;
    private int PictureWidth;
    private int PictureHeight;

    private final int _placeSizeWidth = 210;
    private final int _placeSizeHeight = 80;
    int _maxCount;
    private int _currentIndex;
    
	ArrayList list = new ArrayList();

    public Parking(int sizes, int pictureWidth, int pictureHeight)
    {
    	_places = new HashMap<Integer, T>(sizes);
    	_placesWithMotor = new HashMap<Integer, M>(sizes);
    	_maxCount = sizes;
        PictureWidth = pictureWidth;
        PictureHeight = pictureHeight;
        _currentIndex = -1;
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
    
    public int setBoat(int index, T transport) throws ParkingOccupiedPlaceException {
	    if (CheckFreePlace(index)) {
	    	_places.put(index, transport);
	    	_places.get(index).SetPosition(5 + index / 5 * _placeSizeWidth + 5, index % 5 * _placeSizeHeight + 15, PictureWidth, PictureHeight);
	    	return index;
    	}
    	throw new ParkingOccupiedPlaceException(index);
    }

    public ITransport get (int index) {
    	if (!CheckFreePlace(index))
        {
            return _places.get(index);
        }
    	return null;
    }
    
    public int addBoat(T boat) throws ParkingOverflowException, ParkingAlreadyHaveException 
    {
    	if (_places.containsValue(boat))
            throw new ParkingAlreadyHaveException();
        for (int i = 0; i < _maxCount; i++) {
            if (CheckFreePlace(i)) {
            	_places.put(i, boat);
				_places.get(i).SetPosition(5 + i / 5 * _placeSizeWidth + 5, i % 5 * _placeSizeHeight + 15, PictureWidth, PictureHeight);
                return i;
            }
        }
        throw new ParkingOverflowException();
    }

    public T deletBoat(int index)  throws ParkingNotFoundException
    {
    	if (index < 0 || index > 20)
        {
    		throw new ParkingNotFoundException(index);
        }
        if (!CheckFreePlace(index)) {
            T boat = _places.get(index);
            _places.remove(index);
            return boat;
        }
        throw new ParkingNotFoundException(index);
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
    
    public int GetKey() {
    	return (int)_places.keySet().toArray()[_currentIndex];
    }
    
    public int compareTo(Parking<T, M> other) {
    	if (_places.size() > other._places.size())
    		return -1;
        else if (_places.size() < other._places.size())
        	return 1;
        else if (_places.size() > 0)
        {
        	Object[] thisKeys = _places.keySet().toArray();
        	Object[] otherKeys = other._places.keySet().toArray();
            for (int i = 0; i < _places.size(); ++i)
            {
                if (_places.get(thisKeys[i]).getClass().getName().equals("Boat") && other._places.get(thisKeys[i]).getClass().getName().equals("SportBoat"))
                	return 1;
                if (_places.get(thisKeys[i]).getClass().getName().equals("SportBoat") && other._places.get(thisKeys[i]).getClass().getName().equals("Boat"))
                	return -1;
                if (_places.get(thisKeys[i]).getClass().getName().equals("Boat") && other._places.get(thisKeys[i]).getClass().getName().equals("Boat"))
                {
                	Boat thisBoat = (Boat)_places.get(thisKeys[i]);
                    Boat otherBoat = (Boat)other._places.get(otherKeys[i]);
                    return thisBoat.compareTo(otherBoat);
                }
                if(_places.get(thisKeys[i]).getClass().getName().equals("SportBoat") && other._places.get(thisKeys[i]).getClass().getName().equals("SportBoat")) {
                	SportBoat thisBoat = (SportBoat)_places.get(thisKeys[i]);
                    SportBoat otherBoat = (SportBoat)other._places.get(otherKeys[i]);
                    return thisBoat.compareTo(otherBoat);
                }
            }
        }
        return 0;
    }

	@Override
	public Iterator<T> iterator() {
		return this;
	}

	@Override
	public boolean hasNext() {
		if (_currentIndex + 1 >= _places.size())
		{
			_currentIndex = -1;
			return false;
		}else
			return true;
	}

	@Override
	public T next() {
		return _places.get(++_currentIndex); 
	}
}