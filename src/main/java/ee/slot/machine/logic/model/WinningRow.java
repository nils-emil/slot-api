package ee.slot.machine.logic.model;

public enum WinningRow {
    HORIZONTAL_TOP(0, 0),
    HORIZONTAL_MIDDLE(1, 0),
    HORIZONTAL_BOTTOM(2, 0),
    VERTICAL_LEFT(0, 0),
    VERTICAL_MIDDLE(0, 1),
    VERTICAL_RIGHT(0, 2),
    DIAGONAL_BOTTOM(0, 2),
    DIAGONAL_UPPER(0, 0);

    public final int xCoordinateStart;
    public final int yCoordinateStart;

    WinningRow(int x, int y) {
        this.xCoordinateStart = x;
        this.yCoordinateStart = y;
    }

}
