public class ListGraph {
    private int vertexNumber;
    private Vertex[] vertex;

    private class Vertex{
        private int data;
        private Vertex nextEdge;

        public Vertex(){
            this.nextEdge=null;
        }
    }

    public ListGraph(int vertexNumber){
        this.vertexNumber=vertexNumber;
        vertex=new Vertex[vertexNumber];
        for(int i=0;i<vertexNumber;i++){
            vertex[i]=new Vertex();
            vertex[i].data=i;
        }
    }

    public void AddEdge(int start,int end){
        Vertex newVertex = new Vertex();
        newVertex.data=end;
        Vertex temp = new Vertex();
        if(vertex[start].nextEdge == null){
            vertex[start].nextEdge=newVertex;
        }else{
            temp=vertex[start];
            while(temp.nextEdge != null){
                temp=temp.nextEdge;
            }
            temp.nextEdge=newVertex;
        }

    }

    public void Show(){
        for(int i=0;i<this.vertexNumber;i++) {
            Vertex temp = vertex[i];
            while (temp.nextEdge != null) {
                System.out.print(temp.data+ " -> ");
                temp = temp.nextEdge;
            }
            System.out.println(temp.data);
        }
    }

}
