package leviosa.pumpkin.sleepstatistic.controller;

import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import leviosa.pumpkin.sleepstatistic.domain.UserStatistic;
import leviosa.pumpkin.sleepstatistic.service.StatisticService;

import javax.inject.Inject;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

@io.micronaut.http.annotation.Controller("/statistic")
public class Controller {
    @Inject
    private StatisticService statisticService;

    @Get("/{userId}")
    public UserStatistic get(@PathVariable long userId) throws ParseException {
        return statisticService.getByUserId(userId);
    }
}
