public class Radio {
    private int currentStation;
    private int currentVolume;
    private final int stationsCount;

    public Radio() {
        this(10); // По умолчанию 10 станций
    }

    public Radio(int stationsCount) {
        if (stationsCount <= 0) {
            throw new IllegalArgumentException("Количество станций должно быть положительным");
        }
        this.stationsCount = stationsCount;
    }

    public void next() {
        currentStation = (currentStation == stationsCount - 1) ? 0 : currentStation + 1;
    }

    public void prev() {
        currentStation = (currentStation == 0) ? stationsCount - 1 : currentStation - 1;
    }

    public void setCurrentStation(int station) {
        if (station < 0 || station >= stationsCount) {
            throw new IllegalArgumentException("Неверный номер станции. Должен быть от 0 до " + (stationsCount - 1));
        }
        this.currentStation = station;
    }

    public void increaseVolume() {
        if (currentVolume < 100) {
            currentVolume++;
        }
    }

    public void decreaseVolume() {
        if (currentVolume > 0) {
            currentVolume--;
        }
    }

    // Геттеры
    public int getCurrentStation() {
        return currentStation;
    }

    public int getCurrentVolume() {
        return currentVolume;
    }

    public int getStationsCount() {
        return stationsCount;
    }
}