import java.util.Arrays;
import java.util.Scanner;

public class PriceChangeAlertSecondVariantWithArrayAndMethod {
    public static void main(String args[]) {

        Scanner scanner = new Scanner(System.in);

        int numberOfPrices = Integer.parseInt(scanner.nextLine());
        double significanceThreshold = Double.parseDouble(scanner.nextLine());

        double[] prices = new double[numberOfPrices];

        for (int i = 0; i < prices.length; i++) {
            prices[i] = Double.parseDouble(scanner.nextLine());
        }

        for (int i = 0; i < prices.length; i++) {

            for (int j = i+1; j < prices.length; j++) {

                getAlert(significanceThreshold, prices[i], prices[j]);

                break;
            }

        }
    }

    private static void getAlert(double significanceThreshold, double price, double price1) {
        double differenceInPrices = Math.abs(price1 - price);
        double differenceInPercentage = differenceInPrices/ price * 100;
        double thresholdInPercent = significanceThreshold * 100;

        if (price == price1) {
            System.out.printf("NO CHANGE: %.2f\n", price);
            //UP
        } else if (differenceInPrices > 0 && differenceInPercentage < thresholdInPercent) {
            System.out.printf("PRICE UP, MINOR CHANGE: %.2f to %.2f (%.2f%c)\n",
                    price, price1, differenceInPercentage, '%');

        } else if (differenceInPrices > 0 && differenceInPercentage >= thresholdInPercent) {
            System.out.printf("PRICE UP, SIGNIFICANT CHANGE: %.2f to %.2f (%.2f%c)\n",
                    price, price1, differenceInPercentage, '%');
            //DOWN
        } else if (differenceInPrices < 0 && differenceInPercentage < thresholdInPercent) {
            System.out.printf("PRICE DOWN, MINOR CHANGE: %.2f to %.2f (%.2f%c)\n",
                    price, price1, Math.abs(differenceInPercentage), '%');

        } else if (differenceInPrices > 0 && differenceInPercentage >= thresholdInPercent) {
            System.out.printf("PRICE DOWN, SIGNIFICANT CHANGE: %.2f to %.2f (%.2f%c)\n",
                    price, price1, Math.abs(differenceInPercentage), '%');
        }
    }
}