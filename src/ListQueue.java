/*
ListQueue
zlarbals
2019-11-24
 */
public class ListQueue {
    private class Node{
        private int data;
        private Node nextNode;

        private Node(int data){
            this.data=data;
            nextNode = null;
        }
    }

    private Node front;
    private Node rear;

    public ListQueue(){
        //data가 없는상태 초기화.
        front=null;
        rear=null;
    }

    public void Enqueue(int data){
        Node node = new Node(data);
        node.nextNode=null;

        //Queue가 비어있으면
        if(isEmpty()){
            front=node;
            rear=node;
        }

        else{
            rear.nextNode=node;
            rear=node;
        }

    }

    public int Dequeue(){
        if(isEmpty())
            return 0;

        else{
            int data=front.data;
            front=front.nextNode;

            //front가 null이 되면 Queue에 데이터가 없는 상태이므로
            //rear도 null로 초기화.
            if(front==null){
                rear=null;
            }

            return data;
        }
    }

    public int QPeek(){
        if(isEmpty())
            return 0;

        return front.data;
    }

    public boolean isEmpty(){
        return (front == null);
    }
}
