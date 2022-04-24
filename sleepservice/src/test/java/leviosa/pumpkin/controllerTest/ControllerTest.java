package leviosa.pumpkin.controllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import leviosa.pumpkin.sleepservice.domain.SleepRecord;
import leviosa.pumpkin.sleepservice.service.SleepService;
import leviosa.pumpkin.sleepservice.service.SleepServiceImpl;
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
public class ControllerTest {
    @Inject
    private SleepService sleepService;
    @Inject
    @Client("/")
    private HttpClient httpClient;

    @MockBean(SleepService.class)
    public SleepService sleepService() {
        return mock(SleepService.class);
    }

    @Test
    public void testSaveRecord() throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);
        Date date = format.parse("2022-04-09T00:00:00.000Z");
        when(sleepService.save(any())).thenReturn(new SleepRecord(10l, 1, date, 8));

        String requestBody = "{\n" +
                "\t\"userId\": 1,\n" +
                "\t\"date\": \"2022-04-09T00:00:00.000Z\",\n" +
                "\t\"hours\": 8\n" +
                "}";
        String rsp = httpClient.toBlocking().retrieve(HttpRequest.POST("/sleep/records",requestBody));
        then(rsp).equals("{\"id\":10,\"userId\":1,\"date\":1649451600000,\"hours\":8}");
    }
}
