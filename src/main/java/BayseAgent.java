import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import de.daslaboratorium.machinelearning.classifier.bayes.BayesClassifier;
import de.daslaboratorium.machinelearning.classifier.Classifier;

public class BayseAgent {
  final Classifier<String, String> bayes = new BayesClassifier<String, String>();
  static int fileCount = 0;

  // Uses Files.walk method
  public void learnByCategory(String category) {
    String path = "src/main/resources/samples/";
    try(Stream<Path> trainers = Files.walk(Paths.get(path+category))) {
      trainers.forEach
        (filePath -> {
          if (fileCount <= 500){
            if (Files.isRegularFile(filePath)) {
              try {
                bayes.learn(category, Arrays.asList(readContentFromFile(filePath)));
                fileCount++;
                System.out.print('.');
              } catch (Exception e) {
                e.printStackTrace();
              }
            }
          }
        }
      );
      System.out.println("Learned " + fileCount + " files for " + category + ".");
      fileCount = 0;
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public String[] readContentFromFile(Path filePath) throws IOException {
    List<String> fileList = Files.readAllLines(filePath);
    return fileList.get(0).split("\\s");
  }

  public Classifier<String, String> agent()
  {
    return bayes;
  }
}
