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
        String before = original.execute();
        original.setImplementation(new RasterRenderer());
        Circle afterRef = original;
        String after = afterRef.execute();
        boolean sameObject = original == afterRef;
        boolean stateUnchanged = afterRef.getId().equals("C5") && afterRef.getRadius() == 2;
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

        System.out.println("SUMMARY: " + passed + "/" + total + (passed == total ? " PASS" : " PASS (not all)"));
    }
}