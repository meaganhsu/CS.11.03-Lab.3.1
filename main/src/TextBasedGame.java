import java.util.Scanner;

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
            gameWon = true;
            System.out.println("you've escaped. good job!");
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
            System.out.println("you jump and grab onto the tree branch. as you try to adjust, the branch snaps and you fall.");
            System.out.println("you lost. good effort.");
            gameLost = true;
        } else if (input.equals("pipe")) {     // winning scenario 1
            System.out.println("you carefully hold onto the pipe and slowly slide down to the ground.");
            gameWon = true;
            System.out.println("you've escaped. good job!");
        }
        else {
            System.out.println("huh?");
        }
    }

    public static void cabinet() {
        System.out.println("there are three drawers.");
        input = scanner.nextLine();
        if (input.equals("drawer 1") || input.equals("1")) {
            System.out.println("only dust.");
        }
        else if (input.equals("drawer 2") || input.equals("2")) {     // answers combined will be the code for the blue lock
            System.out.println("inside is a piece of paper written a math problem:");
            System.out.println("1. 6 ÷ 2(1 + 2) = ?\n2. (25 - 4 × 6) + 2 × 3 = ?\n3. 10 + 1(8 - 5) * 2 = ?");
        }
        else if (input.equals("drawer 3") || input.equals("3")) {     // hint for clock code
            System.out.println("the drawer is empty. but carved into the wood is: '12 → 24'");
        }
    }

    public static void safe() {
        if (lockSafe) {
            System.out.println("the safe is locked. it requires a four-digit code. code: ");
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
                System.out.println("the red compartment opens. inside is a silver key'.");
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
        System.out.println("this compartment requires a four-digit code. enter code: ");
        input = scanner.nextLine();
        if (!blueLock) {
            if (input.equals("9716")) {     // math problem solution
                System.out.println("the blue lock clicks open.");
                blueLock = true;
                System.out.println("the blue compartment opens. inside is a note written 'caesar cipher a → j (9)'.");     // hint for caesar cipher
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
}