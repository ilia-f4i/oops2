import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RadioTest {

    @Test
    public void testNextStation() {
        Radio radio = new Radio();
        radio.setCurrentStation(9);
        radio.nextStation();
        assertEquals(0, radio.getCurrentStation());

        radio.setCurrentStation(5);
        radio.nextStation();
        assertEquals(6, radio.getCurrentStation());
    }

    @Test
    public void testPrevStation() {
        Radio radio = new Radio();
        radio.setCurrentStation(0);
        radio.prevStation();
        assertEquals(9, radio.getCurrentStation());

        radio.setCurrentStation(5);
        radio.prevStation();
        assertEquals(4, radio.getCurrentStation());
    }

    @Test
    public void testIncreaseVolume() {
        Radio radio = new Radio();
        for (int i = 0; i < 101; i++) {
            radio.increaseVolume();
        }
        assertEquals(100, radio.getCurrentVolume());
    }

    @Test
    public void testDecreaseVolume() {
        Radio radio = new Radio();
        radio.setCurrentVolume(100);
        for (int i = 0; i < 101; i++) {
            radio.decreaseVolume();
        }
        assertEquals(0, radio.getCurrentVolume());
    }

    @Test
    public void testSetInvalidStation() {
        Radio radio = new Radio();
        radio.setCurrentStation(-1);
        assertEquals(0, radio.getCurrentStation()); // Проверяем, что некорректное значение не изменило станцию

        radio.setCurrentStation(10);
        assertEquals(0, radio.getCurrentStation()); // Проверяем, что некорректное значение не изменило станцию
    }

    @Test
    public void testInitialValues() {
        Radio radio = new Radio();
        assertEquals(0, radio.getCurrentStation());
        assertEquals(0, radio.getCurrentVolume());
    }

    @Test
    public void testSetCurrentVolume() {
        Radio radio = new Radio();
        radio.setCurrentVolume(50);
        assertEquals(50, radio.getCurrentVolume());

        radio.setCurrentVolume(-1);
        assertEquals(50, radio.getCurrentVolume()); // Проверяем, что некорректное значение не изменило громкость

        radio.setCurrentVolume(101);
        assertEquals(50, radio.getCurrentVolume()); // Проверяем, что некорректное значение не изменило громкость
    }

    @Test
    public void testVolumeBoundaries() {
        Radio radio = new Radio();
        radio.setCurrentVolume(0);
        radio.decreaseVolume();
        assertEquals(0, radio.getCurrentVolume()); // Проверяем, что громкость не уменьшается ниже 0

        radio.setCurrentVolume(100);
        radio.increaseVolume();
        assertEquals(100, radio.getCurrentVolume()); // Проверяем, что громкость не увеличивается выше 100
    }

    @Test
    public void testStationBoundaries() {
        Radio radio = new Radio();
        radio.setCurrentStation(0);
        radio.prevStation();
        assertEquals(9, radio.getCurrentStation()); // Проверяем, что станция переключается на 9 при достижении 0

        radio.setCurrentStation(9);
        radio.nextStation();
        assertEquals(0, radio.getCurrentStation()); // Проверяем, что станция переключается на 0 при достижении 9
    }
}