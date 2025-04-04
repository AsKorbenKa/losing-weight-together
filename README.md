# Losing Weight Together - микросервисное приложение, помогающее следить за весом

Java, Spring Boot, Spring JPA, Apache Maven, PostgreSQL, REST API, Docker, JUnit, Lombok

# О проекте

Сервис, который позволяет рассчитать суточную норму калорий по вашим параметрам, сохранить данные о блюде, запомнить 
ваши приемы пищи и проверить следуете ли своей цели.

Приложение содержит микросервисы:

- Gateway для валидации запросов,
- Server, содержащий бизнес-логику

Каждый микросервис запускается в собственном Docker контейнере.
# Основная функциональность:

- Регистрация пользователей
- Добавление данных о блюдах, включающих калорийность, белки, жиры и углеводы
- Сохранение ваших приемов пищи с датой и общей калорийностью съеденного 
- Вычисление суточной нормы калорий
- Получение статистики за указанный день с общим количеством съеденных калорий, количеством приемов пищи и указанием 
достигли ли вы поставленной цели

# Эндпоинты

    POST /users - добавление пользователя


    POST /meal - добавление блюда
    POST /meal/{userId} - сохраниние приема пищи


    GET /statistics/{userId} - получение статистики за указанный день

# Инструкция запуска проекта

- Собираем .jar через команду Maven package
- Запускаем докер
- В терминале IDEA прописываем 
```shell
docker-compose build
```
- Создадутся образы, после чего выполняем
```shell
docker-compose up
```
- Запустится контейнер. Далее с помощью Postman выполняете запросы эндпоинты выше. Gateway работает через порт 8080, 
поэтому запрос начинается с
```shell
localhost:8080
```

### Примеры body запросов

- Создание пользователя
```json
{
  "name": "Ноэль",
  "gender": "MALE",
  "email": "noel@gmail.com",
  "age": "26",
  "weightInKg": "67",
  "heightInCm": "183",
  "goal": "WEIGHT_LOSS"
}
```
- Добавление блюда
```json
{
  "name": "Пирожное Павлова",
  "kkal": 123.3,
  "protein": 1.10,
  "fats": 23.23,
  "carb": 12.00
}
```
- Добавление данных о приёме пищи
```json
[
  {
    "name": "Пирожное Павлова",
    "kkal": 123.3,
    "protein": 1.10,
    "fats": 23.23,
    "carb": 12.00
  },
  {
    "name": "Борщ",
    "kkal": 111.3,
    "protein": 13.10,
    "fats": 14.14,
    "carb": 3.00
  }
]
```
