public class Square extends Shape {
    private final int side;

    public Square(String id, int side, Renderer renderer) {
        super(id, renderer);
        this.side = side;
    }

    public int getSide() {
        return side;
    }

    public String execute() {
        return renderer.drawSquare(side);
    }
}