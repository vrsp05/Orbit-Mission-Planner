# Orbit Mission Planner

A console-based Java application that simulates launch vehicle logistics. This tool validates mission viability by calculating lift capacity against total payload mass and features a custom-built persistence layer to save and reload complex mission manifests.

![Java](https://img.shields.io/badge/Java-OpenJDK%2025-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![JUnit](https://img.shields.io/badge/JUnit-5-25A162?style=for-the-badge&logo=junit5&logoColor=white)

## Features

- **Mission Validation**: Real-time "Go/No-Go" checks comparing Total Mass vs. Lift Capacity.
- **Dynamic Payloads**: Add multiple variable-weight items (satellites, crew modules) to a single rocket.
- **Persistent Storage**: Custom CSV database engine that saves mission data to disk.
- **State Machine Parser**: Reconstructs hierarchical data (Rocket -> Payloads) from flat text files.
- **Interactive Menu**: Console-based UI for creating, viewing, and managing missions.
- **Input Validation**: Robust error handling for numeric inputs and file operations.

## Instructions for Build and Use

### Steps to build and/or run the software:

1. **Clone the repository**:

   git clone [https://github.com/vrsp05/OrbitMissionPlanner.git](https://github.com/vrsp05/OrbitMissionPlanner.git)
   cd OrbitMissionPlanner
  
2. **Open the project in VS Code**:
   - Open the folder containing the `src` and `lib` directories.
   - Ensure the "Extension Pack for Java" is installed.

3. **Run the application**:
   - Open `src/App.java`.
   - Click the **Run** button (Play Icon) or press `F5`.

4. **Run the Tests**:
   - Open the "Testing" tab (Flask icon) in VS Code.
   - Click the "Run All Tests" button to verify logic and persistence.

### Instructions for using the software:

YouTube Demo available [here](PLACE_YOUR_YOUTUBE_LINK_HERE).

1. **Main Menu**:
   - **Option 1**: Start a new mission simulation.
   - **Option 2**: View historical mission logs (loaded from database).
   - **Option 3**: Exit the application.

2. **Creating a Mission**:
   - Enter a Rocket Name (e.g., "Saturn V").
   - Enter Max Capacity (kg).
   - Add Payloads one by one (Name & Weight).
   - The system will prevent adding items that exceed remaining capacity.

3. **Saving Data**:
   - Upon a successful "Go" status, you will be prompted to save.
   - Data is written to `OrbitMissionDatabase.csv` (or your configured CSV file).

## Development Environment

To recreate the development environment, you need the following software and/or libraries:

* **Visual Studio Code** with Extension Pack for Java.
* **Java Development Kit (JDK) 24/25** (OpenJDK).
* **JUnit 5 Platform** (Standalone Console & Jupiter API) for unit testing.
* **Standard Java Libraries**:
  - `java.util.Scanner` - User input handling.
  - `java.util.ArrayList` - Dynamic list management for payloads.
  - `java.io.File` - File persistence operations.
  - `java.util.Scanner` (File Parsing) - Reading CSV data.

## File Structure

ORBIT-MISSION-PLANNER/
├── lib/
│   └── junit-platform-console-standalone-1.13.0-M3.jar
├── OrbitMissionPlanner/
│   ├── .vscode/
│   │   └── settings.json
│   ├── bin/                     # Compiled Java classes
│   │   ├── App.class
│   │   └── RocketTest.class
│   ├── lib/
│   └── src/                     # Source Code
│       ├── App.java
│       ├── FileManager.java
│       ├── FileManagerTest.java
│       ├── PayloadItem.java
│       ├── Rocket.java
│       └── RocketTest.java
├── .gitattributes
├── .gitignore
├── OrbitMissionDatabase.csv     # Persistent database file
└── README.md

## Code Architecture

The application follows a modular design separating logic, data, and user interaction:

* **Rocket**: The core logic unit. Encapsulates weight calculations and adheres to "Encapsulation" principles (private fields).
* **FileManager**: Handles all Input/Output. Uses a custom State Machine algorithm to parse line-by-line CSV data into structured Objects.
* **App**: The "Client" layer. It handles the `Scanner` for user input and orchestrates the flow between the Rocket and File Manager.

## Useful Websites to Learn More

I found these websites useful in developing this software:

* [Oracle Java Documentation](https://docs.oracle.com/en/java/) - Official Java reference.
* [JUnit 5 User Guide](https://junit.org/junit5/docs/current/user-guide/) - Essential for learning TDD assertions.
* [W3Schools Java File Handling](https://www.w3schools.com/java/java_files.asp) - Clear examples for File I/O.
* [GeeksforGeeks ArrayList](https://www.geeksforgeeks.org/arraylist-in-java/) - Guide on using Java Collections.

## Future Work

The following items I plan to fix, improve, and/or add to this project in the future:

* [ ] **Multi-Stage Logic**: Implement Delta-V calculations for multi-stage rockets.
* [ ] **Simulation Mode**: Add a random failure generator based on payload risk factors.
* [ ] **GUI Version**: Port the menu system to JavaFX for a graphical interface.
* [ ] **JSON Integration**: Replace the custom CSV parser with a standard JSON library for better data structure.
* [ ] **Search**: Ability to search for past missions by Rocket Name.

## License

This project is created for educational purposes.

## Author

Victor Santana - [GitHub Profile](https://github.com/vrsp05)
