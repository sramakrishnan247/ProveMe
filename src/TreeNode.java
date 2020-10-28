import java.util.LinkedList;
import java.util.Queue;

public class TreeNode {
    private String val;
    private boolean operand;
    private TreeNode left;
    private TreeNode right;
    private TreeNode uniqueChild;

    public TreeNode(String val, boolean operand ){
        this.val = val;
        this.operand = operand;
        this.left = null;
        this.right = null;
        this.uniqueChild = null;

    }

    public TreeNode(String val, boolean operand, TreeNode uniqueChild ){
        this.val = val;
        this.operand = operand;
        this.left = null;
        this.right = null;
        this.uniqueChild = uniqueChild;
    }

    public TreeNode(String val, boolean operand, TreeNode left, TreeNode right){
        this.val = val;
        this.operand = operand;
        this.left = left;
        this.right = right;
        this.uniqueChild = null;
    }

    public String getVal() {
        return val;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "val='" + val + '\'' +
                ", operand=" + operand +
                ", left=" + left +
                ", right=" + right +
                ", uniqueChild=" + uniqueChild +
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
                System.out.print(front.val+" ");

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
}
