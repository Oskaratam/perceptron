import java.util.List;

public class EvaluationMetrics {

    public double measureAccuracy(List<Integer> realClasses, List<Integer> predictedClasses){
        double correctAnswers = 0;
        if (realClasses.size() != predictedClasses.size()){
            throw new IllegalArgumentException("Real classes and predicted classes should have the same length");
        }
        for (int i = 0; i < realClasses.size(); i++){
            if (realClasses.get(i).equals(predictedClasses.get(i))){
                correctAnswers++;
            }
        }

        return correctAnswers / realClasses.size() * 100;
    }
}
