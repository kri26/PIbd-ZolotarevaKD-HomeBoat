import java.awt.Graphics;

public interface ITransport {
	void SetPosition(int [] x, int [] y, int size, int width, int height);
    void DrawBoat(Graphics g);
}
