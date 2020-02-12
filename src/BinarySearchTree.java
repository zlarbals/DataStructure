public class BinarySearchTree {
    private class Node {
        int key;
        Node leftNode;
        Node rightNode;

        //Node constructer
        public Node(int key) {
            this.key = key;
            leftNode = null;
            rightNode = null;
        }

        public Node() {
            leftNode = null;
            rightNode = null;
        }
    }

    private Node head;

    //BinarySearchTree constructer
    public BinarySearchTree() {
        head = new Node();
    }

    public void Insert(int key) {
        Node parent, target;
        parent = head;
        target = head.rightNode;
        Node tempParent;
        Node newNode = new Node(key);

        if (target == null) {
            parent.rightNode = newNode;
            return;
        }

        while (target != null) {
            parent = target;
            //삽입 하려는 키가 비교 노드키보다 작으면
            if (parent.key > key) {
                target = target.leftNode;

                //이동한 temp값이 null이라면
                if (target == null) {
                    parent.leftNode = newNode;
                    return;
                }
            }
            //삽입 하려는 키가 비교 노드키보다 크면
            else {
                target = target.rightNode;

                //이동한 temp값이 null이라면
                if (target == null) {
                    parent.rightNode = newNode;
                    return;
                }
            }
        }

    }

    public void Delete(int key) {
        Node parent, target, rightMinNode, rightMinParent;
        parent = SearchTargetParent(key);
        target = Search(key);

        boolean isLeftNode;

        if (target == null)
            return;

        if (parent.rightNode == target)
            isLeftNode = false;
        else
            isLeftNode = true;

        //1.target의 오른쪽 자식이 없는 경우.
        if (target.rightNode == null) {
            if (isLeftNode)
                parent.leftNode = target.leftNode;
            else
                parent.rightNode = target.leftNode;
        }
        //2.target의 오른쪽 자식의 왼쪽자식이 없는경우
        else if (target.rightNode.leftNode == null) {
            if (isLeftNode) {
                parent.leftNode = target.rightNode;
            } else
                parent.rightNode = target.rightNode;
        }
        //3.나머지, target의 오른쪽 자식도 존재하고, 오른쪽 자식의 왼쪽자식도 존재할 경우
        else {
            //target의 오른쪽 자식 중 최솟값 찾고
            rightMinNode = getRightMinNode(target);
            //target의 오른쪽 자식의 최솟값 부모노드
            rightMinParent = SearchTargetParent(rightMinNode.key);
            rightMinParent.leftNode = rightMinNode.rightNode;
            rightMinNode.leftNode = target.leftNode;
            rightMinNode.rightNode = target.rightNode;

            if (isLeftNode)
                parent.leftNode = rightMinNode;
            else
                parent.rightNode = rightMinNode;

        }
    }

    public Node Search(int key) {
        Node target = head.rightNode;
        while (target != null) {
            if (target.key == key)
                return target;

            if (target.key > key)
                target = target.leftNode;
            else
                target = target.rightNode;
        }
        return null;
    }

    private Node SearchTargetParent(int key) {
        Node parent, target;
        parent = head;
        target = head.rightNode;
        while (target != null) {

            if (target.key == key)
                return parent;
            parent = target;
            if (target.key > key)
                target = target.leftNode;
            else
                target = target.rightNode;
        }
        return null;
    }

    private Node getRightMinNode(Node targetNode) {
        Node target, temp;
        target = targetNode;
        temp = targetNode.rightNode;
        while (temp != null) {
            target = temp;
            temp = temp.leftNode;
        }
        return target;
    }
}
