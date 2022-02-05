package logger;

public class WriteStdout implements AventuraResult {

    @Override
    public void writeResult(String result) {
        //scrie sirul primit la stdout
        System.out.println(result);
    }
}
