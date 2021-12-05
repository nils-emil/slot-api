package ee.slot.machine.logic.model;

public enum Card {

    ACE(100),
    KING(80),
    QUEEN(50),
    NIGHT(30),
    TEN(20),
    NINE(10),
    EIGHT(4),
    SEVEN(2);

    public final Integer win;

    Card(Integer win) {
        this.win = win;
    }

}
