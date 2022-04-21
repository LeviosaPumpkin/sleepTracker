package leviosa.pumpkin.sleepstatistic.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserStatistic {
    private long userId;
    private double averageHours;

    @JsonCreator
    public UserStatistic(@JsonProperty("userId") long userId, @JsonProperty("averageHours") double averageHours) {
        this.userId = userId;
        this.averageHours = averageHours;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public double getAverageHours() {
        return averageHours;
    }

    public void setAverageHours(double averageHours) {
        this.averageHours = averageHours;
    }
}
