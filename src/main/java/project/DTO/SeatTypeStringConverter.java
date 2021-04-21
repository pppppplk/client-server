package project.DTO;

import javafx.util.StringConverter;

/**
 * Конвертор для отобращения объекта  ChoiceBox'а
 * Реализующий два метода абстрактного класса StringConverter
 * Type
 */

public class SeatTypeStringConverter extends StringConverter<SeatDTO> {
    @Override
    public String toString(SeatDTO seatDTO) {
        return seatDTO.getType();
    }

    @Override
    public SeatDTO fromString(String s) {
        return null;
    }
}
