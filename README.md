# nerdySoft_Java-Test
# Floor layout #

## How to start:
### 1. mvn clean install
### 2. docker build -t [name] .
### 3. Deploying the application
### 3.1. docker pull mysql:8.0.21
### 3.2. docker run --name localhost -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=[name] -d mysql:8.0.21
### 3.3. docker run -d -p 8080:8080 --name [name] --link localhost:mysql [name]

## How to use:
### Open page on: http://localhost:8080/
### Press ***Coordinate list***
### On http://localhost:8080/coordinates you can check list of saved coordinates and create new coordinates
### After you press ***New coordinates***, you go to http://localhost:8080/coordinates/add and you can to enter your coordinates
### After you created coordinates, you go to http://localhost:8080/coordinates/{id}. On this page you can see floor plan and coordinates
### On http://localhost:8080/coordinates/{id} you can edit it by cliking ***Edit*** or delete by cliking ***Delete***
