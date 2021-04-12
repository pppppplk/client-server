package project.DTO;

import org.json.JSONException;
import org.json.JSONObject;

public class SeatLocationDTO extends SeatDTO {

    protected SeatLocationDTO(Long id, String type, Integer location, HallDTO hall) {
        super(id, type, location, hall);
    }

    static public SeatLocationDTO of(JSONObject jsonObject) throws JSONException {
        return new SeatLocationDTO(
                jsonObject.getLong("id"),
                jsonObject.getString("type"),
                jsonObject.getInt("location"),
                HallDTO.of(jsonObject.getJSONObject("hall"))
        );
    }

    @Override
    public String toString() {
        return this.getLocation().toString();
    }
}
