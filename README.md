# Pi4J Groovy example

My first Pi4J program in Groovy based on pi4j-example-minimal-main

![](./etc/led-button_bb.png)

Wring diagram for the breadboard.

# Groovy Pi4J Project

This project demonstrates controlling GPIO pins on a Raspberry Pi using the Pi4J library and the Groovy programming language. It focuses on handling a LED and a button, set up via the Pi4J library to interface with the respective GPIO pins.

## Features

- **LED Control:** The LED's state (ON/OFF) can be controlled programmatically.
- **Button Presses:** The button is set up with an event listener to keep track of button presses, which influence the LED functionality.
- **Properties:** The project loads configuration for the LED and button (like GPIO pin numbers and identifiers) from a properties file, allowing for easy adjustment without code changes.
- **Provider:** Provider details for the LED and the button have been made constant for dependable stability.

## Requirements

Before running this project, you should have the following installed:

1. **Java:** This project requires Java 17 or newer. You can download Java and find installation instructions on the [official Oracle website](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html).

2. **Groovy:** As this project's code base is written in Groovy, you'll need to have Groovy installed. You can download Groovy from the [official Groovy website](https://groovy.apache.org/download.html) and find installation instructions there.

3. **Maven:** This project uses Apache Maven for build automation, so you'll need to have Maven installed. You can download it from the [official Apache Maven website](https://maven.apache.org/download.cgi) and also find installation instructions there.

4. **Raspberry Pi Configuration:** Enable the GPIO pins on your Raspberry Pi if they aren't enabled already. You can enable them by running `sudo raspi-config`, navigating to `Interfacing Options > P8 GPIO` and selecting `Yes` to enable.

Please ensure that your Raspberry Pi's operating system is up-to-date and that you have installed the necessary tools to run Java and Groovy programs.

Ensure that you are following the correct safety protocols while working with the GPIO pins on your Raspberry Pi to prevent any damage.

## Usage:

1. Set up your Raspberry Pi with a connected LED and button.
2. Configure the pin numbers in your `pi4j.properties` file. *Optional
3. Run the `Main.groovy` script to start the program.
4. Press the button to influence the LED's blink rate.

## Building and Executing

This project is set up to build an executable jar, also known as an Uber or shaded jar, containing the project and all its dependency classes in a single file. This makes it easier to distribute and execute on the Raspberry Pi or other systems.

To build the jar, navigate to the project directory in the terminal and run the following Maven command: `$ mvn clean package`
This will compile all the code, run any tests, and package the compiled code and resources into a jar file in the `target` directory.

To execute the jar, you need to run it with superuser (sudo) privileges. This is because accessing the GPIO pins on the Raspberry Pi requires these privileges.

Here's the base command to run your program:
`$ java -jar target/your-artifact-id-your-version`
or
`$ java -jar target/your-artifact-id-your-version /path/to/your.properties`

## Using an External Properties File

The code in `Main.groovy` is designed to accommodate an external properties file, providing a flexible way to change the configuration of the GPIO pins. By default, the program uses a properties file named `pi4j.properties`.

However, you can specify an external properties file at the command line when starting the program. This is useful for changing the configuration without recompiling the code or modifying the packaged JAR file.

To use the external properties file, you pass its absolute path as a command-line argument when you start the program. For instance:
In this example, `com.rajames.Main` is the main class, and `/path/to/your.properties` is the absolute path to the properties file you want to use.

Follow these rules regarding the properties file:

- If no external file is specified, the program uses the default `pi4j.properties` file in the classpath.
- If an external file is specified and found, the program uses that.
- If an external file is specified but not found, the program exits with an error message.

In the properties file, you configure several pieces of information for each component (button, LED), including `id`, `name`, `pin` number, `debounce` (for button only), and `flash.limit` (total times that the button can be pressed).

## Maven Archetype:

This codebase is also set up as a Maven archetype, providing a template for creating similar Groovy + Pi4J projects.

### Creating and Installing the Archetype

This project is set up as a Maven archetype that serves as a template for creating new projects with similar setup and structure.

If you have made changes to the project and want to create and install the updated archetype, follow these steps:

1. **Clean the Project**: Before creating the archetype, clean the project and make sure it's in a ready state, i.e., all tests pass if any exist. Remove or ignore any user-specific configurations, as well as IDE-specific and build-related files.
2. **Generate Archetype**: From the command line, navigate to the root directory of the project and run: `mvn archetype:create-from-project`
3. **Locate Generated Archetype**: After the command finishes, the generated archetype will be located in the `target/generated-sources/archetype` directory.
4. **Install Archetype**: Navigate to the `target/generated-sources/archetype` directory and run: `mvn install`. This will install the archetype into your local Maven repository.

### Using the Archetype

After the archetype is installed, you can use it to generate new projects. To do so, run:

`mvn archetype:generate -DarchetypeGroupId=com.rajames -DarchetypeArtifactId=groovy-pi4j-archetype -DarchetypeVersion=1.0-SNAPSHOT -DgroupId=com.mycompany.myproject -DartifactId=mynewproject`
This command creates a new Maven project in the current directory, based on the template provided by the archetype.

## Summary

The project serves not only as ready-to-execute code, but also as a template for further Raspberry Pi endeavors using Groovy and Pi4j. It provides a compact yet flexible setup to control hardware via GPIO pins. Utilizing this repository, you can venture into creating more complex projects to unleash the full potential of IoT on a Raspberry Pi!

## Disclaimer

Please be aware that this project involves interfacing software with hardware. Improper use, failure to follow safety guidelines, or errors in your hardware setup could potentially damage your Raspberry Pi and related peripherals. Always double-check your wiring and software instructions, and don't hesitate to ask for help if you're unsure.

## Acknowledgments

This project is based on work from [The Pi4J Project](https://pi4j.com/) and their [Minimal Example Project](https://github.com/Pi4J/pi4j-example-minimal). The Pi4J Project provides a bridge between the native libraries of the Raspberry Pi and Java, allowing developers to control the GPIO interface programmatically. This project builds on their example, extending it with additional functionality and packaging it into a convenient Maven archetype.

A huge thank you to the contributors of The Pi4J Project for their fantastic work which made this project possible.

Please visit [The Pi4J Project website](https://pi4j.com/) for more details on using Java to control the GPIO interface on the Raspberry Pi.

## Conclusion

This Groovy project with Pi4J support serves as a powerful base to explore the capabilities of Raspberry Pi's GPIO functionality. By tweaking the properties file or applying this Maven archetype to spin up new projects, you can create complex and wide-ranging IoT tasks, and automate numerous hardware interactions.

Please remember to use this resource responsibly!

## License

This project is licensed under the terms of the [LICENSE](LICENSE) file included in this repository. Please refer to this file for all details regarding the project's license.

## Contributing

While this project isn't currently accepting contributions, you are absolutely welcome to fork it and modify it as you see fit. If you have any new and innovative ideas, feel free to implement them in your own version. Don't hesitate to let your creativity shine!

## Contact

If you have questions, suggestions, or anything you'd like to discuss about this project, feel free to reach out. You can contact me at [robjames44035@gmail.com](mailto:robjames44035@gmail.com). I'll try to respond as soon as I can. 


