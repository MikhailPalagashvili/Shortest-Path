public class ShortestPathWithNoCycles {
    private DirectedEdge[] edgeTo;
    private double[] distTo;

    public ShortestPathWithNoCycles(EdgeWeightedDiGraph graph,int source) {
        this.edgeTo = new DirectedEdge[graph.getVertices()];
        this.distTo = new double[graph.getVertices()];

        for (int v = 0; v < graph.getVertices(); v++)
            distTo[v] = Double.POSITIVE_INFINITY;
        distTo[source] = 0.0;

        TopologicalSort topologicalSort = new TopologicalSort(graph);
        for (int v : topologicalSort.reversePost())
            for (DirectedEdge e : graph.adj(v))
                relax(e);
    }

    private void relax(DirectedEdge e) {
        int v = e.from();
        int w = e.to();
        if (distTo[w] > distTo[v] + e.weight()) {
            distTo[w] = distTo[v] + e.weight();
            edgeTo[w] = e;
        }
    }
}
