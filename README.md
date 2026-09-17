# Tetris Madness

A Java implementation of Tetris, built on the JGameGrid library, extended with new piece shapes, randomised piece drops, and game statistics tracking as part of the SWEN30006 Project 1 (Software Modelling and Design).

## Overview

Tetris Madness starts from a simple Tetris base game (7 standard tetrominoes: I, J, L, O, S, T, Z) and extends it with three "madness" features:

1. **New Tetris Pieces** — three additional shapes: Cross (`X`), Plus (`+`), and Slash (`/`), made up of 5, 5, and 3 blocks respectively. Unlike the original 7 pieces, these new shapes cannot be rotated — only moved left/right and dropped.
2. **Random Fall Down** — pieces spawn at a random position within the top 15×15 grid area (instead of always at the centre, `(6,0)`), and fall at a random speed (1, 2, or 3 rows per tick).
3. **Recording Statistics** — after each round, the game writes a `Statistics.txt` file to the project root logging the score and the count of each piece type played that round.

## Requirements

- Java SDK 21
- Gradle (via IntelliJ, same setup as Workshop 0)
- JGameGrid library ([docs](http://www.aplu.ch/home/apluhomex.jsp?site=45))

## Getting Started

### Clone the repo
```bash
git clone <your-repo-url>
cd SWEN30006_Project1
```

### Build & Run
Open the project in IntelliJ (Gradle project) and run via the `Driver` class — this is the fixed entry point required for automated testing. Alternatively:
```bash
./gradlew run
```

## Controls

| Key | Action |
|-----|--------|
| ← | Move piece left |
| → | Move piece right |
| ↑ | Rotate piece 90° clockwise (standard 7 pieces only — new shapes don't rotate) |
| ↓ | Drop piece (increase fall speed) |

## Game Rules

- Clearing a full row removes it, drops the blocks above down, and increases the score.
- Falling speed increases as more lines are cleared.
- The round ends when the grid fills up and a new piece can no longer drop in.

## Configuration

Game behaviour is driven by `.properties` files (in `app/src/main/resources` for dev, `app/src/test/resources` for automated testing):

- `features.1` — `active`/`inactive`: toggles the new piece shapes (Cross, Plus, Slash)
- `features.2` — `active`/`inactive`: toggles random spawn position and fall speed
- `isAuto` — `true`/`false`: enables auto mode, where piece movement is scripted rather than player-controlled
- `pieces` — ordered list of pieces to drop in auto mode
- `locations` — spawn locations per piece (used when Feature 2 is active)
- `speed` — fall speed per piece (used when Feature 2 is active)
- `actions` — scripted move sequence per piece (`L` = left, `R` = right, `T` = rotate, `-` = no action)

Feature 3 (statistics logging) is always active regardless of properties settings.

## Project Structure
SWEN30006_Project1/
├── app/
│ ├── lib/
│ ├── src/
│ │ ├── main/
│ │ │ ├── java/ # game source code (Driver entry point lives here)
│ │ │ └── resources/ # properties files, sprites
│ │ └── test/
│ │ ├── java/ # automated test cases (do not modify)
│ │ └── resources/ # test properties files (do not modify)
│ └── build.gradle
├── documentation/
│ ├── DomainClassDiagram.pdf
│ └── DesignClassDiagram.pdf
├── gradle/
├── settings.gradle
└── README.md
