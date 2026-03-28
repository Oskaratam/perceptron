import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PrepareDataset {

    public PrepareDataset(String filePath) {

    }

    private List<String[]> loadDataset(String filePath) {
        List<String[]>  dataset = new ArrayList<String[]>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                dataset.add(values);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataset;
    }

    public void trainTestSplit(String filPath){
        double[] inputs;
        String[] labels;
    }
}
