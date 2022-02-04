package logger;

import java.io.*;

public class WriteFile implements AventuraResult{
    private File outputFile;

    public WriteFile(File outputFile) {
        this.outputFile = outputFile;
    }

    public File getOutputFile() {
        return outputFile;
    }

    public void setOutputFile(File outputFile) {
        this.outputFile = outputFile;
    }

    @Override
    public void writeResult(String result) {
        //scrie sirul primit in fisierul de output
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            writer.write(result + "\n");
        } catch (IOException e) {
            System.out.print(e.getMessage());
        }
    }
}
