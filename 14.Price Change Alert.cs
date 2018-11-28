using System;

class PriceChangeAlert
{
    static void Main()
    {
        Console.Write("Enter number of prices: ");
        int numberOfPrices = int.Parse(Console.ReadLine());
        Console.Write("Enter significance threshold: ");
        double significanceThreshold = double.Parse(Console.ReadLine());
        Console.Write("Enter price #1: ");
        double previousPrice = double.Parse(Console.ReadLine());

        for (int i = 1; i <= numberOfPrices - 1; i++)
        {
            //input
            Console.Write($"Enter price #{i+1}: ");
            double currentPrice = double.Parse(Console.ReadLine());

            double difference = CalculateDifference(previousPrice, currentPrice);
            bool isSignificantDifference = IsDifferenceSignificant(difference, significanceThreshold);
            string message = GetAlertMessage(currentPrice, previousPrice, difference, isSignificantDifference);
            Console.WriteLine(message);

            previousPrice = currentPrice;
        }
    }

    static string GetAlertMessage(double currentPrice, double previousPrice, double difference, bool isSignificantDifference)
    {
        string message = "";
        if (difference == 0)
        {
            message = string.Format("NO CHANGE: {0}", currentPrice);
        }
        else if (!isSignificantDifference)
        {
            message = string.Format("MINOR CHANGE: {0} to {1} ({2:F2}%)", previousPrice, currentPrice, difference * 100);
        }
        else if (isSignificantDifference && (difference > 0))
        {
            message = string.Format("PRICE UP: {0} to {1} ({2:F2}%)", previousPrice, currentPrice, difference * 100);
        }
        else if (isSignificantDifference && (difference < 0))
            message = string.Format("PRICE DOWN: {0} to {1} ({2:F2}%)", previousPrice, currentPrice, difference * 100);
        return message;
    }

    static bool IsDifferenceSignificant(double difference, double threshold)
    {
        if (Math.Abs(threshold) <= Math.Abs(difference))
        {
            return true;
        }
        return false;
    }

    private static double CalculateDifference(double previousPrice, double currentPrice)
    {
        return (currentPrice - previousPrice) / previousPrice;
    }

}