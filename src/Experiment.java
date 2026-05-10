public class Experiment {
    public void runTraversals(Graph g, int startVertex, boolean isSmall) {
        long startBFS = System.nanoTime();
        g.bfs(startVertex);
        long endBFS = System.nanoTime();
        long startDFS = System.nanoTime();
        g.dfs(startVertex);
        long endDFS = System.nanoTime();
        System.out.println("BFS Execution Time: " + (endBFS - startBFS) + " ns");
        System.out.println("DFS Execution Time: " + (endDFS - startDFS) + " ns\n");
    }
    public void runMultipleTests() {
        System.out.println("--- Small Graph (10 vertices) ---");
        Graph smallGraph = generateGraph(10);
        smallGraph.printGraph();
        runTraversals(smallGraph, 0, true);

        System.out.println("--- Medium Graph (30 vertices) ---");
        Graph mediumGraph = generateGraph(30);
        runTraversals(mediumGraph, 0, false);

        System.out.println("--- Large Graph (100 vertices) ---");
        Graph largeGraph = generateGraph(100);
        runTraversals(largeGraph, 0, false);
    }
    private Graph generateGraph(int size) {
        Graph g = new Graph();
        for (int i=0; i<size; i++) {
            g.addVertex(new Vertex(i));
        }
        for (int i=0; i<size - 1; i++) {
            g.addEdge(i, i + 1);
            if (i+2<size) {
                g.addEdge(i, i+2);
            }
        }
        return g;
    }
}