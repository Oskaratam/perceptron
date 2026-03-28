import java.lang.reflect.Array;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Perceptron perceptron = new Perceptron(4);
        System.out.println(perceptron.predict(new double[]{6.9,3.1,4.9,1.5}));
    }
}