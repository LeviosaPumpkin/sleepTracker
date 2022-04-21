package leviosa.pumpkin.sleepstatistic.httpclients;

import io.micronaut.core.convert.format.Format;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.http.client.annotation.Client;
import leviosa.pumpkin.sleepstatistic.domain.SleepRecord;

import java.util.Date;
import java.util.List;

@Client(id = "sleepservice")
public interface SleepServiceClient {
    @Get("/sleep/records")
    List<SleepRecord> findByUserIdAndDates(@QueryValue long userId,
                                @Format("yyyy-MM-dd'T'HH:mm:ss") @QueryValue Date dateFrom,
                                @Format("yyyy-MM-dd'T'HH:mm:ss") @QueryValue Date dateTo);
}
