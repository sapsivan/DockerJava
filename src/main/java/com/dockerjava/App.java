package com.dockerjava;

/**
 * Simple Java application that demonstrates Docker containerization
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello from Docker Java Application!");
        System.out.println("Java Version: " + System.getProperty("java.version"));
        System.out.println("Operating System: " + System.getProperty("os.name"));
        System.out.println("Application is running successfully in a containerized environment.");
        
        // Display any command line arguments
        if (args.length > 0) {
            System.out.println("\nCommand line arguments:");
            for (int i = 0; i < args.length; i++) {
                System.out.println("  arg[" + i + "]: " + args[i]);
            }
        }
    }
}
