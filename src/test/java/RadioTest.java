import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class RadioTest {
    private Radio radio;

    @BeforeEach
    void setUp() {
        radio = new Radio();
    }

    @Test
    @DisplayName("Создание радио с настройками по умолчанию")
    void shouldCreateDefaultRadio() {
        assertEquals(10, radio.getStationsCount());
        assertEquals(0, radio.getCurrentStation());
        assertEquals(0, radio.getCurrentVolume());
        assertFalse(radio.isOn());
    }

    @Test
    @DisplayName("Создание радио с кастомным количеством станций")
    void shouldCreateCustomRadio() {
        Radio customRadio = new Radio(5);
        assertEquals(5, customRadio.getStationsCount());
    }

    @Test
    @DisplayName("Нельзя создать радио с недопустимым количеством станций")
    void shouldNotAllowInvalidStationsCount() {
        assertThrows(IllegalArgumentException.class, () -> new Radio(0));
        assertThrows(IllegalArgumentException.class, () -> new Radio(-1));
    }

    @Nested
    @DisplayName("Тесты управления питанием")
    class PowerTests {
        @Test
        @DisplayName("Включение радио")
        void shouldTurnOn() {
            radio.togglePower();
            assertTrue(radio.isOn());
        }

        @Test
        @DisplayName("Выключение радио")
        void shouldTurnOff() {
            radio.togglePower();
            radio.togglePower();
            assertFalse(radio.isOn());
        }
    }

    @Nested
    @DisplayName("Тесты переключения станций (радио включено)")
    class StationTestsWhenOn {
        @BeforeEach
        void setUp() {
            radio.togglePower();
        }

        @Test
        @DisplayName("Переключение на следующую станцию")
        void shouldSwitchToNextStation() {
            radio.setCurrentStation(3);
            radio.next();
            assertEquals(4, radio.getCurrentStation());
        }

        @Test
        @DisplayName("Переключение на следующую после последней станции")
        void shouldWrapAroundFromLastStation() {
            radio.setCurrentStation(9);
            radio.next();
            assertEquals(0, radio.getCurrentStation());
        }

        @Test
        @DisplayName("Переключение на предыдущую станцию")
        void shouldSwitchToPreviousStation() {
            radio.setCurrentStation(3);
            radio.prev();
            assertEquals(2, radio.getCurrentStation());
        }

        @Test
        @DisplayName("Переключение на предыдущую перед первой станции")
        void shouldWrapAroundFromFirstStation() {
            radio.setCurrentStation(0);
            radio.prev();
            assertEquals(9, radio.getCurrentStation());
        }

        @Test
        @DisplayName("Установка корректного номера станции")
        void shouldSetValidStation() {
            radio.setCurrentStation(5);
            assertEquals(5, radio.getCurrentStation());
        }

        @Test
        @DisplayName("Нельзя установить недопустимый номер станции")
        void shouldNotSetInvalidStation() {
            assertThrows(IllegalArgumentException.class, () -> radio.setCurrentStation(-1));
            assertThrows(IllegalArgumentException.class, () -> radio.setCurrentStation(10));
        }
    }

    @Nested
    @DisplayName("Тесты управления громкостью (радио включено)")
    class VolumeTestsWhenOn {
        @BeforeEach
        void setUp() {
            radio.togglePower();
        }

        @Test
        @DisplayName("Увеличение громкости")
        void shouldIncreaseVolume() {
            radio.increaseVolume();
            assertEquals(1, radio.getCurrentVolume());
        }

        @Test
        @DisplayName("Увеличение громкости до максимума")
        void shouldNotIncreaseAboveMaxVolume() {
            for (int i = 0; i < 110; i++) {
                radio.increaseVolume();
            }
            assertEquals(100, radio.getCurrentVolume());
        }

        @Test
        @DisplayName("Уменьшение громкости")
        void shouldDecreaseVolume() {
            radio.increaseVolume();
            radio.decreaseVolume();
            assertEquals(0, radio.getCurrentVolume());
        }

        @Test
        @DisplayName("Уменьшение громкости до минимума")
        void shouldNotDecreaseBelowMinVolume() {
            radio.decreaseVolume();
            assertEquals(0, radio.getCurrentVolume());
        }
    }

    @Nested
    @DisplayName("Тесты при выключенном радио")
    class WhenRadioIsOff {
        @Test
        @DisplayName("Нельзя переключать станции при выключенном радио")
        void shouldNotChangeStationWhenOff() {
            radio.next();
            radio.prev();
            radio.setCurrentStation(5);
            assertEquals(0, radio.getCurrentStation());
        }

        @Test
        @DisplayName("Нельзя изменять громкость при выключенном радио")
        void shouldNotChangeVolumeWhenOff() {
            radio.increaseVolume();
            radio.decreaseVolume();
            assertEquals(0, radio.getCurrentVolume());
        }
    }

    @Nested
    @DisplayName("Тесты для кастомного количества станций")
    class CustomStationsTests {
        @Test
        @DisplayName("Работа с 1 станцией")
        void shouldWorkWithSingleStation() {
            Radio singleStationRadio = new Radio(1);
            singleStationRadio.togglePower();
            singleStationRadio.next();
            assertEquals(0, singleStationRadio.getCurrentStation());
            singleStationRadio.prev();
            assertEquals(0, singleStationRadio.getCurrentStation());
        }

        @Test
        @DisplayName("Работа с большим количеством станций")
        void shouldWorkWithManyStations() {
            Radio manyStationRadio = new Radio(100);
            manyStationRadio.togglePower();
            manyStationRadio.setCurrentStation(99);
            manyStationRadio.next();
            assertEquals(0, manyStationRadio.getCurrentStation());
            manyStationRadio.prev();
            assertEquals(99, manyStationRadio.getCurrentStation());
        }
    }
}