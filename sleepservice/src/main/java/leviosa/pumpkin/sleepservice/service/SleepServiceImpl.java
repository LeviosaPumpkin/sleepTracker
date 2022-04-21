package leviosa.pumpkin.sleepservice.service;

import leviosa.pumpkin.sleepservice.domain.SleepRecord;
import leviosa.pumpkin.sleepservice.repository.SleepRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class SleepServiceImpl implements SleepService {
    @Inject
    private SleepRepository sleepRepository;
    @Override
    public SleepRecord save(SleepRecord sleepRecord) {
        return sleepRecord.getId() == null ? sleepRepository.save(sleepRecord) : sleepRepository.update(sleepRecord);
    }

    @Override
    public SleepRecord get(long id) {
        return sleepRepository.findById(id).orElse(null);
    }

    @Override
    public List<SleepRecord> findByUserIdAndDates(long userId, Date dateFrom, Date dateTo) {
        return sleepRepository.findByUserIdAndDateBetween(userId, dateFrom, dateTo);
    }

    @Override
    public void delete(long id) {
        Optional<SleepRecord> sleepRecord = sleepRepository.findById(id);
        sleepRecord.ifPresent(r -> sleepRepository.delete(r));
    }
}
