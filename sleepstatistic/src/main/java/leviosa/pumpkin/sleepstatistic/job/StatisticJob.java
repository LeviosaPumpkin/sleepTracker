package leviosa.pumpkin.sleepstatistic.job;

import io.micronaut.scheduling.annotation.Scheduled;
import leviosa.pumpkin.sleepstatistic.service.StatisticService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.OptionalDouble;

@Singleton
public class StatisticJob {
    private static Logger log = LoggerFactory.getLogger(StatisticJob.class);
    @Inject
    private StatisticService statisticService;

    @Scheduled(fixedDelay = "5m")
    void execute() throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);
        Date dateFrom = format.parse("2022-04-06T22:00:00.000Z");
        Date dateTo = format.parse("2022-04-12T22:00:00.000Z");
        List<Integer> userIds = List.of(1);
        log.info("Job started");
        userIds.forEach(userId -> {
            OptionalDouble average = statisticService.calculate(userId, dateFrom, dateTo);
            average.ifPresent(a -> statisticService.saveInRedis(userId, a));
        });
        log.info("Job ended");
    }
}
