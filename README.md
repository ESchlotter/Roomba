# A service that navigates a roomba
A simple service with an api to control a roomba around a room cleaning any patches it finds and stops at walls.

# Start-up instructions
Run the application through an IDE like IntelliJ or run through Maven.

## Input

Example input that can be sent through postman to localhost:8080/hoover/records
```json
{
  "roomSize" : [5, 5],
  "coords" : [1, 2],
  "patches" : [
    [1, 0],
    [2, 2],
    [2, 3]
  ],
  "instructions" : "NNESEESWNWW"
}
```

## Output

Output for the above should be: 

```json
{
  "coords" : [1, 3],
  "patches" : 1
}
```

A get request can also be done to localhost:8080/hoover/records to receive all of the records so far. 

# Pre-requisites
- Java 11
- JUnit and Mockito were used for testing.
- Lombok (IntelliJ/Eclipse plugin for annotation processing in your local environment)

# Suggested improvements
- Application does not have any security currently.
- Should replace the database with something more robust like Postgres which works fine with jpa
- Testing could be improved and proper integration tests should be introduced
- Edge cases should also be tested
- Further improvements could be made such as maintaining the location of the roomba in the room for the next request
- Roomba methods could be used for cleaning the room using some kind of algorithm
- I tried to make the program as modular as possible in the time given and also have room for further add-ons and improvements