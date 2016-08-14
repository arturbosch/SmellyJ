# SmellyJ

Proudly presenting an IntelliJ-Plugin which aims to help the developer discover code smells.
The goal of SmellyJ is to integrate SmartSmells (https://gitlab.com/ArtiSmarti/SmartSmells)
and detect most of the bad smells described in 'Refactoring' by Martin Fowler and other source.s

## Build

- Make sure you have gradle installed
- Build Jpal (https://gitlab.com/arturbosch/JPAL): gradle clean build publishToMavenLocal
- Build SmartSmells (https://gitlab.com/arturbosch/SmartSmells): gradle clean build publishToMavenLocal
- All remaining dependencies are downloaded from maven central
- Build SmellyJ: gradle buildPlugin
- Start IntelliJ and go to Plugins -> Install plugin from disk...
- Choose the SmellyJ zip within the build/distributions folder
- Restart IntelliJ

## Usage for M1

- Make sure to load a java project into IntelliJ
- Tools -> Analyze project with SmartSmells to analyze all project files
- Tools -> Show smells for current file is self-explainining

## Future plans

- After analysis smells will automaticly visualized when visiting new files
- Allow to use SmartSmells config file