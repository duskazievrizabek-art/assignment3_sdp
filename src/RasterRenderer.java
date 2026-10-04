public class RasterRenderer implements Renderer {
    public String drawCircle(int radius) {
        return "RASTER circle radius=" + radius + " pixels";
    }

    public String drawSquare(int side) {
        return "RASTER square side=" + side + " pixels";
    }
}