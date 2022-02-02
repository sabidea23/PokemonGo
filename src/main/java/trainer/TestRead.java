package trainer;

import input.ReadTrainer;

import java.io.File;

public class TestRead {
    public static void main(String[] args) throws Exception {
        File file1 = new File("tests\\test1\\test1_antrenor1.in");
        File file2 = new File("tests\\test1\\test1_items.in");
        Trainer trainer = ReadTrainer.readTrainer(file1, file2);

        System.out.println(trainer);
    }
}
