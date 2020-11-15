package main.java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class InfixToPostfix {

    private String expression;
    private Stack<String> operatorStack;
    private HashMap<String,Integer> inputPrecedence;
    private HashMap<String,Integer> stackPrecedence;
    private TreeNode expressionTreeRoot;
    ArrayList<String> postfixList;

    public InfixToPostfix(String expression){
        this.expression = expression;
        fillMap();
        infixToPostfix();
        generateTree();
    }

    public TreeNode getExpressionTree(){
        return expressionTreeRoot;
    }

    private void generateTree() {
        Stack<TreeNode> expressionTreeStack = new Stack<>();
        for(String word: postfixList){
            if(isOperand(word)){
                expressionTreeStack.add(new TreeNode(word, true));
            }
            else{
                //handle negation separately
                if(word.equals("~")){
                    TreeNode topNode = expressionTreeStack.pop();
                    TreeNode negationNode = new TreeNode(word, false, topNode);
                    expressionTreeStack.add(negationNode);
                }
                //for every logical connection pop the top two items in the stack
                //create a new node with the left and right children as these popped nodes
                else{
                    TreeNode right = expressionTreeStack.pop();
                    TreeNode left = expressionTreeStack.pop();
                    TreeNode newNode = new TreeNode(word, false, left, right);
                    expressionTreeStack.add(newNode);
                }
            }
        }
        expressionTreeRoot = expressionTreeStack.peek();
    }

    private void infixToPostfix(){
        postfixList = new ArrayList<>();
        operatorStack = new Stack<>();

        ArrayList<String> processedString = parseInput(expression);
        for(String word: processedString){
            if(isOperand(word)){
                postfixList.add(word);
            }
            else{
                outputHigherOrEqual(word);
            }
        }
        while(!operatorStack.empty()){
            postfixList.add(operatorStack.pop());
        }

        return;
    }

    private ArrayList<String> parseInput(String expression) {
        ArrayList<String> infixList = new ArrayList<>();
        String buffer = "";
        boolean logicalConnective = false;

        for(int i = 0; i < expression.length(); i++){
            char ch = expression.charAt(i);
            if(ch == ' '){
                if(buffer.equals(""))
                    continue;
                infixList.add(buffer);
                buffer = "";
            }

            //atomic sentence accumulate char by char
            else if(!logicalConnective && Character.isLetterOrDigit(ch)){
                buffer += ch;
            }
            else if(!Character.isLetterOrDigit(ch)){

                //logical connective follows an atomic sentence without space
                if(!buffer.isEmpty() && !logicalConnective){
                    infixList.add(buffer);
                    buffer = "";
                }

                logicalConnective = true;
                buffer += ch;

                //valid logical connective found in buffer!
                if(stackPrecedence.containsKey(buffer)){
                    infixList.add(buffer);
                    buffer = "";
                    logicalConnective = false;
                }
            }
        }

        //handle last item
        if(!buffer.isEmpty())
            infixList.add(buffer);

        return infixList;
    }

    private void outputHigherOrEqual(String op){

        while(!operatorStack.isEmpty() && stackPrecedence.get(operatorStack.peek()) > inputPrecedence.get(op)){
                postfixList.add(operatorStack.pop());
            }
        if(op.equals(")") && operatorStack.peek().equals("(")){
            operatorStack.pop();
            return;
        }
        else{
            operatorStack.add(op);
        }
    }

    private void fillMap(){
        inputPrecedence = new HashMap<>();
        inputPrecedence.put("~",5);
        inputPrecedence.put("&&",4);
        inputPrecedence.put("||",3);
        inputPrecedence.put("=>",2);
        inputPrecedence.put("<=>",1);
        inputPrecedence.put("(",6);
        inputPrecedence.put(")",0);

        stackPrecedence = new HashMap<>();
        stackPrecedence.put("~",5);
        stackPrecedence.put("&&",4);
        stackPrecedence.put("||",3);
        stackPrecedence.put("=>",2);
        stackPrecedence.put("<=>",1);
        stackPrecedence.put("(",-1);
        stackPrecedence.put(")",0);
    }

    private boolean isOperand(String s){
        if(inputPrecedence.containsKey(s)){
            return false;
        }
        return true;
    }

    public void printPostfix(){
        System.out.println("Infix Expression");
        System.out.println(expression);
        System.out.println("Postfix Expression");
        for(String word: postfixList){
            System.out.print(word);
        }
        System.out.println();
    }

}
