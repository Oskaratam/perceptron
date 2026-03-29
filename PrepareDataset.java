import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class PrepareDataset {

    public List<String[]> loadDataset(String filePath) {
        List<String[]>  dataset = new ArrayList<String[]>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            String header = br.readLine();
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] values = line.split(",");
                dataset.add(values);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataset;
    }

    public void shuffleList(ArrayList list) {
        int lastIndex = list.size() - 1;
        while(lastIndex > 0){
           int randomIndex = (int) Math.floor(Math.random() * list.size());
           Object temp = list.get(lastIndex);
           list.set(lastIndex, list.get(randomIndex));
           list.set(randomIndex, temp);
           lastIndex--;
        }
    }

    public Map trainTestSplit(String filePath){
        List<String[]> dataset = this.loadDataset(filePath);
        List<double[]> trainInputs = new ArrayList<>();
        List<double[]> testInputs = new ArrayList<>();
        List<String> trainLabels = new ArrayList<>();
        List<String> testLabels = new ArrayList<>();

        Map<String, ArrayList<String[]>> classMap = new HashMap<>();

        for(String[] row : dataset) {
            String label = row[row.length - 1];
            if(!classMap.containsKey(label)) {
                classMap.put(label, new ArrayList());
            }
            classMap.get(label).add(row);

        }

        for(String label : classMap.keySet()) {
            ArrayList<String[]> rowsList = classMap.get(label);
            this.shuffleList(rowsList);

            for (int i = 0; i < rowsList.size(); i++) {
                String[] row = rowsList.get(i);

                double[] input = new double[row.length - 1];
                Arrays.setAll(input, j -> Double.parseDouble(row[j]));

                if(i < rowsList.size() * 0.7) {
                    trainInputs.add(input);
                    trainLabels.add(label);
                } else {
                    testInputs.add(input);
                    testLabels.add(label);
                }
            }
        }
       return Map.of("trainLabels", trainLabels, "trainInputs", trainInputs,
               "testLabels", testLabels, "testInputs", testInputs);
    }
}
