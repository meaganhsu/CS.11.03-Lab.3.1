import java.util.Scanner;
import java.util.Random;

public class TextBasedGame {
    private static boolean lockSafe = true;
    private static boolean hasKey = false;
    private static boolean gameWon = false;
    private static boolean gameLost = false;
    private static boolean redLock = false;
    private static boolean blueLock = false;
    private static boolean knife = false;
    private static Scanner scanner = new Scanner(System.in);
    private static String input;

    public static void main(String[] args) {
        System.out.println("you find yourself in a locked room. find your way out.");
        while (!gameWon && !gameLost) {     // while user has not yet won or lost
            input = scanner.nextLine();     // calling methods depending on what user inputs
            if (input.equals("look")) {
                look();
            } else if (input.equals("door")) {
                door();
            } else if (input.equals("window")) {
                window();
            } else if (input.equals("safe")) {
                safe();
            } else if (input.equals("box")) {
                box();
            } else if (input.equals("clock")) {
                clock();
            } else if (input.equals("cabinet")) {
                cabinet();
            } else {
                System.out.println("huh?");
            }
        }
    }

    public static void look() {
        System.out.println("you look around the room and see a closed door, a closed window, a clock, and a safe.");
        System.out.println("there is a table in the centre of the room with a box and a drawer cabinet in the corner.");
    }

    public static void clock() {
        System.out.println("the time is 7:43. the hands seem to be inoperative.");
    }

    public static void door() {
        if (hasKey) {     // if the user got the key from the box, then they can open the door
            System.out.println("you use the key to unlock the door.");
            System.out.println("however, there is a number pad lock preventing your escape. enter a number: ");
            int doorNum = scanner.nextInt();
            if (doorNum % 2 == 0 ) {
                System.out.println("the door opens with a click.");
                gameWon = true;
                System.out.println("you've escaped. good job!");
            }
            else {
                System.out.println("oh no! you've pressed the wrong number. the alarm sounds, locking all possible exits from the room. ");
                gameLost = true;
                System.out.println("you lost. good effort.");
            }
        } else {     // if not, they cannot open the door
            System.out.println("the door is locked. you need to find a key.");
        }
    }

    public static void window() {
        System.out.println("the window is closed; it seems to be stuck.");
        System.out.println("written on the window is: 'cqn cAxdkun rb, hxD cqrWt hxd qjen crvn.'");     // caesar cipher, hint given in drawer 2
        input = scanner.nextLine();
        if ((input.equals("open") || input.equals("use knife")) && knife) {     // only if user collected the knife
            System.out.println("after a bit of force, the window opens. you can climb out.");
            windowOutside();
        } else {
            System.out.println("huh?");
        }
    }

    public static void windowOutside() {
        System.out.println("there is a ledge a few floors lower that you can jump to. there is a tree branching out towards you that you can jump and grab onto.");
        System.out.println("there is also a pipe that you can scale down to the ground.");
        input = scanner.nextLine();
        if (input.equals("ledge")) {     // losing scenario 1
            System.out.println("you jump to the ledge. you land badly and break your foot, losing your balance.");
            System.out.println("you lost. good effort.");
            gameLost = true;
        } else if (input.equals("tree")) {     // losing scenario 2
            double weightTree;
            System.out.println("you jump and grab onto the tree branch.");
            System.out.println("please enter your weight in kg: ");
            weightTree = scanner.nextDouble();
            if (weightTree >= 80) {
                System.out.println("as you adjust your grip, you slip and fall. \nyou lost. good effort.");
                gameLost = true;
            }
            else if (weightTree < 80) {
                System.out.println("you maneuver your way to the tree trunk and slide down. ");
                gameWon = true;
                System.out.println("you've escaped. good job!");
            }
            else {
                System.out.println("invalid input.");
                gameLost = true;
                System.out.println("you lost. good effort.");
            }
        } else if (input.equals("pipe")) {     // winning scenario 1
            double weightPipe;
            System.out.println("you carefully hold onto the pipe and begin sliding down.");
            System.out.println("please enter your weight in kg: ");
            weightPipe = scanner.nextDouble();
            if (weightPipe >= 80) {
                System.out.println("you realise the pipe is wobbly and you fall. \nyou lost. good effort.");
                gameLost = true;
            }
            else if (weightPipe < 80) {
                System.out.println("you slowly slide down the pipe and land safely on the ground. ");
                gameWon = true;
                System.out.println("you've escaped. good job!");
            }
            else {
                System.out.println("invalid input.");
                gameLost = true;
                System.out.println("you lost. good effort.");
            }
        }
        else {
            System.out.println("huh?");
        }
    }

    public static void cabinet() {
        System.out.println("there are two drawers.");
        input = scanner.nextLine();
        if (input.equals("drawer 1") || input.equals("1")) {
            System.out.println("only dust.");
        }
        else if (input.equals("drawer 2") || input.equals("2")) {      // hint for clock code
            System.out.println("the drawer is empty. but carved into the wood is: '12 → 24'");
        }
        else {
            System.out.println("huh?");
        }
    }

    public static void safe() {
        if (lockSafe) {
            System.out.println("the safe is locked. it requires a four-digit code.");
            input = scanner.nextLine();
            if (input.equals("1943")) {     // time of clock 7:43 —> 24hr time 19:43
                lockSafe = false;
                System.out.println("the lock opens. inside is a knife.");
                System.out.println("you take the knife.");
                knife = true;
            }
            else {
                System.out.println("incorrect code.");
            }
        } else {
            System.out.println("you already unlocked the safe. it's empty.");
        }
    }

    public static void box() {
        System.out.println("on the table, there is a box with two colored compartments: red and blue.");
        input = scanner.nextLine();
        if (input.equals("red")) {
            redCompartment();
        }
        else if (input.equals("blue")) {
            blueCompartment();
        }
        else {
            System.out.println("huh?");
        }
    }

    public static void redCompartment() {
        if (!redLock) {
            System.out.println("this compartment requires a three-letter alphabetical code. enter code:");
            input = scanner.nextLine();
            if (input.equals("run")) {     // caesar cipher solution
                System.out.println("the red lock clicks open.");
                redLock = true;
                System.out.println("the red compartment opens. inside is a silver key.");
                System.out.println("you take the key.");
                hasKey = true;
            }
            else {
                System.out.println("the code is incorrect.");
            }
        }
        else {
            System.out.println("the compartment is empty. you've already unlocked it.");
        }
    }

    public static void blueCompartment() {
        if (!blueLock) {
            Random rand = new Random();
            int score = 0;
            int a = rand.nextInt(10);
            int b = rand.nextInt(50);
            int c = rand.nextInt(25);
            int d = rand.nextInt(15);
            int e = rand.nextInt(50);
            int f = rand.nextInt(50);
            System.out.println("this compartment requires answers to three math problems.");
            System.out.println("1. what is the remainder of " + b + " / " + a + "?");
            int blueAns = scanner.nextInt();
            if (blueAns == b % a) {
                System.out.println("correct!");
                score += 1;
            } else {
                System.out.println("incorrect. the answer was " + b % a + ".");
            }

            System.out.println("2. what is " + f + " - " + e + "?");
            blueAns = scanner.nextInt();
            if (blueAns == f - e) {
                System.out.println("correct!");
                score += 1;
            } else {
                System.out.println("incorrect. the answer was " + (f - e) + ".");
            }

            System.out.println("3. what is " + c + " * " + d + "?");
            blueAns = scanner.nextInt();
            if (blueAns == c * d) {
                System.out.println("correct!");
                score += 1;
            } else {
                System.out.println("incorrect. the answer was " + c * d + ".");
            }
            if (score == 3) {
                System.out.println("the blue lock clicks open.");
                blueLock = true;
                System.out.println("the blue compartment opens. inside is a note written 'caesar cipher a → j (9)'.");     // hint for caesar cipher
            }
            else {
                System.out.println("you didn't get all 3 questions right. try again.");
            }
        }
        else {
            System.out.println("the compartment is empty. you've already unlocked it.");
        }
    }
}