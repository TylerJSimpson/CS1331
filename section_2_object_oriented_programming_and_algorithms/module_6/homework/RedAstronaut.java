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

    public void freeze() {
        System.out.println("test");
    }

    public void sabotage() {
        System.out.println("test");
    }

    public void equals() {
        System.out.println("test");
    }

    public String toString() {
        return ("Test");
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
