package arena;

import input.ReadTrainer;
import trainer.Trainer;

import java.io.File;

public class TestArena {
    public static void main(String[] args) throws Exception {
        File file1 = new File("tests\\test1\\test1_antrenor1.in");
        File file2 = new File("tests\\test1\\test1_items1.in");
        Trainer trainer = ReadTrainer.readTrainer(file1, file2);

        System.out.println(trainer);
        Prepare.giveItems(trainer);

        System.out.println(trainer);
    }
}
