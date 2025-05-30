import java.util.Arrays;
import java.util.Collections;

public class BlueAstronaut extends Player implements Crewmate {
    
    /* VARIABLES */
    private int numTasks;
    private int taskSpeed;

    /* CONSTRUCTOR */
    public BlueAstronaut(String name) {
        super(name, 15);
        this.numTasks = 6;
        this.taskSpeed = 10;
    }

    public BlueAstronaut(String name, int susLevel, int numTasks, int taskSpeed) {
        super(name, susLevel);
        this.numTasks = numTasks;
        this.taskSpeed = taskSpeed;
    }

    /* METHODS */
    public void emergencyMeeting() {
        // Only players that are not frozen can call a meeting
        if (this.isFrozen()) {
            return;
        }

        // Get array of all players
        Player[] allPlayers = Player.getPlayers();
        
        // Create a copy of allPlayers and sort it by susLevel (descending)
        Player[] sortedPlayers = allPlayers.clone();
        Arrays.sort(sortedPlayers, Collections.reverseOrder());
        
        // Check the top players for tie and frozen status
        Player mostSuspicious = null;
        for (Player player : sortedPlayers) {
            if (!player.isFrozen()) {
                if (mostSuspicious == null) {
                    mostSuspicious = player;
                } else if (player.getSusLevel() == mostSuspicious.getSusLevel()) {
                    // Tie found - don't vote anyone off
                    mostSuspicious = null;
                    break;
                } else {
                    // We've found our most suspicious unfrozen player
                    break;
                }
            }
        }
        
        // Freeze the most suspicious player if found
        if (mostSuspicious != null) {
            mostSuspicious.setFrozen(true);
        }
        
        // Check if the game is over
        this.gameOver();
    }

    public void completeTask() {
        // Frozen BlueAstronaut cannot complete tasks
        if (this.isFrozen()) {
            return;
        }
    
        // Store previous numTasks to check if reaching 0 for the first time
        int previousNumTasks = this.numTasks;
    
        // Reduce numTasks based on taskSpeed
        if (taskSpeed > 20) {
            numTasks = numTasks - 2;
        } else {
            numTasks = numTasks - 1;
        }
        
        // If numTasks falls below 0, set it to 0
        if (numTasks < 0) {
            numTasks = 0;
        }
    
        // If Blue Astronaut finished their tasks for the first time
        if (numTasks == 0 && previousNumTasks > 0) {
            System.out.println("I have completed all my tasks");
            setSusLevel(getSusLevel() / 2); // 50% reduction (integer division rounds down)
        }
    }

    public boolean equals(Object o) {
        // Check if object is a BlueAstronaut then use parent class equals to check name, frozen, and susLevel
        // Then separately check if numTasks and taskSpeed match
        if (o instanceof BlueAstronaut) {
            BlueAstronaut blueAstronaut = (BlueAstronaut) o;
            return super.equals(o) && 
                   this.numTasks == blueAstronaut.numTasks && 
                   this.taskSpeed == blueAstronaut.taskSpeed;
        }
        return false;
    }

    public String toString() {
        // Get the base string from the parent Player class
        String baseString = super.toString();
        
        // Add the BlueAstronaut-specific information with the correct format
        String returnString = baseString + ". I have " + this.numTasks + " leftover.";
        
        // Apply uppercase if susLevel > 15
        if (this.getSusLevel() > 15) {
            return returnString.toUpperCase();
        } else {
            return returnString;   
        }
    }

    /* GETTERS */
    public int getNumTasks() {
        return numTasks;
    }

    public int getTaskSpeed() {
        return taskSpeed;
    }
}