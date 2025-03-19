public class RedAstronaut extends Player implements Impostor{
    
    /* VARIABLES */
    private String skill;

    /* CONSTRUCTOR */

    public RedAstronaut(String name) {
        super(name, 15);
        this.skill = "experienced";
    }

    public RedAstronaut(String name, int susLevel, String skill) {
        super(name, susLevel);
        this.skill = validateSkills(skill);
    }

    /* METHODS */
    @Override
    public void emergencyMeeting() {

        // Only consider Players that are not frozen
        if (this.isFrozen()) {
            return;
        }

        // Get array of all players
        Player[] allPlayers = Player.getPlayers();

        // Variables
        Player mostSuspicious = null;
        boolean isTie = false;

        // Loop thru all players
        for (Player player : allPlayers) {
            // Skip player calling the meeting and already frozen players
            if (player == this || player.isFrozen()) {
                continue;
            }

            // Set mostSuspicious to 1st player so far
            if (mostSuspicious == null) {
                mostSuspicious = player;
                continue;
            }

            // Compare current player to mostSuspicious
            int comparisonResult = player.compareTo(mostSuspicious);

            // If positive int result then player is now mostSuspicious, if equal then tie
            if (comparisonResult > 0) {
                mostSuspicious = player;
                isTie = false;
            } else if (comparisonResult == 0) {
                isTie = true;
            }
        }

        // Freeze player if they are the most suspicious and no tie
        if (mostSuspicious != null && !isTie) {
            mostSuspicious.setFrozen(true);
        }

        // Check if game is over
        gameOver();

    }

    public void freeze(Player p) {
        System.out.println("test");

        // Cannot freeze if you are frozen and cannot freeze another imposter or someone who is already frozen
        if (this.isFrozen() || p.isFrozen() || p instanceof RedAstronaut) {
            return;
        }

        // If RedAstronaut's SusLevel < Player's then it is successful otherwise susLevel x 2
        if (this.getSusLevel() < p.getSusLevel()) {
            p.setFrozen(true);
        } else {
            this.setSusLevel(getSusLevel() * 2);
        }

        // Check if game is over
        gameOver();
    }

    public void sabotage(Player p) {
        // Cannot sabotage another impostor, cannot sabotage if you are frozen, cannot sabotage a frozen player
        if (p.isFrozen() || this.isFrozen() || p instanceof RedAstronaut) {
            return;
        }

        // If Impostor's susLevel < 20 increase Crewmate's susLevel by 50%, otherwise 25% rounding down
        if (this.getSusLevel() < 20) {
            p.setSusLevel((int)(p.getSusLevel() + Math.floor(p.getSusLevel() * 0.5)));
        } else {
            p.setSusLevel((int)(p.getSusLevel() + Math.floor(p.getSusLevel() * 0.25)));
        }
    }

    public boolean equals(Object o) {
        // Check if object is a RedAsatronaut then use parent class equals to check name, frozen, and susLevel
        // Then separately check if skills match
        if (o instanceof RedAstronaut) {
            RedAstronaut redAstronaut = (RedAstronaut) o;
            return super.equals(o) && this.skill.equals(redAstronaut.skill);
        }
        return false;
    }

    public String toString() {

        // Get the base string from the parent Player class
        String baseString = super.toString();
        
        // Add the RedAstronaut-specific information
        String returnString = baseString + ". I am an " + this.skill + " player!";
        
        // Apply uppercase if susLevel > 15
        if (this.getSusLevel() > 15) {
            return returnString.toUpperCase();
        } else {
            return returnString;   
        }
    }

    /* HELPERS */

    private String validateSkills(String skill) {
        String lowerSkill = skill.toLowerCase();

        if (lowerSkill.equals("inexperienced") || lowerSkill.equals("experienced") || lowerSkill.equals("expert")) {
            return lowerSkill;
        } else {
            return "experienced";
        }
    }

    /* GETTERS */

    /* SETTERS */

    /* TESTING */

    public static void main(String[] args) {
        System.out.println("test");
    }
}
