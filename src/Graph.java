import java.util.*;
public class Graph {
    private Map<Integer, Vertex> vertices;
    private Map<Integer, List<Edge>> adjacencyList;
    public Graph() {
        vertices = new HashMap<>();
        adjacencyList = new HashMap<>();
    }
    public void addVertex(Vertex v) {
        vertices.putIfAbsent(v.getId(), v);
        adjacencyList.putIfAbsent(v.getId(), new ArrayList<>());
    }
    public void addEdge(int from, int to) {
        adjacencyList.get(from).add(new Edge(from, to));
        adjacencyList.get(to).add(new Edge(to, from));
    }
    public void printGraph() {
        for (Integer vertexId : adjacencyList.keySet()) {
            System.out.print("Vertex " + vertexId + " is connected to: ");
            for (Edge edge : adjacencyList.get(vertexId)) {
                System.out.print(edge.getDestination() + " ");
            }
            System.out.println();
        }
    }
    public void bfs(int start) {
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        visited.add(start);
        queue.add(start);
        System.out.print("BFS Traversal: ");
        while (!queue.isEmpty()) {
            int current = queue.poll();
            System.out.print(current + " ");
            for (Edge edge : adjacencyList.getOrDefault(current, new ArrayList<>())) {
                int neighbor = edge.getDestination();
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
        System.out.println();
    }
    public void dfs(int start) {
        Set<Integer> visited = new HashSet<>();
        System.out.print("DFS Traversal: ");
        dfsHelper(start, visited);
        System.out.println();
    }
    private void dfsHelper(int current, Set<Integer> visited) {
        visited.add(current);
        System.out.print(current + " ");
        for (Edge edge : adjacencyList.getOrDefault(current, new ArrayList<>())) {
            int neighbor = edge.getDestination();
            if (!visited.contains(neighbor)) {
                dfsHelper(neighbor, visited);
            }
        }
    }
}