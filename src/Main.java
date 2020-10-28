public class Main {

    public static void main(String[] args) {

//        String inputString = "( Rain     && Inside )  || (Sunny && Outside) || (NoIdea)";
//        String inputString = "        ( Warm && ~Rain ) <=> Pleasant";
          String inputString = "           ~(P&&~Q)||R=>S&&~T   ";
//          String inputString = "           (A && B ) || C  ";
//          String inputString = "           ~(A && B ) || C  ";
//          String inputString = "           (A && B ) || ~(C ||    D)  ";


        InfixToPostfix postfixExpression = new InfixToPostfix(inputString);
        postfixExpression.printPostfix();
        TreeNode expressionTree = postfixExpression.getExpressionTree();
        expressionTree.printLevelOrderTraversal();

        CNFGenerator cnfGenerator = new CNFGenerator();
        expressionTree = cnfGenerator.postOrder(expressionTree);
        expressionTree.printRoot();
//        expressionTree.printLevelOrderTraversal();

    }
}
