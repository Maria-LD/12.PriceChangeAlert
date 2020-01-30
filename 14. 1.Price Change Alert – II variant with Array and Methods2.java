import java.util.Arrays;
import java.util.Scanner;

public class PriceChangeAlertSecondVariantWithArrayAndMethods2 {
    public static void main(String args[]) {

        Scanner scanner = new Scanner(System.in);

        int numberOfPrices = Integer.parseInt(scanner.nextLine());
        double significanceThreshold = Double.parseDouble(scanner.nextLine());

        double[] prices = new double[numberOfPrices];

        for (int i = 0; i < prices.length; i++) {
            prices[i] = Double.parseDouble(scanner.nextLine());
        }

        for (int i = 0; i < prices.length; i++) {

            for (int j = i + 1; j < prices.length; j++) {

                double differenceInPrices = Math.abs(prices[j] - prices[i]);
                double differenceInPercentage = differenceInPrices / prices[i] * 100;
                double thresholdInPercent = significanceThreshold * 100;

                checkPrice(prices, i, j, differenceInPrices, differenceInPercentage, thresholdInPercent);

                break;
            }
        }

    }

    private static void checkPrice(double[] prices, int i, int j,
                                   double differenceInPrices,
                                   double differenceInPercentage,
                                   double thresholdInPercent) {
        if (prices[i] == prices[j]) {
            System.out.printf("NO CHANGE: %.2f\n", prices[i]);

            //UP
        } else if (differenceInPrices > 0) {
            getAlertWhenPriceIsUp(prices, i, j, differenceInPrices, differenceInPercentage, thresholdInPercent);

            //DOWN
        } else if (differenceInPrices < 0) {
            getAlertWhenPriceIsDown(prices, i, j, differenceInPrices, differenceInPercentage, thresholdInPercent);
        }
    }

    private static void getAlertWhenPriceIsDown(double[] prices, int i, int j,
                                                double differenceInPrices,
                                                double differenceInPercentage,
                                                double thresholdInPercent) {
        if (differenceInPercentage < thresholdInPercent) {
            System.out.printf("PRICE DOWN, MINOR CHANGE: %.2f to %.2f (%.2f%c)\n",
                    prices[i], prices[j], Math.abs(differenceInPercentage), '%');

        } else if (differenceInPrices > 0 && differenceInPercentage >= thresholdInPercent) {
            System.out.printf("PRICE DOWN, SIGNIFICANT CHANGE: %.2f to %.2f (%.2f%c)\n",
                    prices[i], prices[j], Math.abs(differenceInPercentage), '%');
        }
    }

    private static void getAlertWhenPriceIsUp(double[] prices, int i, int j,
                                          double differenceInPrices,
                                          double differenceInPercentage,
                                          double thresholdInPercent) {
        if (differenceInPercentage < thresholdInPercent) {
            System.out.printf("PRICE UP, MINOR CHANGE: %.2f to %.2f (%.2f%c)\n",
                    prices[i], prices[j], differenceInPercentage, '%');

        } else if (differenceInPrices > 0 && differenceInPercentage >= thresholdInPercent) {
            System.out.printf("PRICE UP, SIGNIFICANT CHANGE: %.2f to %.2f (%.2f%c)\n",
                    prices[i], prices[j], differenceInPercentage, '%');
        }
    }
}