package leviosa.pumpkin.servicetest;

import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import leviosa.pumpkin.sleepservice.domain.SleepRecord;
import leviosa.pumpkin.sleepservice.repository.SleepRepository;
import leviosa.pumpkin.sleepservice.service.SleepService;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@MicronautTest
public class SleepServiceTest {
    @Inject
    private SleepRepository sleepRepository;
    @Inject
    private SleepService sleepService;

    @MockBean(SleepRepository.class)
    public SleepRepository sleepRepository() {
        return mock(SleepRepository.class);
    }

    @Test
    public void saveTest() throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);
        Date date = format.parse("2022-04-09T00:00:00.000Z");
        SleepRecord sleepRecordNew = new SleepRecord(null, 1, date, 8);
        SleepRecord sleepRecordSaved = new SleepRecord(10l, 1, date, 8);

        when(sleepRepository.save(any())).thenReturn(sleepRecordSaved);
        SleepRecord sleepRecord = sleepService.save(sleepRecordNew);
        then(sleepRecord).equals(sleepRecordSaved);

        sleepRecord.setHours(7);
        sleepRecordSaved.setHours(7);
        when(sleepRepository.save(any())).thenReturn(sleepRecordSaved);
        sleepRecord = sleepService.save(sleepRecordNew);
        then(sleepRecord).equals(sleepRecordSaved);
    }
}
