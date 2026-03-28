import java.util.HashMap;
import java.util.Map;
import java.util.Random;
public class Perceptron {
    int dimensions;
    double alpha;
    double beta;
    double threshold;
    double[] weights;
    int maxNumberOfEpochs;
    HashMap<String, Integer> label_map;

    public Perceptron(int dimensions, String[] labels) {
        this.dimensions = dimensions;
        this.label_map = new HashMap<>(Map.of(
                labels[0], 1,
                labels[1], 0
        ));
        this.alpha = 0.1;
        this.beta = 0.7;
        this.threshold = 0.1;
        this.maxNumberOfEpochs = 10;
        this.weights = new double[this.dimensions];
        for(int i = 0; i < dimensions; i++) {
            weights[i] = Math.random() * 2 - 1;
        }
    }

    public int predict(double[] input) {
        double net = 0;
        for (int i = 0; i < dimensions; i++) {
            net += weights[i] * input[i];
        }
        return net - this.threshold >= 0 ? 1 : 0;
    }

    private void adjustWeigths(int desiredResult, int realResult, double[] input) {
        for (int i = 0; i < this.weights.length; i++) {
            this.weights[i] += input[i] * this.alpha * (desiredResult - realResult);
        }
    }

    private void adjustThreshold(int desiredResult, int realResult) {
        this.threshold -= (desiredResult - realResult) * this.alpha;
    }

    public void train(double[][] inputs, String[] labels) {
        if (inputs.length != labels.length) { throw new IllegalArgumentException("Inputs and labels array must have same length"); }
        int correctAnswers = 0;
        int numberOfEpochs = 0;

        while (correctAnswers < inputs.length && numberOfEpochs < this.maxNumberOfEpochs) {
            correctAnswers = 0;
            for (int i = 0; i < inputs.length; i++) {
                int desiredResult = label_map.get(labels[i]);
                int realResult = predict(inputs[i]);
                if(desiredResult == realResult) {
                    correctAnswers++;
                } else {
                    adjustWeigths(desiredResult, realResult, inputs[i]);
                    adjustThreshold(desiredResult, realResult);
                }
            }
            numberOfEpochs++;
        }
    }
}
