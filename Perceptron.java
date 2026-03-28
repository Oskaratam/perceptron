import java.util.Random;
public class Perceptron {
    int dimensions;
    double alpha;
    double beta;
    double threshold;
    double[] weights;

    public Perceptron(int dimensions) {
        this.dimensions = dimensions;
        this.alpha = 0.1;
        this.beta = 0.7;
        this.threshold = 0.1;

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
    public void train(){

    }
}
