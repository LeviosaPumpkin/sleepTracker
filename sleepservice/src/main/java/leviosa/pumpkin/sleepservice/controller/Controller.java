package leviosa.pumpkin.sleepservice.controller;

import io.micronaut.core.convert.format.Format;
import io.micronaut.http.annotation.*;
import leviosa.pumpkin.sleepservice.domain.SleepRecord;
import leviosa.pumpkin.sleepservice.service.SleepService;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

@io.micronaut.http.annotation.Controller("/sleep")
public class Controller {
    @Inject
    private SleepService sleepService;

    @Post("/records")
    public SleepRecord save(@Body SleepRecord sleepRecord) {
        return sleepService.save(sleepRecord);
    }

    @Get("/records")
    public List<SleepRecord> findByUserIdAndDates(@QueryValue long userId,
                                                  @Format("yyyy-MM-dd'T'HH:mm:ss") @QueryValue Date dateFrom,
                                                  @Format("yyyy-MM-dd'T'HH:mm:ss") @QueryValue Date dateTo) {
        return sleepService.findByUserIdAndDates(userId, dateFrom, dateTo);
    }

    @Get("/records/{id}")
    public SleepRecord get(@PathVariable long id) {
        return sleepService.get(id);
    }

    @Delete("/records/{id}")
    public void delete(@PathVariable long id) {
        sleepService.delete(id);
    }


}