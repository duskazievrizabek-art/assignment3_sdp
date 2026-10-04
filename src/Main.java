public class Main {
    private static int total = 0;
    private static int passed = 0;

    private static void check(String id, String classes, String actual, String expected) {
        total++;
        boolean ok = actual.equals(expected);
        if (ok) {
            passed++;
        }
        System.out.println(id + " " + (ok ? "PASS" : "FAIL") + " | " + classes + " | result=" + actual);
        if (!ok) {
            System.out.println("   expected=" + expected);
        }
    }

    public static void main(String[] args) {
        if (args.length == 0 || !args[0].equals("--demo")) {
            System.out.println("Usage: java -cp out Main --demo");
            return;
        }

        Circle c1 = new Circle("C1", 2, new VectorRenderer());
        check("T1", "Circle + VectorRenderer", c1.execute(), "VECTOR circle radius=2");

        Circle c2 = new Circle("C1", 2, new RasterRenderer());
        check("T2", "Circle + RasterRenderer", c2.execute(), "RASTER circle radius=2 pixels");

        Square s1 = new Square("S1", 3, new VectorRenderer());
        check("T3", "Square + VectorRenderer", s1.execute(), "VECTOR square side=3");

        Square s2 = new Square("S1", 3, new RasterRenderer());
        check("T4", "Square + RasterRenderer", s2.execute(), "RASTER square side=3 pixels");

        Circle original = new Circle("C5", 2, new VectorRenderer());
        Shape saved = original;
        String before = saved.execute();
        original.setImplementation(new RasterRenderer());
        String after = original.execute();
        boolean sameObject = saved == original;
        boolean stateUnchanged = original.getId().equals("C5") && original.getRadius() == 2;
        boolean resultsOk = before.equals("VECTOR circle radius=2")
                && after.equals("RASTER circle radius=2 pixels");
        total++;
        boolean t5 = sameObject && stateUnchanged && resultsOk;
        if (t5) {
            passed++;
        }
        System.out.println("T5 " + (t5 ? "PASS" : "FAIL") + " | sameObject=" + sameObject
                + " | stateUnchanged=" + stateUnchanged);
        System.out.println("   before=" + before + " | after=" + after);
        Circle c3 = new Circle("C1", 2, new AsciiRenderer());
        check("T6", "Circle + AsciiRenderer", c3.execute(), "ASCII circle radius=2 (o)");

        Square s3 = new Square("S1", 3, new AsciiRenderer());
        check("T7", "Square + AsciiRenderer", s3.execute(), "ASCII square side=3 [#]");

        System.out.println("SUMMARY: " + passed + "/" + total + " PASS");
    }
}