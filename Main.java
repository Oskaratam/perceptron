import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Perceptron perceptron = new Perceptron(4, new String[]{"setosa", "versicolor"});
        System.out.println(perceptron.predict(new double[]{6.9,3.1,4.9,1.5}));

        PrepareDataset pr = new PrepareDataset();

        /*pr.shuffleList(new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9))); */
        pr.trainTestSplit("C:/REPOS/Practise/perceptron/data/iris.csv");
    }
}