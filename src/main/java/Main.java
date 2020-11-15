public class Main {

    public static void main(String[] args) {

        String inputFile = "./src/main/resources/test0.txt";
//        String inputFile = "./src/main/resources/test1.txt";
//        String inputFile = "./src/main/resources/test2.txt";
//        String inputFile = "./src/main/resources/test3.txt";
        AI ai = new AI();
        ai.prove(inputFile);
    }
}
