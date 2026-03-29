import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Perceptron p = new Perceptron(4, new String[]{"setosa", "versicolor"});

        PrepareDataset prepareDataset = new PrepareDataset();
        Map<String, Object> data = prepareDataset.trainTestSplit("C:/REPOS/Practise/perceptron/data/iris.csv");
        p.train((List<double[]>) data.get("trainInputs"), (List<String>) data.get("trainLabels"));

        /*
        System.out.println(p.predictClass(new double[]{5.1,3.5,1.4,0.2}));
        System.out.println(p.predictClass(new double[]{4.8,3.4,1.6,0.2}));
        System.out.println(p.predictClass(new double[]{6.2,2.2,4.5,1.5}));
         */

        new Plotter((List<double[]>) data.get("testInputs"), (List<String>) data.get("testLabels"), p.weights, p.threshold);

        Scanner scanner = new Scanner(System.in);
        System.out.println("\n--- Perceptron Prediction Interface ---");

        while (true) {
            System.out.println("\nEnter " + p.dimensions + " features separated by spaces (or type 'q' to quit):");
            String inputLine = scanner.nextLine();

            if (inputLine.equalsIgnoreCase("q")) {
                break;
            }

            try {
                String[] parts = inputLine.trim().split("\\s+");

                if (parts.length != p.dimensions) {
                    System.out.println("Error: You must enter exactly " + p.dimensions + " numbers.");
                    continue;
                }

                double[] userInput = new double[p.dimensions];
                for (int i = 0; i < p.dimensions; i++) {
                    userInput[i] = Double.parseDouble(parts[i]);
                }

                String result = p.predictClass(userInput);
                System.out.println(">>> Predicted Class: " + result);

            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter valid numbers only.");
            }
        }

        System.out.println("Goodbye!");
        scanner.close();

    }
}