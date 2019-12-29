class TreeNode {
    public int data;
    public TreeNode leftChildNode;
    public TreeNode rightChildNode;

    public TreeNode(int data)
    {
        this.data = data;
        this.leftChildNode = null;
        this.rightChildNode = null;
    }
}

public class ListTree {

    public void MakeLeftSubTree(TreeNode parent, TreeNode leftChild) {
        parent.leftChildNode = leftChild;
    }

    public void MakeRightSubTree(TreeNode parent, TreeNode rightChild) {
        parent.rightChildNode = rightChild;
    }

    public void PreOrder(TreeNode root) {//전위 순회
        if (root != null) {
            System.out.print(root.data + " ");
            PreOrder(root.leftChildNode);
            PreOrder(root.rightChildNode);
        }
    }

    public void InOrder(TreeNode root) {//중위 순회
        if (root != null) {
            InOrder(root.leftChildNode);
            System.out.print(root.data + " ");
            InOrder(root.rightChildNode);
        }
    }

    public void PostOrder(TreeNode root) {//후위 순회
        if (root != null) {
            PostOrder(root.leftChildNode);
            PostOrder(root.rightChildNode);
            System.out.print(root.data + " ");
        }
    }


}
