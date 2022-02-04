import arena.Arena;

import java.io.File;

public class Test {
    File inputTrainer1;
    File inputTrainer2;
    File items1;
    File items2;
    File outputFile;

    public  void initFiles(String fileTrainer1, String fileTrainer2, String fileItems1,
                          String fileItems2, String fileOutput) {
        try {
            inputTrainer1 = new File(fileTrainer1);
            inputTrainer2 = new File(fileTrainer2);
            items1 = new File(fileItems1);
            items2 = new File(fileItems2);
            outputFile = new File(fileOutput);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @org.junit.jupiter.api.Test
    public void Test1() {
        String fileTrainer1 = "tests\\\\test1\\\\test1_antrenor1.in";
        String fileTrainer2 = "tests\\test1\\test1_antrenor2.in";
        String fileItems1 = "tests\\test1\\test1_items1.in";
        String fileItems2 = "tests\\test1\\test1_items2.in";
        String fileOutput = "tests\\test1\\test1_output.in";

        initFiles(fileTrainer1, fileTrainer2, fileItems1, fileItems2, fileOutput);
        Arena arena = new Arena(inputTrainer1, inputTrainer2, items1, items2, outputFile);
        arena.fight();
    }

    @org.junit.jupiter.api.Test
    public void Test2() {
        String fileTrainer1 = "tests\\\\test2\\\\test2_antrenor1.in";
        String fileTrainer2 = "tests\\test2\\test2_antrenor2.in";
        String fileItems1 = "tests\\test2\\test2_items1.in";
        String fileItems2 = "tests\\test2\\test2_items2.in";
        String fileOutput = "tests\\test2\\test2_output.in";

        initFiles(fileTrainer1, fileTrainer2, fileItems1, fileItems2, fileOutput);
        Arena arena = new Arena(inputTrainer1, inputTrainer2, items1, items2, outputFile);
        arena.fight();
    }

}
