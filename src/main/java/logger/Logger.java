package logger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Logger {
    private ArrayList<AventuraResult> results = new ArrayList<>();
    private File outputFile;

    private static Logger single_instance = null;

    private Logger() {
    }

    public static Logger getInstance() {
        if (single_instance == null)
            single_instance = new Logger();

        return single_instance;
    }

    /**
     * Adds a new way to display battle information in the list
     */
    public void addResult(AventuraResult result) {
        results.add(result);
    }

    /**
     * When he finds out new information from the battle, sends it to everyone
     */
    public synchronized void publishResult(String news) {
        for (AventuraResult result: results) {
            try {
                result.writeResult(news);
            } catch (IOException e) {
                e.printStackTrace();
            }
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
