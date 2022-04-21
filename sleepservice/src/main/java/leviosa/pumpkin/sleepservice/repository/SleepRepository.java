package leviosa.pumpkin.sleepservice.repository;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import leviosa.pumpkin.sleepservice.domain.SleepRecord;

import java.util.Date;
import java.util.List;

@Repository
public interface SleepRepository extends CrudRepository<SleepRecord, Long> {
    List<SleepRecord> findByUserIdAndDateBetween(long userId, Date dateFrom, Date dateTo);
}
