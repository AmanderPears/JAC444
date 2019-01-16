import java.util.Scanner;
import java.util.Vector;
import java.text.DecimalFormat;

class lab1 {

    public static void program1() {
        System.out.println("Program: 1");
        System.out.println("==========");
        double currentPop = 312032486;
        int secondsInAYear = 365 * 24 * 60 * 60;
        System.out.format("Current population: %.0f\n", currentPop);
        for (int year = 1; year <= 5; year++) {
            currentPop += (secondsInAYear / 7) + (secondsInAYear / 45) - (secondsInAYear / 13);
            System.out.format("Population after %d years: %.0f\n", year, currentPop);
        }
        System.out.println();
    }

    public static void program2() {
        System.out.println("Program: 2");
        System.out.println("==========");
        Scanner get_input = new Scanner(System.in);
        int input;
        do {
            System.out.print("Enter a number between 0 and 1000: ");
            input = get_input.nextInt();
        } while (input <= 0 && input >= 1000);
        get_input.close();

        int sum = 0;
        while (input != 0) {
            sum += input % 10;
            input = input / 10;
        }

        System.out.println("The sum of the digits is " + sum + "\n");
    }

    public static void program3() {
        System.out.println("Program: 3");
        System.out.println("==========");
        System.out.print("Enter the monthly saving amount: ");
        Scanner get_input = new Scanner(System.in);
        double amt = get_input.nextDouble();
        get_input.close();
        System.out.format("Monthly interest rate: %.5f\n", (0.05 / 12));

        double total = 0;
        for (int i = 0; i < 6; i++) {
            total = (total + amt) * (1 + (0.05 / 12));
        }
        System.out.format("After the sixth month, the account value is $%.2f.\n\n", total);
    }

    public static void program4() {
        System.out.println("Program: 4");
        System.out.println("==========");
        /*
         * 4. Write a program that randomly generates an integer between 1 and 12 and
         * displays the English month name January, February, …, December for the number
         * 1, 2, …, 12, accordingly. Use Math.Random to generate the random number for
         * the month.
         */

        String months[] = { "months", "January", "Feburary", "March", "April", "May", "June", "July", "August",
                "September", "October", "November", "December" };
        int num = (int) (Math.random() * 12 + 1);
        System.out.println("Random number between 1 and 12 inclusive: " + num);
        System.out.println("Corresponding month: " + months[num] + "\n");
    }

    public static void program5() {
        /*
         * Write a program that simulates picking a card from a deck of 52 cards. Your
         * program should display the rank (Ace, 2, 3, 4, 5, 6, 7, 8, 9, 10, Jack,
         * Queen, King) and suit (Clubs, Diamonds, Hearts, Spades) of the card.
         */
        System.out.println("Program: 5");
        System.out.println("==========");
        System.out.print("Generating a standard deck of 52 cards...");
        class Card {
            public String face, suit;

            public Card(String f, String s) {
                face = f;
                suit = s;
            }

            @Override
            public String toString() {
                return "(" + face + "," + suit + ")";
            }
        }

        String suit[] = { "Clubs", "Diamonds", "Hearts", "Spades" };
        Vector<Card> deck = new Vector<Card>();

        for (String s : suit) {
            deck.addElement(new Card("Ace", s));
            for (Integer i = 2; i <= 10; i++) {
                deck.addElement(new Card(i.toString(), s));
            }
            deck.addElement(new Card("Jack", s));
            deck.addElement(new Card("Queen", s));
            deck.addElement(new Card("King", s));
        }
        System.out.println("    [DONE]");

        // stupid
        char yn = 'N';
        Scanner get_input = new Scanner(System.in);

        while (yn != 'Y') {
            System.out.print("Pick a card? (Y/n): ");
            yn = get_input.next().toUpperCase().charAt(0);
        }

        get_input.close();

        int rn = (int) (Math.random() * 52);
        String aAn = "a";
        if ((deck.get(rn).face.compareTo("Ace") == 0) || (deck.get(rn).face.compareTo("8") == 0)) {
            aAn = "an";
        }
        System.out.format("The card you picked is %s %s of %s.\n\n", aAn, deck.get(rn).face, deck.get(rn).suit);

    }

    public static void program6() {
        /*
         * Suppose that the tuition for a university is $10,000 this year and increases
         * 5% every year. In one year, the tuition will be $10,500. Write a program that
         * computes the tuition in ten years and the total cost of four years’ worth of
         * tuition after the tenth year.
         */
        System.out.println("Program: 6");
        System.out.println("==========");
        System.out.println("Tuition for a univerity this year: $10,000");
        System.out.println("Tuition increase every year: 5%");
        System.out.println("Tuition next year: $10,500");
        double tuition = 10000;
        for (int i = 0; i < 10; i++) {
            tuition = tuition + tuition * 0.05;
        }
        DecimalFormat numFormat = new DecimalFormat("$###,###,###.##");
        System.out.format("Tuition increase after 10 years: %s\n", numFormat.format(tuition));
        double sum = 0;
        for (int i = 0; i < 4; i++) {
            tuition = tuition + tuition * 0.05;
            sum += tuition;
        }
        System.out.format("4-year tuition cost after 10th year: %s\n\n", numFormat.format(sum));

    }

    public static void program7() {
        System.out.println("Program: 7");
        System.out.println("==========");
        int rows = 8;
        for (int i = 0; i < rows; i++) {
            for (int s = i; s < rows; s++) {
                System.out.print("    ");
            }

            int j;
            for (j = 0; j < i; j++) {
                System.out.print(String.format("%1$4d", (int) Math.pow(2, j)));
            }

            for (int r = j; r >= 0; r--) {
                System.out.print(String.format("%1$4d", (int) Math.pow(2, r)));
            }

            System.out.println();
        }
    }

    public static void main(String[] args) {
        program1();
        // program2();
        // program3();
        // program4();
        // program5();
        // program6();
        // program7();
    }
}