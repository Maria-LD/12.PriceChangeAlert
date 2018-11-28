import java.text.DecimalFormat;
import java.util.Scanner;

public class PriceChangeAlert  {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of prices: ");
        int numberOfPrices = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter significance threshold: ");
        double significanceThreshold = Double.parseDouble(scanner.nextLine());
        System.out.print("Enter price #1: ");
        double previousPrice = Double.parseDouble(scanner.nextLine());

        for (int i = 1; i <= numberOfPrices - 1; i++) {

            //input
            System.out.printf("Enter price #%d: ", i + 1);
            double currentPrice = Double.parseDouble(scanner.nextLine());

            double difference = calculateDifference(previousPrice, currentPrice);
            boolean isSignificantDifference = isDifferenceSignificant(difference, significanceThreshold);
            String message = getAlertMessage(currentPrice, previousPrice, difference, isSignificantDifference);
            System.out.println(message);

            previousPrice = currentPrice;
        }
    }

    static String getAlertMessage(double currentPrice, double previousPrice, double difference, boolean isSignificantDifference) {

        DecimalFormat df = new DecimalFormat("#.###################");
        String message = "";

        if (difference == 0) {
            message = String.format("NO CHANGE: %s", df.format(currentPrice));
        } else if (!isSignificantDifference) {
            message = String.format("MINOR CHANGE: %s to %s (%.2f",
                    df.format(previousPrice), df.format(currentPrice), difference * 100);
            message += "%)";
        } else if (isSignificantDifference && (difference > 0)) {
            message = String.format("PRICE UP: %s to %s (%.2f",
                    df.format(previousPrice), df.format(currentPrice), difference * 100);
            message += "%)";
        } else if (isSignificantDifference && (difference < 0)) {
            message = String.format("PRICE DOWN: %s to %s (%.2f",
                    df.format(previousPrice), df.format(currentPrice), difference * 100);
            message += "%)";
        }
        return message;
    }

    static boolean isDifferenceSignificant(double difference, double threshold) {
        if (Math.abs(threshold) <= Math.abs(difference)) {
            return true;
        }
        return false;
    }

    static double calculateDifference(double previousPrice, double currentPrice) {
        return (currentPrice - previousPrice) / previousPrice;
    }
}