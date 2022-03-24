package controllers;

import entities.base.BaseEntity;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Utilities {
    private final Scanner scanner = new Scanner(System.in);

    public String passwordReceiver() {
        return regexAdder(".{4,}", "Password", "At Least 4 Characters.");
    }

    public String regexAdder(String regex, String tag, String additionalInfo) {
        while (true) {
            System.out.print(tag + "(" + additionalInfo + "): ");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if (checkRegex(input, regex)) {
                return input;
            } else {
                System.out.println("Wrong " + tag + " Format. Enter a Correct " + tag + " Format:");
            }
        }
    }

    private boolean checkRegex(String input, String regexPattern) {
        return Pattern.compile(regexPattern).matcher(input).matches();
    }

    public int intReceiver() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("You should enter a number here: ");
            }
        }
    }

    public double doubleReceiver() {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("You should enter a number here: ");
            }
        }
    }

    public String randomFiller(Integer size, Character lowerBound, Character upperBound){
        StringBuilder output = new StringBuilder();
        if(((int) upperBound - lowerBound) >= size) { //characters range should not be smaller than the input size
            while (output.length() < size) {
                output.append(Utilities.rangedBaseRandomizer(lowerBound, upperBound));
            }
            return output.toString();
        } else return null;
    }

    public static Character rangedBaseRandomizer(Character lowerBound,Character upperBound){
        return (char) ((int) (Math.random() * (upperBound - lowerBound) + lowerBound));
    }

    public <T> void iterateThrough(List<T> lists) {
        if (lists != null && lists.size() > 0) {
            for (T object : lists) {
                if (object != null)
                    System.out.println("\u001B[32m" + object + "\u001B[0m");
            }
        } else {
            printYellow("This list is empty.");
        }
    }

    public <T extends BaseEntity> T findIdInCollection(Collection<T> toSearch, Integer id) {
        if (toSearch != null && toSearch.size() > 0)
            return toSearch
                    .stream()
                    .filter(t -> Objects.equals(t.getId(), id))
                    .findAny()
                    .orElse(null);
        else return null;
    }

    public void printRed(String input) {
        try {
            String ANSI_RED = "\u001B[31m";
            System.out.print("------------------------------\n" + ANSI_RED + input + ANSI_RESET + "\n------------------------------\n");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void printGreen(String input) {
        try {
            String ANSI_GREEN = "\u001B[32m";
            System.out.print("------------------------------\n" + ANSI_GREEN + input + ANSI_RESET + "\n------------------------------\n");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void printGreen(String input, Integer waitTime) {
        try {
            String ANSI_GREEN = "\u001B[32m";
            System.out.print("------------------------------\n" + ANSI_GREEN + input + ANSI_RESET + "\n------------------------------\n");
            Thread.sleep(waitTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void printYellow(String input) {
        try {
            String ANSI_YELLOW = "\u001B[33m";
            System.out.print("------------------------------\n" + ANSI_YELLOW + input + ANSI_RESET + "\n------------------------------\n");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private final String ANSI_RESET = "\u001B[0m";
}
