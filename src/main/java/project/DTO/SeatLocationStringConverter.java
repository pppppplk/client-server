package project.DTO;

import javafx.util.StringConverter;

/**
 * Конвертор для отобращения объекта  ChoiceBox'а
 * Реализующий два метода абстрактного класса StringConverter
 * Location
 */

public class SeatLocationStringConverter extends StringConverter<SeatDTO> {

    @Override
    public String toString(SeatDTO seatDTO) {
        return seatDTO.getLocation().toString();
    }

    @Override
    public SeatDTO fromString(String s) {
        return null;
    }
}
