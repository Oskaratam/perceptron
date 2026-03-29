import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    private int predict(double[] input) {
        double net = 0;
        for (int i = 0; i < dimensions; i++) {
            net += weights[i] * input[i];
        }
        return net - this.threshold >= 0 ? 1 : 0;
    }

    public String predictClass(double[] input) {
        double net = 0;
        for (int i = 0; i < dimensions; i++) {
            net += weights[i] * input[i];
        }
        int numericPrediction = net - this.threshold >= 0 ? 1 : 0;
        for (Map.Entry<String, Integer> entry : label_map.entrySet()) {
            if (entry.getValue().equals(numericPrediction)) {
                return entry.getKey();
            }
        }
        return "Unknown";
    }

    private void adjustWeigths(int desiredResult, int realResult, double[] input) {
        for (int i = 0; i < this.weights.length; i++) {
            this.weights[i] += input[i] * this.alpha * (desiredResult - realResult);
        }
    }

    private void adjustThreshold(int desiredResult, int realResult) {
        this.threshold -= (desiredResult - realResult) * this.alpha;
    }

    public void train(List<double[]> inputs, List<String> labels) {
        if (inputs.size() != labels.size()) { throw new IllegalArgumentException("Inputs and labels array must have same length"); }
        int correctAnswers = 0;
        int numberOfEpochs = 1;
        EvaluationMetrics evaluationMetrics = new EvaluationMetrics();

        while (correctAnswers < inputs.size() && numberOfEpochs < this.maxNumberOfEpochs) {
            List<Integer> predictedClasses = new ArrayList<Integer>();
            List<Integer> realClasses = new ArrayList<Integer>();
            correctAnswers = 0;
            double accuracy = 0.0;
            for (int i = 0; i < inputs.size(); i++) {
                int desiredResult = label_map.get(labels.get(i));
                realClasses.add(desiredResult);
                int realResult = predict(inputs.get(i));
                predictedClasses.add(realResult);
                if(desiredResult == realResult) {
                    correctAnswers++;
                } else {
                    adjustWeigths(desiredResult, realResult, inputs.get(i));
                    adjustThreshold(desiredResult, realResult);
                }
                accuracy = evaluationMetrics.measureAccuracy(realClasses, predictedClasses);
            }
            System.out.println("Epoch number: " + numberOfEpochs);
            System.out.println("Correct answers: " + correctAnswers + " / " + inputs.size());
            System.out.println("Accuracy is: " + accuracy + "%");
            System.out.println("--------------------------------");
            numberOfEpochs++;
        }
    }
}
