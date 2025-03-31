
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
            System.out.println("On a scale of 1 to 10, how much pain is " + name + " in right now?");
            int painLevel = 0;
            boolean validPain = false;
            while (!validPain) {
                String input = userInput.nextLine();
                try {
                    painLevel = Integer.parseInt(input);
                    validPain = true;
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a number");
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
                patientInfo += "\n";  // Using newline as separator
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
        try {
            // Parse the new patient info
            String[] newPatientData = patientInfo.split(",");
            String newName = newPatientData[0];
            String newSpecies = newPatientData[1];
            String newAttribute = newPatientData[2]; // DroolRate or MiceCaught
            String newDay = newPatientData[3];
            String newEntryTime = newPatientData[4];
            String newExitTime = newPatientData[5];
            String newHealth = newPatientData[6];
            String newPainLevel = newPatientData[7];
            
            // Create a temporary list to store the updated file content
            StringBuilder fileContent = new StringBuilder();
            boolean patientExists = false;
            
            // Try to read the existing file
            if (patientFile.exists()) {
                Scanner fileScanner = new Scanner(patientFile);
                while (fileScanner.hasNextLine()) {
                    String line = fileScanner.nextLine();
                    
                    // Check if this line contains data for our patient
                    if (line.startsWith(newName + ",")) {
                        // This is an existing patient
                        patientExists = true;
                        
                        // For existing patients, keep their basic info and add new appointment info
                        fileContent.append(line).append(",")
                                 .append(newDay).append(",")
                                 .append(newEntryTime).append(",")
                                 .append(newExitTime).append(",")
                                 .append(newHealth).append(",")
                                 .append(newPainLevel);
                    } else {
                        // This is a different patient, keep their data as is
                        fileContent.append(line);
                    }
                    fileContent.append(System.lineSeparator());
                }
                fileScanner.close();
            }
            
            // If this is a new patient, add all their info
            if (!patientExists) {
                fileContent.append(patientInfo);
                fileContent.append(System.lineSeparator());
            }
            
            // Write the updated content back to the file
            PrintWriter writer = new PrintWriter(patientFile);
            writer.print(fileContent.toString());
            writer.close();
            
            return true;
        } catch (Exception e) {
            // If any error occurs, return false
            return false;
        }
    }

    private String addTime(String timeIn, int treatmentTime) {

        // Parse the input time in military format
        int time = Integer.parseInt(timeIn);

        // Extract hours and minutes
        int hours = time / 100;
        int minutes = time % 100;

        // Convert all to minutes
        int totalMinutes = hours * 60 + minutes + treatmentTime;

        // Convert back toh ours and minutes
        int newHours = (totalMinutes / 60) % 24;
        int newMinutes = (totalMinutes % 60);

        // Format as military time
        return String.format("%d%02d", newHours, newMinutes);

    }

}

