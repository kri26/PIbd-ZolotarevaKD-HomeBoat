import java.util.Iterator;

public class EnumIterator implements Iterator<DirectionMotor>{
	private DirectionMotor[] Motor;
	private int _currentIndex = -1;

	public EnumIterator() {
		Motor = DirectionMotor.values();
	}

	@Override
	public boolean hasNext() {
		if (_currentIndex + 1 >= Motor.length)
		{
			_currentIndex = -1;
			return false;
		}else
			return true;
	}

	@Override
	public DirectionMotor next() {
		return Motor[++_currentIndex];
	}
}