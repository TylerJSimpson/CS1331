public class Gameplay {
    
    public static void main(String args[]) {

        BlueAstronaut blueAstronautBob = new BlueAstronaut("Bob",20,6, 30);
        BlueAstronaut blueAstronautHeath = new BlueAstronaut("Heath",30,3,21);
        BlueAstronaut blueAstronautAlbert = new BlueAstronaut("Albert",44,2,0);
        BlueAstronaut blueAstronautAngel = new BlueAstronaut("Angel",0,1,0);
        RedAstronaut redAstronautLiam = new RedAstronaut("Liam",19,"experienced");
        RedAstronaut redAstronautSuspiciousPerson = new RedAstronaut("Suspicious Person",100,"expert");

        System.out.println("TESTING:");
        System.out.println();

        // 1
        System.out.println("1. RedAstronaut Liam Sabotages BlueAstronaut Bob leading to susLevel = 30, frozen = false.");
        System.out.println(blueAstronautBob);
        redAstronautLiam.sabotage(blueAstronautBob);
        System.out.println(blueAstronautBob);
        System.out.println();

        // 2
        System.out.println("2. RedAstronaut Liam freezes RedAstronaut Suspicious Person. Nothing should happen.");
        System.out.println(redAstronautSuspiciousPerson);
        redAstronautLiam.freeze(redAstronautSuspiciousPerson);
        System.out.println(redAstronautSuspiciousPerson);
        System.out.println();

        // 3
        System.out.println("3. RedAstronaut Liam freezes BlueAstronaut Albert leading to Liam susLevel 19 and Albert frozen.");
        System.out.println(redAstronautLiam);
        System.out.println(blueAstronautAlbert);
        redAstronautLiam.freeze(blueAstronautAlbert);
        System.out.println(redAstronautLiam);
        System.out.println(blueAstronautAlbert);
        System.out.println();

        // 4
        System.out.println("4. BlueAstronaut Albert calls an emergency meeting, he is frozen so nothing should happen.");
        System.out.println(blueAstronautAlbert);
        blueAstronautAlbert.emergencyMeeting();
        System.out.println();

        // 5
        System.out.println("5. RedAstronaut Suspicious Person calls an emergency meeting. Should be a tie with Bob and Heath so nothing should happen.");
        System.out.println(blueAstronautBob);
        System.out.println(blueAstronautHeath);
        redAstronautSuspiciousPerson.emergencyMeeting();
        System.out.println();

        // 6
        System.out.println("6. BlueAstronaut Bob calls an emergency meeting which should freeze Suspicious Person.");
        System.out.println(redAstronautSuspiciousPerson);
        blueAstronautBob.emergencyMeeting();
        System.out.println(redAstronautSuspiciousPerson);
        System.out.println();

        // 7
        System.out.println("7. BlueAstronaut Heath completes a task resulting in numTasks = 1.");
        System.out.println(blueAstronautHeath);
        blueAstronautHeath.completeTask();
        System.out.println(blueAstronautHeath);
        System.out.println();

        // 8
        System.out.println("8. BlueAstronaut Heath completes a task resulting in completing all tasks and susLevel = 15.");
        System.out.println(blueAstronautHeath);
        blueAstronautHeath.completeTask();
        System.out.println(blueAstronautHeath);
        System.out.println();    

        // 9
        System.out.println("9. BlueAstronaut Heath completes a task, nothing should happen.");
        System.out.println(blueAstronautHeath);
        blueAstronautHeath.completeTask();
        System.out.println(blueAstronautHeath);
        System.out.println();

        // 10
        System.out.println("10. RedAstronaut Liam freezes Angel resulting in frozen = false for Angel and susLevel = 38 for Liam.");
        System.out.println(redAstronautLiam);
        System.out.println(blueAstronautAngel);        
        redAstronautLiam.freeze(blueAstronautAngel);
        System.out.println(redAstronautLiam);
        System.out.println(blueAstronautAngel);
        System.out.println();

        // 11
        System.out.println("11. Red Astronaut Liam sobatges Bob twice susLevel should go from 30 to 37 to 46.");
        System.out.println(blueAstronautBob);
        redAstronautLiam.sabotage(blueAstronautBob);
        System.out.println(blueAstronautBob);
        redAstronautLiam.sabotage(blueAstronautBob);
        System.out.println(blueAstronautBob);
        System.out.println();

        // 12
        System.out.println("12. RedAstronaut Liam freezes Bob resulting in Bob being frozen.");
        System.out.println(blueAstronautBob);
        redAstronautLiam.freeze(blueAstronautBob);
        System.out.println(blueAstronautBob);
        System.out.println();

        // 13
        System.out.println("13. BlueAstronaut Angel calls an emergency meeting resulting in Liam being frozen and Crewmates Win!.");
        System.out.println(redAstronautLiam);
        blueAstronautAngel.emergencyMeeting();
        System.out.println();        

    }
}
