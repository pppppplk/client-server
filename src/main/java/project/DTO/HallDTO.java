package project.DTO;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * HallDTO - подобный объект Hall, который хранит данных
 */
public class HallDTO {

    private Long id;
    private String name;


    private HallDTO(Long id, String name){
        this.id = id;
        this.name = name;
    }

    /**
     * Возврает объект класса HallDTO
     * @param jsonObject -  серверное Json представление объекта Hall
     * @return HallDTO
     * @throws JSONException
     */


    static public HallDTO instanceOf(JSONObject jsonObject) throws JSONException {
        return new HallDTO(
                jsonObject.getLong("id"),
                jsonObject.getString("name")

        );
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return  name ;
    }
}
