import java.util.Stack;

public class TopologicalSort {
    private boolean[] marked;
    private Stack <Integer> reversePostOrder;

    public TopologicalSort(DiGraph graph) {
        this.reversePostOrder = new Stack <>();
        this.marked = new boolean[graph.getVertices()];
        for (int vertex = 0; vertex < graph.getVertices(); vertex++) {
            if (!marked[vertex])
                dfs(graph,vertex);
        }

    }



    private void dfs(DiGraph graph, int vertex) {
        this.marked[vertex] = true;
        for (int w : graph.adj(vertex)) {
            if (!marked[w]) dfs(graph,w);
            this.reversePostOrder.push(vertex);
        }
    }

    public TopologicalSort(EdgeWeightedDiGraph graph) {
        this.reversePostOrder = new Stack <>();
        this.marked = new boolean[graph.getVertices()];
        for (int vertex = 0; vertex < graph.getVertices(); vertex++) {
            if (!marked[vertex])
                dfs(graph,vertex);
        }

    }

    private void dfs(EdgeWeightedDiGraph graph, int vertex) {
        this.marked[vertex] = true;
        for (DirectedEdge e : graph.adj(vertex)) {
            if (!marked[e.to()]) dfs(graph,e.to());
            this.reversePostOrder.push(vertex);
        }
    }
    public Iterable<Integer> reversePost() {
        return this.reversePostOrder;
    }
}
