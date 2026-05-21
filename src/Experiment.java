import java.util.Random;
public class Experiment {
    // now this is the generation method now creates random weights for the edges
    private Graph generateGraph(int size) {
        Graph g = new Graph();
        Random rand = new Random(); // it is used to generate random weights from 1 to 10
        for (int i=0; i<size; i++) {
            g.addVertex(new Vertex(i));
        }
        for (int i=0; i<size - 1; i++) {
            g.addEdge(i, i + 1, rand.nextInt(10) + 1);
            if (i+2<size) {
                g.addEdge(i, i + 2, rand.nextInt(10) + 1);
            }
        }
        return g;
    }
    public void runMultipleTests() {
        System.out.println("Small Graph (10 vertices)");
        Graph smallGraph = generateGraph(10);
        smallGraph.printGraph();

        // Testing Dijkstra from TK
        smallGraph.dijkstra(0);

        System.out.println("Medium Graph (30 vertices)");
        Graph mediumGraph = generateGraph(30);
        mediumGraph.dijkstra(0);
    }
}