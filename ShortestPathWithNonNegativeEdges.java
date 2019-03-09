import java.util.Stack;

public class ShortestPathWithNonNegativeEdges {
    private DirectedEdge[] edgeTo;
    private double[] distTo;
    private IndexMinPQ <Double> pq;

    public ShortestPathWithNonNegativeEdges(EdgeWeightedDiGraph graph, int source) {
        this.edgeTo = new DirectedEdge[graph.getVertices()];
        this.distTo = new double[graph.getVertices()];
        this.pq = new IndexMinPQ <>(graph.getVertices());

        for (int v = 0; v < graph.getVertices(); v++)
            this.distTo[v] = Double.POSITIVE_INFINITY;
        distTo[source] = 0.0;

        pq.insert(source, 0.0);
        while (!pq.isEmpty()) {

            int v = pq.delMin();
            for (DirectedEdge e : graph.adj(v))
                relax(e);
        }

    }

    private void relax(DirectedEdge e) {
        int v = e.from();
        int w = e.to();
        if (distTo[w] > distTo[v] + e.weight()) {
            distTo[w] = distTo[v] + e.weight();
            edgeTo[w] = e;
            if (pq.contains(w)) pq.decreaseKey(w, distTo[w]);
            else pq.insert(w, distTo[w]);
        }
    }

    public double distTo(int v) {
        return this.distTo[v];
    }

    public Iterable <DirectedEdge> pathTo(int v) {
        Stack <DirectedEdge> path = new Stack <>();
        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()])
            path.push(e);
        return path;
    }

    public static void main(String[] args) {
        final int vertices = 8;
        final int source = 0;
        EdgeWeightedDiGraph graph = new EdgeWeightedDiGraph(vertices);
        System.out.println(graph.getVertices());
        System.out.println("***********");
        graph.addEdge(new DirectedEdge(0, 1, 5));
        graph.addEdge(new DirectedEdge(0, 4, 9));
        graph.addEdge(new DirectedEdge(0, 7, 8));
        graph.addEdge(new DirectedEdge(1, 2, 12));
        graph.addEdge(new DirectedEdge(1, 3, 15));
        graph.addEdge(new DirectedEdge(1, 7, 4));
        graph.addEdge(new DirectedEdge(2, 3, 3));
        graph.addEdge(new DirectedEdge(2, 6, 11));
        graph.addEdge(new DirectedEdge(3, 6, 9));
        graph.addEdge(new DirectedEdge(4, 5, 4));
        graph.addEdge(new DirectedEdge(4, 6, 20));
        graph.addEdge(new DirectedEdge(4, 7, 5));
        graph.addEdge(new DirectedEdge(5, 2, 1));
        graph.addEdge(new DirectedEdge(5, 6, 13));
        graph.addEdge(new DirectedEdge(7, 5, 6));
        graph.addEdge(new DirectedEdge(7, 2, 7));
        ShortestPathWithNonNegativeEdges sp = new ShortestPathWithNonNegativeEdges(graph, source);
        for (int v = 0; v < graph.getVertices(); v++) {
            System.out.println(sp.distTo(v));
            System.out.println(sp.pathTo(v));
            System.out.println();
        }
    }
}
