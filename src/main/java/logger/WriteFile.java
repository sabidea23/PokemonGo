package logger;

import java.io.*;

public class WriteFile implements AventuraResult{
    private BufferedWriter bufferedWriter;

    public WriteFile(File outputFile) throws IOException {
        this.bufferedWriter = new BufferedWriter(new FileWriter(outputFile));
    }

    public BufferedWriter getBufferedWriter() {
        return bufferedWriter;
    }

    public void setBufferedWriter(BufferedWriter bufferedWriter) {
        this.bufferedWriter = bufferedWriter;
    }

    @Override
    public void writeResult(String result) throws IOException {
        bufferedWriter.write(result + "\n");
    }
}
