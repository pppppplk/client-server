package project.DTO;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * HallDTO - подобный объект, который хранит данных
 */
public class HallDTO {

    private Long id;
    private String name;


    private HallDTO(Long id, String name){
        this.id = id;
        this.name = name;
    }

    /**
     *
     * @param jsonObject - объект JSON
     * @return jsonObject c id и названием зала
     * @throws JSONException
     */


    static public HallDTO of(JSONObject jsonObject) throws JSONException {
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
