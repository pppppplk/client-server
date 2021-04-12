package project.DTO;

import org.json.JSONException;
import org.json.JSONObject;

public class SeatDTO  {

    private Long id;
    private String type;
    private Integer location;
    private HallDTO hall;


    protected SeatDTO(Long id, String type, Integer location, HallDTO hall){
        this.id = id;
        this.type = type;
        this.location = location;
        this.hall = hall;
    }


    static public SeatDTO of(JSONObject jsonObject) throws JSONException {
        return new SeatDTO(
                jsonObject.getLong("id"),
                jsonObject.getString("type"),
                jsonObject.getInt("location"),
                HallDTO.of(jsonObject.getJSONObject("hall"))


        );
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getLocation() {
        return location;
    }

    public void setLocation(Integer location) {
        this.location = location;
    }

    public HallDTO getHall() {
        return hall;
    }

    public void setHall(HallDTO hall) {
        this.hall = hall;
    }


    @Override
    public String toString() {
        return type;
    }
}
