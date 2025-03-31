public class Radio {
    private int currentStation;
    private int currentVolume;
    private final int stationsCount;
    private boolean isOn;
    private static final int DEFAULT_STATIONS_COUNT = 10;
    private static final int MAX_VOLUME = 100;
    private static final int MIN_VOLUME = 0;

    public Radio() {
        this(DEFAULT_STATIONS_COUNT);
    }

    public Radio(int stationsCount) {
        if (stationsCount <= 0) {
            throw new IllegalArgumentException("Количество станций должно быть положительным");
        }
        this.stationsCount = stationsCount;
        this.isOn = false;
    }

    public void next() {
        if (!isOn) return;
        currentStation = (currentStation + 1) % stationsCount;
    }

    public void prev() {
        if (!isOn) return;
        currentStation = (currentStation - 1 + stationsCount) % stationsCount;
    }

    public void setCurrentStation(int station) {
        if (!isOn) return;
        if (station < 0 || station >= stationsCount) {
            throw new IllegalArgumentException(String.format(
                    "Неверный номер станции. Должен быть от 0 до %d, получено: %d",
                    stationsCount - 1, station));
        }
        this.currentStation = station;
    }

    public void increaseVolume() {
        if (!isOn) return;
        if (currentVolume < MAX_VOLUME) {
            currentVolume++;
        }
    }

    public void decreaseVolume() {
        if (!isOn) return;
        if (currentVolume > MIN_VOLUME) {
            currentVolume--;
        }
    }

    public void togglePower() {
        isOn = !isOn;
    }

    public int getCurrentStation() {
        return currentStation;
    }

    public int getCurrentVolume() {
        return currentVolume;
    }

    public int getStationsCount() {
        return stationsCount;
    }

    public boolean isOn() {
        return isOn;
    }
}