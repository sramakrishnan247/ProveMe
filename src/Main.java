public class Main {

    public static void main(String[] args) {

//        String inputString = "( Rain     && Outside )    => Wet";
        String inputString = "        ( Warm && ~Rain ) <=> Pleasant";
//          String inputString = "           ~(P&&~Q)||R=>S&&~T   ";


        InfixToPostfix postfixExpression = new InfixToPostfix(inputString);
        postfixExpression.printPostfix();
        TreeNode expressionTree = postfixExpression.getExpressionTree();
        expressionTree.printLevelOrderTraversal();

    }
}
