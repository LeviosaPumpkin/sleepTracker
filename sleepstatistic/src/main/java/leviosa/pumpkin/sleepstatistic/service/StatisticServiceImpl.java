package leviosa.pumpkin.sleepstatistic.service;

import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.async.RedisAsyncCommands;
import io.lettuce.core.api.sync.RedisCommands;
import leviosa.pumpkin.sleepstatistic.domain.SleepRecord;
import leviosa.pumpkin.sleepstatistic.domain.UserStatistic;
import leviosa.pumpkin.sleepstatistic.httpclients.SleepServiceClient;

import javax.inject.Inject;
import java.util.Date;
import java.util.OptionalDouble;

public class StatisticServiceImpl implements StatisticService {
    @Inject
    private SleepServiceClient sleepServiceClient;
    @Inject
    private StatefulRedisConnection<String, String> redisConnection;

    @Override
    public OptionalDouble calculate(long userId, Date dateFrom, Date dateTo) {
        return sleepServiceClient.findByUserIdAndDates(userId, dateFrom, dateTo)
                .stream().mapToInt(SleepRecord::getHours).average();
    }

    @Override
    public void saveInRedis(long userId, Double value) {
        RedisAsyncCommands<String, String> commands = redisConnection.async();
        commands.set(String.valueOf(userId), value.toString());
    }

    @Override
    public UserStatistic getByUserId(long userId) {
        RedisCommands<String, String> commands = redisConnection.sync();
        return new UserStatistic(userId, Double.parseDouble(commands.get(String.valueOf(userId))));
    }
}
