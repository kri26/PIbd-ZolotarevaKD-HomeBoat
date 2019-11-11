public enum NumderOfMotors {
	One,
	Two,
	Three;
	
	public static NumderOfMotors getRandom() {
        return values()[(int) (Math.random() * values().length)];
    }
}
