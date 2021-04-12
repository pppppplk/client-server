package project.DTO;

import org.json.JSONException;
import org.json.JSONObject;

public class PerformanceDTO {
    private Long id;
    private String name,timeofpremier,timeofend,time ;
    private Integer agelimit;
    private HallDTO hall;

    public PerformanceDTO() {
    }

    protected PerformanceDTO(Long id, String name, String timeofpremier, String timeofend, String time, int agelimit, HallDTO hall) {
        this.id = id;
        this.name = name;
        this.timeofpremier = timeofpremier;
        this.timeofend = timeofend;
        this.time = time;
        this.agelimit = agelimit;
        this.hall = hall;
    }

    static public PerformanceDTO of(JSONObject jsonObject) throws JSONException {
        return new PerformanceDTO(
                jsonObject.getLong("id"),
                jsonObject.getString("name"),
                jsonObject.getString("timeofpremier"),
                jsonObject.getString("timeofend"),
                jsonObject.getString("time"),
                jsonObject.getInt("agelimit"),
                HallDTO.of(jsonObject.getJSONObject("hall"))
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

    public HallDTO getHall() {
        return hall;
    }

    public void setHall(HallDTO hall) {
        this.hall = hall;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTimeofpremier() {
        return timeofpremier;
    }

    public void setTimeofpremier(String timeofpremier) {
        this.timeofpremier = timeofpremier;
    }

    public String getTimeofend() {
        return timeofend;
    }

    public void setTimeofend(String timeofend) {
        this.timeofend = timeofend;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getAgelimit() {
        return agelimit;
    }

    public void setAgelimit(Integer agelimit) {
        this.agelimit = agelimit;
    }

    @Override
    public String toString() {
        return name;
    }


}
