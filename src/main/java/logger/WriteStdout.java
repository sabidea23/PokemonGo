package logger;

public class WriteStdout implements AventuraResult {

    @Override
    public void writeResult(String result) {
        System.out.println(result);
    }
}
