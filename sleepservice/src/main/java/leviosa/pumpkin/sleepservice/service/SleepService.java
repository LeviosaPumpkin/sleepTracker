package leviosa.pumpkin.sleepservice.service;

import leviosa.pumpkin.sleepservice.domain.SleepRecord;

import java.util.Date;
import java.util.List;

public interface SleepService {
    SleepRecord save(SleepRecord sleepRecord);
    SleepRecord get(long id);
    List<SleepRecord> findByUserIdAndDates(long userId, Date dateFrom, Date dateTo);
    void delete(long id);
}
