# Assignment 3: Bridge Pattern

**Student:** Rizabek Begen Nurlybekuly
**Group:** SE-2529
**Topic:** Option A (Drawing: Shape and Renderer)
**Repository URL:** https://github.com/duskazievrizabek-art/assignment3_sdp.git
**Base Commit Hash:** d8b0ce333be908fcfcd566a0cbc8811c4d8b6ae5

---

## Role Map

| Pattern Role | Class | Source Path |
|---|---|---|
| Abstraction | `Shape` | `src/Shape.java` |
| A1 (refined abstraction) | `Circle` | `src/Circle.java` |
| A2 (refined abstraction) | `Square` | `src/Square.java` |
| Implementor | `Renderer` | `src/Renderer.java` |
| I1 | `VectorRenderer` | `src/VectorRenderer.java` |
| I2 | `RasterRenderer` | `src/RasterRenderer.java` |
| I3 (extension) | `AsciiRenderer` | `src/AsciiRenderer.java` |
| Client | `Main` | `src/Main.java` |

---

## Where to Find Key Parts

- **Bridge field:** `Shape.java` -> `protected Renderer renderer;` (set through the constructor)
- **execute():** declared abstract in `Shape.java`. Implemented in `Circle.java` (calls `renderer.drawCircle(radius)`) and `Square.java` (calls `renderer.drawSquare(side)`)
- **setImplementation(...):** `Shape.java` -> `public void setImplementation(Renderer renderer)`
- **T5 check:** `Main.java` -> `main()` method, the block that starts with `Circle original = ...`

---

## Build and Run Commands

Run from the project folder:

```
javac --release 17 -encoding UTF-8 -d out "@sources.txt"
java -cp out Main --demo
```

---

## Expected Results

| Check | Setup | Expected result |
|---|---|---|
| T1 | Circle (radius 2) + VectorRenderer | `VECTOR circle radius=2` |
| T2 | Circle (radius 2) + RasterRenderer | `RASTER circle radius=2 pixels` |
| T3 | Square (side 3) + VectorRenderer | `VECTOR square side=3` |
| T4 | Square (side 3) + RasterRenderer | `RASTER square side=3 pixels` |
| T5 | Circle C5 starts with VectorRenderer, then switched to RasterRenderer | `sameObject=true`, `stateUnchanged=true`, before=`VECTOR circle radius=2`, after=`RASTER circle radius=2 pixels` |
| T6 | Circle (radius 2) + AsciiRenderer | `ASCII circle radius=2 (o)` |
| T7 | Square (side 3) + AsciiRenderer | `ASCII square side=3 [#]` |

Last line of the output: `SUMMARY: 7/7 PASS`

---

## Extension

`AsciiRenderer` was added in a separate commit after the base commit.
Only `AsciiRenderer.java` and `Main.java` changed in `src`. The diff is in `extension.diff`.
