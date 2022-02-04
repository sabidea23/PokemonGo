package logger;

import java.io.File;
import java.util.ArrayList;

//observer
public class Logger {
    private ArrayList<AventuraResult> results = new ArrayList<>();
    private File outputFile;

    public Logger(File outputFile) {
        this.outputFile = outputFile;
    }

    //adauga o noua optiune de afisare a rezultatului in lista de optiuni
    public void addResult(AventuraResult result) {
        results.add(result);
    }

    //cand afla ceva noua, se transmite tuturor metodelor de afisa a noutatii din lista
    //iar acestea afiseaza informatia 
    public void publishResult(String news) {
        for (AventuraResult result: results) {
            result.writeResult(news);
        }
    }

    public ArrayList<AventuraResult> getResults() {
        return results;
    }

    public void setResults(ArrayList<AventuraResult> results) {
        this.results = results;
    }

    public File getOutputFile() {
        return outputFile;
    }

    public void setOutputFile(File outputFile) {
        this.outputFile = outputFile;
    }
}
