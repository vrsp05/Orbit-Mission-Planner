# Orbit Mission Planner

A console-based Java application that simulates launch vehicle logistics. This tool validates mission viability by calculating lift capacity against total payload mass and features a custom-built persistence layer to save and reload complex mission manifests.

![Java](https://img.shields.io/badge/Java-OpenJDK%2025-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![JUnit](https://img.shields.io/badge/JUnit-5-25A162?style=for-the-badge&logo=junit5&logoColor=white)
![Status](https://img.shields.io/badge/Status-MVP%20Complete-success?style=for-the-badge)

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
   ```bash
   git clone [https://github.com/YOUR_USERNAME/OrbitMissionPlanner.git](https://github.com/YOUR_USERNAME/OrbitMissionPlanner.git)
   cd OrbitMissionPlanner
  
