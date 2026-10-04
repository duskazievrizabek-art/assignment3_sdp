public class AsciiRenderer implements Renderer {
    public String drawCircle(int radius) {
        return "ASCII circle radius=" + radius + " (o)";
    }

    public String drawSquare(int side) {
        return "ASCII square side=" + side + " [#]";
    }
}