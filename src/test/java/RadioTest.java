import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RadioTest {
    @Test
    void defaultConstructorShouldCreate10Stations() {
        Radio radio = new Radio();
        assertEquals(10, radio.getStationsCount());
    }

    @Test
    void customConstructorShouldSetCorrectStationsCount() {
        Radio radio = new Radio(15);
        assertEquals(15, radio.getStationsCount());
    }

    @Test
    void shouldNotAllowZeroOrNegativeStations() {
        assertThrows(IllegalArgumentException.class, () -> new Radio(0));
        assertThrows(IllegalArgumentException.class, () -> new Radio(-5));
    }

    @Test
    void nextShouldWrapFromLastToFirstStation() {
        Radio radio = new Radio(5);
        radio.setCurrentStation(4);
        radio.next();
        assertEquals(0, radio.getCurrentStation());
    }

    @Test
    void prevShouldWrapFromFirstToLastStation() {
        Radio radio = new Radio(5);
        radio.setCurrentStation(0);
        radio.prev();
        assertEquals(4, radio.getCurrentStation());
    }

    @Test
    void shouldNotSetInvalidStationNumber() {
        Radio radio = new Radio(5);
        assertThrows(IllegalArgumentException.class, () -> radio.setCurrentStation(-1));
        assertThrows(IllegalArgumentException.class, () -> radio.setCurrentStation(5));
    }

    @Test
    void volumeShouldNotExceedMax() {
        Radio radio = new Radio();
        for (int i = 0; i < 150; i++) radio.increaseVolume();
        assertEquals(100, radio.getCurrentVolume());
    }

    @Test
    void volumeShouldNotGoBelowMin() {
        Radio radio = new Radio();
        for (int i = 0; i < 150; i++) radio.decreaseVolume();
        assertEquals(0, radio.getCurrentVolume());
    }
}