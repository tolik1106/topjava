HW07 - curl запросы
=============================
-----------------------------
####AdminRestController

* ***Admin GET all:*** `curl http://localhost:8080/topjava/rest/admin/users`
* ***Admin POST create:*** `curl -i -H "Content-Type: application/json" -d "{\"name\": \"Tolik\",  \"email\": \"tolik@yandex.ru\", \"password\": \"11111\", \"roles\": [\"ROLE_ADMIN\"]}" http://localhost:8080/topjava/rest/admin/users`
* ***Admin GET byEmail:*** `curl http://localhost:8080/topjava/rest/admin/users/by?email=admin@gmail.com`
* ***Admin GET singleUser:*** `curl http://localhost:8080/topjava/rest/admin/users/100000`
* ***Admin PUT updateUser:*** `curl -i -H "Content-Type: application/json" -d "{\"name\": \"UserUpdated\",  \"email\": \"user@yandex.ru\", \"password\": \"newPassword\", \"roles\": [\"ROLE_USER\"]}" -X PUT http://localhost:8080/topjava/rest/admin/users/100000`
---
####ProfileRestController

* ***Profile GET getProfile:*** `curl http://localhost:8080/topjava/rest/profile`
* ***Profile PUT updateProfile:*** `curl -H "Content-Type: application/json" -d "{\"name\": \"New777\",  \"email\": \"new777@yandex.ru\", \"password\": \"passwordNew\", \"roles\": [\"ROLE_USER\"]}" -i -X PUT http://localhost:8080/topjava/rest/profile`
* ***Profile DELETE deleteProfile:*** `curl -i -X DELETE http://localhost:8080/topjava/rest/profile`
---
####MealRestController

* ***Meal GET singleMeal:*** `curl http://localhost:8080/topjava/rest/meals/100007`
* ***Meal PUT updateMeal:*** `curl -H "Content-Type: application/json" -d "{\"dateTime\": \"2015-05-21T15:30\", \"description\": \"NewDinner\", \"calories\": 200}" -i -X PUT http://localhost:8080/topjava/rest/meals/100007`
* ***Meals GET all:*** `curl http://localhost:8080/topjava/rest/meals`
* ***Meal POST create:*** `curl -i -H "Content-Type: application/json" -d "{\"dateTime\": \"2015-05-21T18:30\", \"description\": \"TolikDinner\", \"calories\": 600}" http://localhost:8080/topjava/rest/meals`
* ***Meal DELETE deleteMeal:*** `curl -i -X DELETE http://localhost:8080/topjava/rest/meals/100006`
* ***Meals GET between:*** `curl "http://localhost:8080/topjava/rest/meals/between?startDate=2015-05-30&endDate=2015-05-31&startTime=12:00&endTime=21:00"`