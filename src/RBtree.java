public class RBtree {
    private class Node {
        int key;
        int color;  //black -> 0    red -> 1
        Node leftNode;
        Node rightNode;
        Node parent;

        //Node constructer
        public Node(int key) {
            this.key = key;
            this.color = 1;
            leftNode = null;
            rightNode = null;
            parent = null;
        }

        public Node() {
            leftNode = null;
            rightNode = null;
        }
    }

    private Node head;

    public RBtree() {
        head = null;
    }

    public void Insert(int key) {
        Node target;
        Node insertNode = new Node(key);

        if (head == null) {
            head = insertNode;
            insertNode.color = 0;//black
            insertNode.parent = null;
            return;
        }
        target = head;

        while (target != null) {
            //삽입 하려는 키가 비교 노드키보다 작으면
            if (target.key > key) {
                //이동할 target 값이 null 이라면
                if (target.leftNode == null) {
                    ConnectHelper(target, insertNode, true);
                    break;
                }
                target = target.leftNode;
            }
            //삽입 하려는 키가 비교 노드키보다 크면
            else {
                //이동할 target 값이 null 이라면
                if (target.rightNode == null) {
                    ConnectHelper(target, insertNode, false);
                    break;
                }
                target = target.rightNode;
            }
        }
        CheckColor(insertNode);
    }

    public void Delete(int key) {
        Node target = Search(key);
        int targetColor = target.color;
        Node rightMinNode;

        boolean isLeftNode = true;
        boolean isHead = false;

        if (target == null)
            return;

        if (target == head)
            isHead = true;
        else if (target.parent.rightNode == target)
            isLeftNode = false;
        else if (target.parent.leftNode == target)
            isLeftNode = true;

        //1.target의 오른쪽 자식이 없는 경우.
        if (target.rightNode == null) {
            if (isHead) {
                head = target.leftNode;
                target.leftNode.parent = null;
            } else
                ConnectHelper(target.parent, target.leftNode, isLeftNode);
        }
        //2.target의 오른쪽 자식의 왼쪽자식이 없는경우
        else if (target.rightNode.leftNode == null) {
            if (isHead) {
                ConnectHelper(target.rightNode,target.leftNode,true);
                target.rightNode.parent = null;
                head = target.rightNode;
            } else
                ConnectHelper(target.parent, target.rightNode, isLeftNode);
        }
        //3.나머지, target의 오른쪽 자식도 존재하고, 오른쪽 자식의 왼쪽자식도 존재할 경우
        else {
            rightMinNode = getRightMinNode(target);

            ConnectHelper(rightMinNode.parent, rightMinNode.rightNode, true);

            ConnectHelper(rightMinNode, target.leftNode, true);

            ConnectHelper(rightMinNode, target.rightNode, false);

            if (isHead) {
                rightMinNode.parent = null;
                head = rightMinNode;
            } else
                ConnectHelper(target.parent, rightMinNode, isLeftNode);

        }

        if (isHead)
            head.color = 0;
        else if (targetColor == 0)
            DeleteCheckColor(target, isLeftNode);

    }

    private void DeleteCheckColor(Node node, boolean direction) {
        while (node != head && node.color == 0) {
            if (direction) {
                Node uncle = node.parent.rightNode;
                if (uncle.color == 1) {
                    uncle.color = 0;
                    node.parent.color = 1;
                    RotateLeft(node.parent);
                    uncle = node.parent.rightNode;
                }
                if ((uncle.leftNode == null || uncle.leftNode.color == 0) && (uncle.rightNode == null || uncle.rightNode.color == 0)) {
                    uncle.color = 1;
                    node = node.parent;
                    continue;
                } else if (uncle.rightNode == null || uncle.rightNode.color == 0) {
                    uncle.leftNode.color = 0;
                    uncle.color = 1;
                    RotateRight(uncle);
                    uncle = node.parent.rightNode;
                }
                if (uncle.rightNode.color == 1) {
                    uncle.color = node.parent.color;
                    node.parent.color = 0;
                    uncle.rightNode.color = 0;
                    RotateLeft(node.parent);
                    node = head;
                }
            } else {
                Node uncle = node.parent.leftNode;
                if (uncle.color == 1) {
                    uncle.color = 0;
                    node.parent.color = 1;
                    RotateRight(node.parent);
                    uncle = node.parent.leftNode;
                }
                if ((uncle.rightNode == null || uncle.rightNode.color == 0) && (uncle.leftNode == null || uncle.leftNode.color == 0)) {
                    uncle.color = 1;
                    node = node.parent;
                    continue;
                } else if (uncle.leftNode == null || uncle.leftNode.color == 0) {
                    uncle.rightNode.color = 0;
                    uncle.color = 1;
                    RotateLeft(uncle);
                    uncle = node.parent.leftNode;
                }
                if (uncle.leftNode.color == 1) {
                    uncle.color = node.parent.color;
                    node.parent.color = 0;
                    uncle.leftNode.color = 0;
                    RotateRight(node.parent);
                    node = head;
                }
            }
        }
        node.color = 0;
    }

    private void ConnectHelper(Node high, Node low, boolean direction) {
        if (direction)
            high.leftNode = low;
        else
            high.rightNode = low;

        if (low != null)
            low.parent = high;
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

    public Node Search(int key) {
        Node target = head;
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

    private void CheckColor(Node node) {
        Node uncle;
        while (node.parent != null && node.parent.color == 1) {
            if (node.parent == node.parent.parent.leftNode) {
                uncle = node.parent.parent.rightNode;

                if (uncle != null && uncle.color == 1) {
                    //re coloring
                    ReColoring(node, uncle);
                    node = node.parent.parent;
                    continue;
                }
                if(node == node.parent.rightNode){
                    //double rotate
                    node=node.parent;
                    RotateLeft(node);
                }

                //re structuring
                node.parent.color = 0;
                node.parent.parent.color = 1;
                RotateRight(node.parent.parent);
            } else {
                uncle = node.parent.parent.leftNode;

                if (uncle != null && uncle.color == 1) {
                    //re coloring
                    ReColoring(node, uncle);
                    node = node.parent.parent;
                    continue;
                }
                if(node==node.parent.leftNode){
                    //double rotate
                    node=node.parent;
                    RotateRight(node);
                }

                //re structruing
                node.parent.color = 0;
                node.parent.parent.color = 1;
                RotateLeft(node.parent.parent);
            }
        }
        //because of re coloring.
        head.color = 0;
    }

    private void ReColoring(Node node, Node uncle) {
        node.parent.color = 0;
        uncle.color = 0;
        node.parent.parent.color = 1;
    }

    private void RotateRight(Node node) {
        boolean isHead = false;

        if (node.parent == null)
            isHead = true;
        else if (node == node.parent.leftNode)
            node.parent.leftNode = node.leftNode;
        else if (node == node.parent.rightNode)
            node.parent.rightNode = node.leftNode;

        node.leftNode.parent = node.parent;
        node.parent = node.leftNode;
        if (node.leftNode.rightNode != null)
            node.leftNode.rightNode.parent = node;
        node.leftNode = node.leftNode.rightNode;
        node.parent.rightNode = node;

        //node가 head 일 때
        if (isHead)
            head = node.parent;
    }

    private void RotateLeft(Node node) {
        boolean isHead = false;

        if (node.parent == null)
            isHead = true;
        else if (node == node.parent.leftNode)
            node.parent.leftNode = node.rightNode;
        else if (node == node.parent.rightNode)
            node.parent.rightNode = node.rightNode;

        node.rightNode.parent = node.parent;
        node.parent = node.rightNode;

        if (node.rightNode.leftNode != null)
            node.rightNode.leftNode.parent = node;

        node.rightNode = node.rightNode.leftNode;
        node.parent.leftNode = node;

        if (isHead)
            head = node.parent;
    }
}
