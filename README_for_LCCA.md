# Lose-Coupled Actions and Components

This project is a small Swing framework that shows how to keep actions and GUI components loosely coupled. 
Action objects are registered with an `ActionMediator` while components are wrapped by `ComponentHolder` classes and coordinated by a `CHolderMediator`. 
The mediators route events so that actions can manipulate components without direct references, making it easier to extend or replace UI parts.

## Goals
- Demonstrate decoupled actions and component holders
- Provide a basic menu bar and panels that can be extended
- Allow keyboard shortcuts to be configured at runtime

## Build and Run
Use Gradle to compile and launch the demo application.
```bash
# Build the project
./gradlew build

# Run the GUI
./gradlew run
```
The `run` task executes `MainStarter`, showing the demo window.

## How actions and component holders interact
Each action implements business logic in a subclass of `AbstActionMember` and is managed by the `ActionMediator`. 
UI components such as panels or windows are wrapped in subclasses of `AbstCHolderMember` and registered in the `CHolderMediator`. 
Both mediators know about each other so when a user performs an action, the mediator can notify the appropriate component holder to update the UI.

## License
This project is licensed under the terms of the MIT License. See [LICENSE](LICENSE) for details.

