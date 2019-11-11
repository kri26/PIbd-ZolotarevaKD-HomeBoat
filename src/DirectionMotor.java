
public enum DirectionMotor {
	One,
	Two,
	Three;
	
	public static DirectionMotor getRandom() {
        return values()[(int) (Math.random() * values().length)];
    }
}
