import java.util.PriorityQueue;
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
            else                pq.insert(w, distTo[w]);
        }
    }

    public double distTo(int v) {
        return this.distTo[v];
    }

    public Iterable<DirectedEdge> pathTo(int v) {
        Stack<DirectedEdge> path = new Stack <>();
        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()])
            path.push(e);
        return path;
    }
}
