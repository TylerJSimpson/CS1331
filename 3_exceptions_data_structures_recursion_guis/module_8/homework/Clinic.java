
import java.io.File;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileNotFoundException;

public class Clinic {
    
    // Variables
    
    private File patientFile;
    private int day;


    // Constructors

    public Clinic(File file) {
        this.patientFile = file;
        this.day = 1;
    }

    public Clinic(String fileName) {
        this(new File(fileName));
    }

    // Methods

    public String nextDay(File f) throws FileNotFoundException{

        // Scanner to read appointment data
        Scanner fileScanner = new Scanner(f);
        // Scanner to get user input during appointment
        Scanner userInput = new Scanner(System.in);

        // String to collect patient information
        String patientInfo = "";

        // Process each line in the file
        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            String[] parts = line.split(",");

            // Parse appointment details
            String name = parts[0];
            String petType = parts[1];
            double petAttribute = Double.parseDouble(parts[2]);
            int entryTime = Integer.parseInt(parts[3]);

            // Convert military time
            String formattedTime = "";
            int hours = entryTime / 100;
            int minutes = entryTime % 100;
            String amPm = "am";

            // Convert to 12 hour format
            if (hours >= 12) {
                amPm = "pm";
                if (hours > 12) {
                    hours -= 12;
                }
            }
            if (hours == 0) {
                hours = 12;
            }

            // Format time
            formattedTime = hours + ":" + (minutes < 10 ? "0" : "") + minutes + " " + amPm;

            // Validate pet type
            if (!petType.equals("Dog") && !petType.equals("Cat")) {
                fileScanner.close();
                throw new InvalidPetException("Pet type '" + petType + "' is not valid.");
            }

            // Gather health information
            System.out.println("Consultation for " + name + " the " + petType + " at " + formattedTime + ".");
            System.out.println("What is the health of " + name + "?");

            // get health information
            double health = 0.0;
            boolean validHealth = false;
            while (!validHealth) {
                try {
                    health = Double.parseDouble(userInput.nextLine());
                    validHealth = true;
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid number for health:");
                }
            }

            // Get pain level information 
            int painLevel = 0;
            boolean validPain = false;
            while (!validPain) {
                try {
                    painLevel = Integer.parseInt(userInput.nextLine());
                    validPain = true;
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid number for pain level:");
                }
            }

            // Create pet object based on type
            Pet pet;
            if (petType.equals("Dog")) {
                pet = new Dog(name, health, painLevel, petAttribute);
            } else {
                pet = new Cat(name, health, painLevel, (int)petAttribute);
            }

            // Have the pet speak
            pet.speak();

            // Treat the pet and get the treatment time in minutes
            int treatmentTime = pet.treat();

            // Calculate exit time using specified method
            String exitTimeStr = addTime(Integer.toString(entryTime), treatmentTime);
            int exitTime = Integer.parseInt(exitTimeStr);

            // Build patientInfo string
            if (!patientInfo.isEmpty()) {
                patientInfo += " ";
            }

            // Format patientInfo
            patientInfo += name + "," +
                          petType + "," +
                          (petType.equals("Dog") ? petAttribute : (int)petAttribute) + "," +
                          "Day " + day + "," +
                          entryTime + "," +
                          exitTime + "," +
                          health + "," +
                          painLevel;
        }

        fileScanner.close();

        // Increment day count
        day++;

        return patientInfo;

    }

    public String nextDay(String fileName) throws FileNotFoundException {
        return nextDay(new File(fileName));
    }

    public boolean addToFile(String patientInfo) {

    }

    private String addTime(String timeIn, int treatmentTime) {

        int time = Integer.parseInt(timeIn);

    }

}

