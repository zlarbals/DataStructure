/*
ArrayStack
zlarbals
2019-11-07
 */

public class ArrayStack {
    private int top;
    private int maxSize;
    private Object[] stackArray; //자료형은 필요에 따라 변경 필요.

    //stack 생성
    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        this.stackArray = new Object[maxSize];
        this.top = -1;
    }

    //stack data 입력.
    public void Push(Object data){
        if(isFull())
            return;

        stackArray[++top] = data;
    }

    //stack data 제거.
    public Object Pop(){
        if(isEmpty())
            return 0;
        return stackArray[top--];
    }

    //stack 가장 위 data 반환.
    public Object Peek(){
        if(isEmpty())
            return 0;

        return stackArray[top];
    }


    public boolean isEmpty(){
        if(top==-1)
            return true;
        else
            return false;

    }

    public boolean isFull(){
        if(top == maxSize - 1)
            return true;
        else
            return false;
    }
}

