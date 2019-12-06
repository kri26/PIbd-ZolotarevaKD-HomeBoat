import java.awt.Graphics;

public interface ITransport {
	void SetPosition(int x, int y, int width, int height);
    void DrawBoat(Graphics g);
    ITransport Clone();
}
