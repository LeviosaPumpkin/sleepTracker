package leviosa.pumpkin.sleepstatistic.servicetest;

import io.micronaut.context.ApplicationContext;
import io.micronaut.context.env.PropertySource;
import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import leviosa.pumpkin.sleepstatistic.domain.SleepRecord;
import leviosa.pumpkin.sleepstatistic.domain.UserStatistic;
import leviosa.pumpkin.sleepstatistic.httpclients.SleepServiceClient;
import leviosa.pumpkin.sleepstatistic.service.StatisticService;
import org.junit.ClassRule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.utility.DockerImageName;

import javax.inject.Inject;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@MicronautTest
@Testcontainers
public class StatisticServiceTest {
    private static final int REDIS_PORT = 6379;
    private static ApplicationContext context;

    @Inject
    private StatisticService statisticService;
    @Inject
    private SleepServiceClient sleepServiceClient;


    @Container
    static GenericContainer redis = new GenericContainer<>("redis:3.0.6")
            .withExposedPorts(REDIS_PORT);


    @BeforeAll
    public static void init() {
        context = ApplicationContext.run(PropertySource.of(
                "test", Map.of("redis.host", redis.getContainerIpAddress(), "redis.port", redis.getMappedPort(REDIS_PORT))
        ));
    }

    @MockBean(SleepServiceClient.class)
    public SleepServiceClient sleepServiceClient() {
        return mock(SleepServiceClient.class);
    }

    @Test
    public void testSave() throws ParseException {
        List<SleepRecord> list = List.of(
                new SleepRecord(2, 1, new Date(1649278800000L), 7),
                new SleepRecord(3, 1, new Date(1649365200000L), 8));
        //when(sleepServiceClient.findByUserIdAndDates(anyLong(), any(), any())).thenReturn(list);

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);
        Date dateFrom = format.parse("2022-04-06T22:00:00.000Z");
        Date dateTo = format.parse("2022-04-08T22:00:00.000Z");
        OptionalDouble avg = statisticService.calculate(1, dateFrom, dateTo);
        statisticService.saveInRedis(1, avg.getAsDouble());

        then(statisticService.getByUserId(1)).equals(new UserStatistic(1, 7.5));
    }
}
