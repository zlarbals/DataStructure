/*
Array Circular Queue
blackgirin
2019-11-19
 */

public class ArrayQueue {
    private int front;
    private int rear;
    private int maxSize;
    private int array[];//자료형은 필요에 따라 변경.
    private int returnData;

    public ArrayQueue(int maxSize) {
        front = -1;
        rear = -1;
        this.maxSize = maxSize;
        array = new int[maxSize];
    }

    public void Enqueue(int data) {
        if (IsFull()) {
            System.out.println("Queue is full");
            return;
        }

        //empty상태의 queue에서 enqueue를 진행함으로써 front의 역할을 할 수 있도록
        //front에 0을 할당.
        if (front == -1) {
            front = 0;
        }

        rear = (rear+1)%maxSize;
        array[rear] = data;
    }

    public int Dequeue() {
        if(IsEmpty()){
            System.out.println("Queue is empty");
            return 0;
        }

        //front와 rear이 같으면 데이터가 queue에 하나 남아있음을 의미.
        //그 상태에서 Dequeue를 진행하면 queue는 empty상태이므로
        // Queue를 empty상태로 변경 하고 하나 남아있던 data반환.
        if(front == rear) {
            returnData = array[front];
            MakeEmptyState();
            return returnData;
        }
        else {
            returnData = array[front];
            front = (front + 1) % maxSize;
            return returnData;
        }
    }

    public int QPeek() {
        if(IsEmpty()){
            System.out.println("Queue is empty");
            return 0;
        }
        return array[front];
    }

    public boolean IsEmpty() {
        if (front == -1 && rear == -1)
            return true;
        else
            return false;
    }

    public boolean IsFull() {
        if ((rear+1)%maxSize == front)
            return true;
        else
            return false;
    }

    public void MakeEmptyState(){
        this.front=-1;
        this.rear=-1;
    }

    public void Print(){
        if(front>rear){//circular queue 이기 때문에 front 가 rear 보다 큰 경우
            for(int i=front;i<maxSize;i++)
                System.out.print(array[i]+" ");

            for(int i=0;i<=rear;i++)
                System.out.print(array[i]+" ");
        }
        else{
            for(int i=front;i<=rear;i++)
                System.out.print(array[i]+" ");
        }
    }
}
