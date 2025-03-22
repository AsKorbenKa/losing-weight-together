package ru.example.losingweighttogether.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.util.DefaultUriBuilderFactory;
import ru.example.losingweighttogether.client.BaseClient;
import ru.example.losingweighttogether.meal.dto.MealDto;

import java.util.List;

@Service
public class MealClient extends BaseClient {
    private static final String API_PREFIX = "/meal";

    @Autowired
    public MealClient(@Value("${LWT-server.url}") String serverUrl, RestTemplateBuilder builder) {
        super(
                builder
                        .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl + API_PREFIX))
                        .requestFactory(() -> new HttpComponentsClientHttpRequestFactory())
                        .build()
        );
    }

    public ResponseEntity<Object> create(MealDto meal) {
        return post("", meal);
    }

    public ResponseEntity<Object> createMealRecords(Long userId, List<MealDto> eatenMeal) {
        return post("/" + userId, userId, eatenMeal);
    }
}
