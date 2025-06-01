# ayy_asteroid_game
Astroid Game for the Component Based Course

### Split Packaging:
2 Modules have been created that implments the SplitProvider interface.
They get compiled when running the `mvn clean install`-command.
However to have the packaging work correctly the `split.second.provider.jar`-file has to be moved to the `plugins`-folder

To test split packaging:
1. First uncomment the `<module>split.second.provider</module>`-line within the root pom.xml file.
2. Run: `mvn clean install`
3. Move the `split.second.provider-1.0.1-SNAPSHOT.jar` file to the `plugins`-folder
4. Run the application as normal using the: `java --module-path mods-mvn --class-path "libs/*" --module=Core/dk.anfra22.cbse.core.Main`-command

### Microservices:
For the microservice module: `ScoringSystem` you have to specifically run that module by itself, **before**
starting the main application!

To use Microservices:
1. Run: `mvn clean install`
2. Spin up the `ScoreSystem`-class inside of the `ScoringSystem` module
3. Run the application as normal: `java --module-path mods-mvn --class-path "libs/*" --module=Core/dk.anfra22.cbse.core.Main`

