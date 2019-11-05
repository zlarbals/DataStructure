public class main {
    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(5);

        for(int i=0;i<5;i++){
            stack.Push(i);
        }
        System.out.println(stack.Pop());
        System.out.println(stack.Pop());
        System.out.println(stack.Peek());
        System.out.println(stack.Pop());
        System.out.println(stack.Pop());
        System.out.println(stack.Pop());
        System.out.println(stack.Pop());
        System.out.println("-----------------------");
        stack.Push(3);
        System.out.println(stack.Pop());
    }
}
