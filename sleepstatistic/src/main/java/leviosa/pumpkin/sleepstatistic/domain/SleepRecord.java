package leviosa.pumpkin.sleepstatistic.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class SleepRecord {
    private long id;
    private long userId;
    private Date date;
    private int hours;

    public SleepRecord() {
    }
    @JsonCreator
    public SleepRecord(@JsonProperty("id") long id,
                       @JsonProperty("userId") long userId,
                       @JsonProperty("date") Date date,
                       @JsonProperty("hours") int hours) {
        this.id = id;
        this.userId = userId;
        this.date = date;
        this.hours = hours;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }
}
