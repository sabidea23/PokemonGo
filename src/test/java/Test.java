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

    @org.junit.jupiter.api.Test
    public void Test3() {
        String fileTrainer1 = "tests\\\\test3\\\\test3_antrenor1.in";
        String fileTrainer2 = "tests\\test3\\test3_antrenor2.in";
        String fileItems1 = "tests\\test3\\test3_items1.in";
        String fileItems2 = "tests\\test3\\test3_items2.in";
        String fileOutput = "tests\\test3\\test3_output.in";

        initFiles(fileTrainer1, fileTrainer2, fileItems1, fileItems2, fileOutput);
        Arena arena = new Arena(inputTrainer1, inputTrainer2, items1, items2, outputFile);
        arena.fight();
    }

    @org.junit.jupiter.api.Test
    public void Test4() {
        String fileTrainer1 = "tests\\\\test4\\\\test4_antrenor1.in";
        String fileTrainer2 = "tests\\test4\\test4_antrenor2.in";
        String fileItems1 = "tests\\test4\\test4_items1.in";
        String fileItems2 = "tests\\test4\\test4_items2.in";
        String fileOutput = "tests\\test4\\test4_output.in";

        initFiles(fileTrainer1, fileTrainer2, fileItems1, fileItems2, fileOutput);
        Arena arena = new Arena(inputTrainer1, inputTrainer2, items1, items2, outputFile);
        arena.fight();
    }


    @org.junit.jupiter.api.Test
    public void Test5() {
        String fileTrainer1 = "tests\\\\test5\\\\test5_antrenor1.in";
        String fileTrainer2 = "tests\\test5\\test5_antrenor2.in";
        String fileItems1 = "tests\\test5\\test5_items1.in";
        String fileItems2 = "tests\\test5\\test5_items2.in";
        String fileOutput = "tests\\test5\\test5_output.in";

        initFiles(fileTrainer1, fileTrainer2, fileItems1, fileItems2, fileOutput);
        Arena arena = new Arena(inputTrainer1, inputTrainer2, items1, items2, outputFile);
        arena.fight();
    }

    @org.junit.jupiter.api.Test
    public void Test6() {
        String fileTrainer1 = "tests\\\\test6\\\\test6_antrenor1.in";
        String fileTrainer2 = "tests\\test6\\test6_antrenor2.in";
        String fileItems1 = "tests\\test6\\test6_items1.in";
        String fileItems2 = "tests\\test6\\test6_items2.in";
        String fileOutput = "tests\\test6\\test6_output.in";

        initFiles(fileTrainer1, fileTrainer2, fileItems1, fileItems2, fileOutput);
        Arena arena = new Arena(inputTrainer1, inputTrainer2, items1, items2, outputFile);
        arena.fight();
    }


    @org.junit.jupiter.api.Test
    public void Test7() {
        String fileTrainer1 = "tests\\\\test7\\\\test7_antrenor1.in";
        String fileTrainer2 = "tests\\test7\\test7_antrenor2.in";
        String fileItems1 = "tests\\test7\\test7_items1.in";
        String fileItems2 = "tests\\test7\\test7_items2.in";
        String fileOutput = "tests\\test7\\test7_output.in";

        initFiles(fileTrainer1, fileTrainer2, fileItems1, fileItems2, fileOutput);
        Arena arena = new Arena(inputTrainer1, inputTrainer2, items1, items2, outputFile);
        arena.fight();
    }


    @org.junit.jupiter.api.Test
    public void Test8() {
        String fileTrainer1 = "tests\\\\test8\\\\test8_antrenor1.in";
        String fileTrainer2 = "tests\\test8\\test8_antrenor2.in";
        String fileItems1 = "tests\\test8\\test8_items1.in";
        String fileItems2 = "tests\\test8\\test8_items2.in";
        String fileOutput = "tests\\test8\\test8_output.in";

        initFiles(fileTrainer1, fileTrainer2, fileItems1, fileItems2, fileOutput);
        Arena arena = new Arena(inputTrainer1, inputTrainer2, items1, items2, outputFile);
        arena.fight();
    }


    @org.junit.jupiter.api.Test
    public void Test9() {
        String fileTrainer1 = "tests\\\\test9\\\\test9_antrenor1.in";
        String fileTrainer2 = "tests\\test9\\test9_antrenor2.in";
        String fileItems1 = "tests\\test9\\test9_items1.in";
        String fileItems2 = "tests\\test9\\test9_items2.in";
        String fileOutput = "tests\\test9\\test9_output.in";

        initFiles(fileTrainer1, fileTrainer2, fileItems1, fileItems2, fileOutput);
        Arena arena = new Arena(inputTrainer1, inputTrainer2, items1, items2, outputFile);
        arena.fight();
    }


    @org.junit.jupiter.api.Test
    public void Test10() {
        String fileTrainer1 = "tests\\\\test10\\\\test10_antrenor1.in";
        String fileTrainer2 = "tests\\test10\\test10_antrenor2.in";
        String fileItems1 = "tests\\test10\\test10_items1.in";
        String fileItems2 = "tests\\test10\\test10_items2.in";
        String fileOutput = "tests\\test10\\test10_output.in";

        initFiles(fileTrainer1, fileTrainer2, fileItems1, fileItems2, fileOutput);
        Arena arena = new Arena(inputTrainer1, inputTrainer2, items1, items2, outputFile);
        arena.fight();
    }
}
