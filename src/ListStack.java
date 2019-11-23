/*
ListStack
blackgirin
2019-11-23
 */

public class ListStack {
    private Node top;

    private class Node{
        private int data;
        private Node nextNode;

        public Node(int data){
            this.data=data;
            this.nextNode=null;
        }
    }

    public ListStack(){
        this.top=null;
    }

    public void Push(int data){
        Node node=new Node(data);
        node.nextNode=top;
        top=node;
    }

    public int Pop(){
        if(isEmpty()){
            return 0;
        }
        int data=top.data;
        top = top.nextNode;
        return data;
    }

    public int Peek(){
        if(isEmpty())
            return 0;
        return top.data;
    }

    public boolean isEmpty(){
        return (top==null);
    }

}


