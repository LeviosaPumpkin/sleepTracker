package leviosa.pumpkin.sleepstatistic.service;

import leviosa.pumpkin.sleepstatistic.domain.UserStatistic;

import java.util.Date;
import java.util.OptionalDouble;

public interface StatisticService {
    OptionalDouble calculate(long userId, Date dateFrom, Date dateTo);
    void saveInRedis(long userId, Double value);
    UserStatistic getByUserId(long userId);
}
