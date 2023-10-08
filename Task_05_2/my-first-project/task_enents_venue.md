Task 05
Реализовать приложение:

### Событие

Участники
Площадка проведения
В одном событии может быть много участников, но одна площадка проведения

У одной площадки проведения может быть много событий

Один участник может участвовать в нескольких событиях

Необходимо сделать модели с нужными аннотациями

Получить базу данных (сделать ее скриншот)

### Реализовать endpoints:

Создать мероприятие
Создать площадку
Добавить в площадку мероприятие (пока без участников)



### 1. Создать мероприятие
### Description actions with Venues
Implement endpoints for working with the Venues list

Add Venue

### Examples of requests and responses
1. Add User
- requests:
  POST /api/venues
  Request body:
````json
{
  "venueName": "Carnegie Hall",
  "address": "881 7th Ave, New York, NY 10019, USA",
  "square": 485000.00
}
````
- responses:
  Status 201 CREATED, new venue has been added successful
  Status 400 Bad Request, Validation error
  Status 409 CONFLICT, There is already a venue with this venueName
  Response body:
````json
{
  "id":1, 
  "venueName": "Carnegie Hall",
  "address": "881 7th Ave, New York, NY 10019, USA",
  "square": 485000.00

}
````


### 2. Создать площадку
### Description actions with Events
Implement endpoints for working with the Events list

Add Event

### Examples of requests and responses
1. Add Event
- requests:
  POST /api/events
  Request body:
````json
{
  "title": "birthday party",
  "data": "15.06.2024"
}
````
- responses:
  Status 201 CREATED, new event has been added successful
  Status 400 Bad Request, Validation error
  Response body:
````json
{
  "id":1,
  "title": "birthday party",
  "data": "15.06.2024"
}
````


### 3. Добавить в площадку мероприятие (пока без участников)
### Description actions adding an event to the venue

POST /api/venues/{id-venues}/events
- requests:
-   Request body:
````json
{
  "title": "birthday party",
  "data": "15.06.2024"
}
`````
- response:
  Status 201 CREATED, new event has been added successful
  Status 400 Bad Request, Validation error
  Response body:
````json
{
  "title": "birthday party",
  "data": "15.06.2024",
  "venueId": 1
}
````