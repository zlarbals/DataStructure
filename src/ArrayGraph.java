public class ArrayGraph {
    private int vertexNumber;
    private int edgeNumber;
    private int[][] array;

    public ArrayGraph(int vertexNumber) {
        this.vertexNumber = vertexNumber;
        this.edgeNumber = 0;
        this.array = new int[this.vertexNumber][this.vertexNumber];
    }

    public void AddEdge(int start,int end){//consider use enum
        array[start][end]=1;
        this.edgeNumber++;
    }
}
