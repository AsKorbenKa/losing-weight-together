package ru.example.losingweighttogether.statistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.util.DefaultUriBuilderFactory;
import ru.example.losingweighttogether.client.BaseClient;

import java.time.LocalDate;
import java.util.Map;

@Service
public class StatisticsClient extends BaseClient {
    private static final String API_PREFIX = "/statistics";

    @Autowired
    public StatisticsClient(@Value("${LWT-server.url}") String serverUrl, RestTemplateBuilder builder) {
        super(
                builder
                        .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl + API_PREFIX))
                        .requestFactory(() -> new HttpComponentsClientHttpRequestFactory())
                        .build()
        );
    }

    public ResponseEntity<Object> getUserStatistics(Long userId, LocalDate date) {
        Map<String, Object> parameters = Map.of(
                "date", date
        );

        return get("/" + userId + "?date={date}", userId, parameters);
    }
}
