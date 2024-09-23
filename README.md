# Шаблон приложения Kora Java CRUD

Пример сервиса реализованного на Kora с HTTP [CRUD](https://github.com/swagger-api/swagger-petstore) API, 
в качестве базы данных выступает Postgres, используется кэш Caffeine, а также другие модули которые использовались бы в реальном приложении в бою.

В примере использовались модули:
- [HTTP сервер](https://kora-projects.github.io/kora-docs/ru/documentation/http-server/)
- [OpenAPI HTTP серверная генерация](https://kora-projects.github.io/kora-docs/ru/documentation/openapi-codegen/)
- [Пробы](https://kora-projects.github.io/kora-docs/ru/documentation/probes/)
- [Метрики](https://kora-projects.github.io/kora-docs/ru/documentation/metrics/)
- [JDBC база данных](https://kora-projects.github.io/kora-docs/ru/documentation/database-jdbc/)
- [JSON конвертация](https://kora-projects.github.io/kora-docs/ru/documentation/json/)
- [Отказоусточивость](https://kora-projects.github.io/kora-docs/ru/documentation/resilient/)
- [Валидация](https://kora-projects.github.io/kora-docs/ru/documentation/validation/)
- [Caffeine кеш](https://kora-projects.github.io/kora-docs/ru/documentation/cache/#caffeine)

## Build

Собрать артефакт:

```shell
./gradlew distTar
```

### Generate

Сгенерировать API для HTTP Server:
```shell
./gradlew openApiGenerateHttpServer
```

## Run

Запустить локально:
```shell
./gradlew run
```

## Test

Тесты используют [Testcontainers](https://java.testcontainers.org/), требуется [Docker](https://docs.docker.com/engine/install/) окружение для запуска тестов или аналогичные контейнерные окружения ([colima](https://github.com/abiosoft/colima) / итп)

Протестировать локально:
```shell
./gradlew test
```
