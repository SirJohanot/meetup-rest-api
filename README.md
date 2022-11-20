# meetup-rest-api

## Инструкция по запуску проекта

1. Скачать и установить контейнер Java-сервлетов(например, Apache Tomcat)
2. Подготовить базу данных для приложения
    * Создать базу данных PostgreSQL
    * Выполнить скрипты из `/databaseScripts/databaseScripts.sql` в новой базе данных(для создания схемы)
3. Создать war-артифакт приложения
    * Выполнить команду `./gradlew war`
    * Готовый артифакт приложения появится в директории `/build/libs/`
4. Развернуть war-файл в контейнере сервлетов
5. Запросы к API можно отправлять через URL вида: `http://localhost:8080/meetup-rest-api-0-0-1/meetup`

## Форматы запросов к API

* Получить все митапы\
  Пример запроса:

```http request
GET http://localhost:8080/meetup-rest-api-0-0-1/meetup HTTP/1.1
Content-Type: application/json

{
"filterParameters": {
"agenda": "meet"
},
"sortingParameters":[
"agenda"
]
}
```

где `filterParameters` и `sortingParameters` являются опциональными параметрами, задающими фильтрацию и сортировку
результатов. Поддерживаемые параметры митапов: `agenda`, `dateTime`, `organizer`

* Получить митап\
  Пример запроса:

```http request
GET http://localhost:8080/meetup-rest-api-0-0-1/meetup/{id} HTTP/1.1
```

где `{id}` - id запрашиваемого митапа

* Добавить новый митап\
  Пример запроса:

```http request
PUT http://localhost:8080/meetup-rest-api-0-0-1/meetup HTTP/1.1
Content-Type: application/json

{
"agenda": "Very Important Things",
"description": "We discuss some very important Things",
"organizer": "An important person",
"dateTime": "17.11.2022 11:41",
"location": "An important office"
}
```

* Удалить митап\
  Пример запроса:

```http request
DELETE http://localhost:8080/meetup-rest-api-0-0-1/meetup/{id} HTTP/1.1
```

где `{id}` - id удаляемого митапа

* Изменить митап\
  Пример запроса:

```http request
POST http://localhost:8080/meetup-rest-api-0-0-1/meetup/{id} HTTP/1.1
Content-Type: application/json

{
"agenda": "Very Important Things",
"description": "We discuss some very important Things",
"organizer": "An important person",
"dateTime": "17.11.2022 11:41",
"location": "An important office"
}
```

где `{id}` - id изменяемого митапа
