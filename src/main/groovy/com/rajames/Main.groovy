package com.rajames


import com.pi4j.Pi4J
import com.pi4j.context.Context
import com.pi4j.io.gpio.digital.DigitalInput
import com.pi4j.io.gpio.digital.DigitalInputConfigBuilder
import com.pi4j.io.gpio.digital.DigitalOutput
import com.pi4j.io.gpio.digital.DigitalOutputConfigBuilder
import com.pi4j.io.gpio.digital.DigitalState
import com.pi4j.io.gpio.digital.PullResistance

class Main {


    private static int pressCount = 0

    public static final String LED_PROVIDER = "pigpio-digital-output"
    public static final String BUTTON_PROVIDER = "pigpio-digital-input"

    static void main(String[] args) throws Exception {

        String osName = System.getProperty("os.name").toLowerCase()
        String osArch = System.getProperty("os.arch").toLowerCase()
        if (!(osName.startsWith("linux") && osArch.startsWith("arm"))) {
            System.err.println("Not a Raspberry Pi. Exiting.")
            System.exit(1)
        }
        if (!isRoot()) {
            System.err.println("Application must be run as root!")
            System.exit(1)
        }

        Properties props = new Properties()
        InputStream input = null

        // Check if a command line argument exists (a file path for properties)
        if (args.length != 0) {
            try {
                // Try to open the file
                input = new FileInputStream(new File(args[0]))
            } catch (FileNotFoundException ignored) {
                println "Specified properties file not found."
                System.exit(1)
            }
        } else {
            // Default to bundled properties file in the jar
            input = Thread.currentThread().getContextClassLoader().getResourceAsStream('pi4j.properties')
        }

        try {
            input.withCloseable { InputStream stream ->
                props.load(stream)
            }
        } catch (Exception ignored) {
            println "Could not load properties."
            System.exit(1)
        }

        Context pi4j = Pi4J.newAutoContext()

        DigitalOutputConfigBuilder ledConfig = DigitalOutput.newConfigBuilder(pi4j)
                .id(props.getProperty("led.id"))
                .name(props.getProperty("led.name"))
                .address(Integer.parseInt(props.getProperty("led.pin")))
                .shutdown(DigitalState.LOW)
                .initial(DigitalState.LOW)
                .provider(LED_PROVIDER)
        DigitalOutput led = pi4j.create(ledConfig)

        DigitalInputConfigBuilder buttonConfig = DigitalInput.newConfigBuilder(pi4j)
                .id(props.getProperty("button.id"))
                .name(props.getProperty("button.name"))
                .address(Integer.parseInt(props.getProperty("button.pin")))
                .pull(PullResistance.PULL_DOWN)
                .debounce(Long.parseLong(props.getProperty("button.debounce")))
                .provider(BUTTON_PROVIDER)
        DigitalInput button = pi4j.create(buttonConfig)
        button.addListener(e -> {
            if (e.state() == DigitalState.LOW) {
                pressCount++
            }
        })

        while (pressCount < Integer.parseInt(props.getProperty("flash.limit"))) {
            //noinspection GrEqualsBetweenInconvertibleTypes
            if (led == DigitalState.HIGH) {
                led.low()
            } else {
                led.high()
            }
            Thread.sleep(500 / (pressCount + 1) as long)
        }

        // Shutdown Pi4J
        pi4j.shutdown()
        System.exit(0)
    }

    static boolean isRoot() {
        try {
            'id -u'.execute().text.trim() == '0'
        } catch(Exception ignored) {
            System.err.println("Error determining root status.")
            System.exit(1)
        }
    }
}
