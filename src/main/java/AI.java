package main.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class AI {

    public AI(){

    }

    public ArrayList<Boolean> prove(String inputFile){

        File file = new File(inputFile);

        ArrayList<String> inputKB = new ArrayList<>();
        ArrayList<String> inputSentences = new ArrayList<>();
        ArrayList<Clause> knowledgeBase = new ArrayList<>();
        ArrayList<Boolean> results = new ArrayList<>();

        try{
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()){
                String input = scanner.nextLine();
                //Read KB expressions
                if(input.equals("Knowledge Base:")){
                    while (scanner.hasNextLine()){
                        input = scanner.nextLine();
                        if(input.matches("\\s*")){
                            continue;
                        }
                        if(input.equals("Prove the following sentences by refutation:")){
                            break;
                        }
                        inputKB.add(input);
                    }
                }
                while (scanner.hasNextLine()){
                    input = scanner.nextLine();
                    if(input.matches("\\s*")){
                        continue;
                    }
                    inputSentences.add(input);
                }
            }

            System.out.println("KB expressions");
            for(String expression : inputKB){
                System.out.println(expression);
            }
            System.out.println();

            System.out.println("Sentences to be proved:");
            for(String sentence: inputSentences){
                System.out.println(sentence);
            }
            System.out.println();

            scanner.close();

            //Preprocess the input KB and store all the expressions as CNF.
            for(String inputString: inputKB){
                InfixToPostfix postfixExpression = new InfixToPostfix(inputString);
                TreeNode expressionTree = postfixExpression.getExpressionTree();
                CNFGenerator cnfGenerator = new CNFGenerator();
                expressionTree = cnfGenerator.postOrder(expressionTree);
                ConjunctiveNormalForm conjunctiveNormalForm = expressionTree.getCNF();
                knowledgeBase.addAll(conjunctiveNormalForm.getCNF());
            }

            System.out.println("Theorem Prover working...");
            System.out.println();
            //Theorem Proving for each input sentence
            for(String sentence : inputSentences){
                System.out.println("------------------------------------");
                String negatedSentence = "~(" + sentence + ")";
                Prover prover = new Prover(new ArrayList<>(knowledgeBase), negatedSentence);
                System.out.println("Knowledge base");
                System.out.println(knowledgeBase);
                System.out.println();
                System.out.print("To prove: ");
                System.out.println(sentence);
                System.out.println();
                System.out.println("Resolution as follows...");
                System.out.println();
                results.add(prover.plResolution());
                System.out.println(sentence);
                System.out.println();
                System.out.println();


            }

        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }

        System.out.println(results);
        return results;
    }
}
