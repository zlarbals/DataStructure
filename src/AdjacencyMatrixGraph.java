public class AdjacencyMatrixGraph {
    private int vertexNumber;
    private int[][] array;

    public AdjacencyMatrixGraph(int vertexNumber) {
        this.vertexNumber = vertexNumber;
        this.array = new int[this.vertexNumber+1][this.vertexNumber+1];
    }

    public void AddEdge(int start,int end,int weight){
        array[start][end]=weight;
    }

    public void DeleteEdge(int start,int end){
        array[start][end]=0;
    }

    public void AddVertex(int newVertexNumber){
        if(newVertexNumber<=this.vertexNumber)
            return;

        int[][] newArray=new int[newVertexNumber+1][newVertexNumber+1];
        for(int i=1;i<=this.vertexNumber;i++){
            for(int j=1;j<=this.vertexNumber;j++)
                newArray[i][j]=this.array[i][j];
        }
        this.vertexNumber=newVertexNumber;
        this.array=newArray;
    }

    public void DeleteVertex(int vertex){
        if(vertex>this.vertexNumber)
            return;

        for(int i=1;i<=vertexNumber;i++){
            array[vertex][i]=0;
            array[i][vertex]=0;
        }
    }

}
