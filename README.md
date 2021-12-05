## Slot machine api

This project was developed with Java 11.

The project uses no external API-s.

The game reels are configurable via `application.properties`

Example request:

```
curl --header "Content-Type: application/json" \
  --request POST \
  --data '{"bet":"5"}' \
  http://localhost:8080/play
```

Example response:

```
{
    "initialBet": 5,
    "totalWin": 400,
    "playerBalance": 1278,
    "reelWindow": [
        [
            "QUEEN",
            "ACE",
            "KING"
        ],
        [
            "ACE",
            "KING",
            "NINE"
        ],
        [
            "QUEEN",
            "QUEEN",
            "QUEEN"
        ]
    ],
    "winningRows": [
        "VERTICAL_RIGHT"
    ]
}
```
### Running the project from command-line:
1. `gradlew bootRun`

### Running the project from IntelliJ:
1. Run the `SlotMachineApplication.java` file in `slot-api\src\main\java\ee\slot\machine\SlotMachineApplication.java`
