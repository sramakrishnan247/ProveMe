package main.java;

import java.util.LinkedList;

public class TreeNode {
    private String stringVal;
    private boolean operand;
    private TreeNode left;
    private TreeNode right;
    private TreeNode uniqueChild;

    private ConjunctiveNormalForm CNF;

    public TreeNode(){
        this.stringVal = "";
        this.left = null;
        this.right = null;
    }

    public TreeNode(String stringVal, boolean operand ){
        this.stringVal = stringVal;
        this.operand = operand;
        this.left = null;
        this.right = null;
        this.uniqueChild = null;
    }

    public TreeNode(String stringVal, boolean operand, TreeNode uniqueChild ){
        this.stringVal = stringVal;
        this.operand = operand;
        this.left = null;
        this.right = null;
        this.uniqueChild = uniqueChild;
        this.CNF = null;
    }

    public TreeNode(String stringVal, boolean operand, TreeNode left, TreeNode right){
        this.stringVal = stringVal;
        this.operand = operand;
        this.left = left;
        this.right = right;
        this.uniqueChild = null;
        this.CNF = null;
    }

    public String getStringVal() {
        return stringVal;
    }

    public ConjunctiveNormalForm getCNF() {
        return CNF;
    }

    public boolean isOperand() {
        return operand;
    }

    public void setCNF(ConjunctiveNormalForm CNF) {
        this.CNF = CNF;
    }

    @Override
    public String toString() {
        String CNFString = CNF == null? "":CNF.toString();
        return "TreeNode{" +
                "stringVal='" + stringVal + '\'' +
//                ", operand=" + operand +
//                ", left=" + left +
//                ", right=" + right +
//                ", uniqueChild=" + uniqueChild +
                ", \nCNF=\n" + CNFString +
                '}';
    }

    public void printLevelOrderTraversal(){
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.addFirst(this);
        System.out.println("Level order traversal: ");
        while(!queue.isEmpty()){
            int levelCount = queue.size();
            for(int i = 0; i < levelCount; i++){
                TreeNode front = queue.removeFirst();
                System.out.print(front+ "   ");

                if(front.uniqueChild != null){
                    queue.addLast(front.uniqueChild);
                }

                if(front.left != null){
                    queue.addLast(front.left);
                }

                if(front.right != null){
                    queue.addLast(front.right);
                }
            }
            System.out.println();
        }
    }

    public void printRoot(){
//        System.out.println("Root Node CNF");
        System.out.println(this);
    }


    public boolean isLeftChild() {
        if(left != null)
            return true;
        return false;
    }
    public boolean isRightChild() {
        if(right != null)
            return true;
        return false;
    }
    public boolean isUniqueChild() {
        if(uniqueChild != null)
            return true;
        return false;
    }

    public TreeNode getLeft() {
        return left;
    }

    public TreeNode getRight() {
        return right;
    }

    public TreeNode getUniqueChild() {
        return uniqueChild;
    }

}
