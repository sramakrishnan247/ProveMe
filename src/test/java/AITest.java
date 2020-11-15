package test.java;

import java.util.ArrayList;
import java.util.List;
import main.java.AI;

import static org.junit.jupiter.api.Assertions.*;

class AITest {

    @org.junit.jupiter.api.Test
    void prove() {
        AI test = new AI();
        String inputFile0 = "./src/test/resources/test0.txt";
        String inputFile1 = "./src/test/resources/test1.txt";
        String inputFile2 = "./src/test/resources/test2.txt";
        String inputFile3 = "./src/test/resources/test3.txt";

        ArrayList<Boolean> test0 = new ArrayList<>(List.of(true,false));
        ArrayList<Boolean> test1 = new ArrayList<>(List.of(true));
        ArrayList<Boolean> test2 = new ArrayList<>(List.of(false));
        ArrayList<Boolean> test3 = new ArrayList<>(List.of(false));

        assertEquals(test0, test.prove(inputFile0));
        assertEquals(test1, test.prove(inputFile1));
        assertEquals(test2, test.prove(inputFile2));
        assertEquals(test3, test.prove(inputFile3));

    }
}