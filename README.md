# SunDevs Technical Test

This is a technical test in order to show the experience about Spring (Boot and Data).

The test has 2 basic controller tests with MockMVC, it only tests the endpoints required, but the REST api works with the whole classic CRUD.

## Maven

The project works with Maven in order to build the .war file, so please run this command:

```
mvn install
```

## Docker

The project runs through docker compose based on a docker file, in order to make it run you need to have installed docker on your local machine, once you have it, run the following command inside of the root folder of the project (./sundevs/):

```
docker-compose up
```

This command will run the instance based on the Docker file configuration that is also at the root folder of the project.

# Endpoints

You can access the endpoints for getting all registered users here like this

```
curl --location --request GET 'http://localhost:8080/sundevs/api/v1/users'
```

And the creation of a user like this:

```
curl --location --request POST 'http://localhost:8080/sundevs/api/v1/users' \
--header 'Content-Type: application/json' \
--data-raw '{	        
        "firstName": "Diego",
        "lastName": "Vargas",
        "address": "Cra x Calle y",
        "city": "Medellín",
        "state": "Antioquia",
        "zip": "0123456789"
    }'
```

# Documentation

For a detailed information about the REST API, once you run it, go to the following url:

```
http://localhost:8080/sundevs/swagger-ui.html
```

Enjoy it !

