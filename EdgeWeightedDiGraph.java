import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class EdgeWeightedDiGraph {
    private final int vertices;
    private final List<List <DirectedEdge>> adj;

    public EdgeWeightedDiGraph(int vertices) {
        this.vertices = vertices;
        this.adj = new ArrayList <>();
        for (int v = 0; v < vertices; v++)
            this.adj.add(new LinkedList <>());
    }

    public int getVertices() {
        return this.vertices;
    }

    public void addEdge(DirectedEdge e) {
        int v = e.from();
        this.adj.get(v).add(e);
    }

    public Iterable<DirectedEdge> adj(int v) {
        return this.adj.get(v);
    }
}
