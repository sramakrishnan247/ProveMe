import java.util.ArrayList;

/**
 * Takes the input KB and an expression.
 * This class performs resolution to check if KB entails
 * the fed input expression.
 */

public class Prover {
    ArrayList<Clause> KB;
    String sentence;

    public Prover(ArrayList<Clause> inputKB, String sentence ){
        KB = new ArrayList<>();
        this.KB.addAll(inputKB);
        InfixToPostfix postfixExpression = new InfixToPostfix(sentence);
        CNFGenerator cnfGenerator = new CNFGenerator();
        TreeNode expressionTree = cnfGenerator.postOrder(postfixExpression.getExpressionTree());
        ConjunctiveNormalForm conjunctiveNormalForm = expressionTree.getCNF();
        KB.addAll(conjunctiveNormalForm.getCNF());
    }

    @Override
    public String toString() {
        String claueses = "";
        for(Clause clause : KB){
            claueses += clause;
            claueses += "\n";
        }
        return "Prover{" +
                "KB=\n" + claueses +
                '}';
    }
}

