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
    public void addEdge(int from, int to) { // An old method without weight (let it be 1 by default)
        addEdge(from, to, 1);
    }
    public void addEdge(int from, int to, int weight) { // A new method for supporting weighted edges
        adjacencyList.get(from).add(new Edge(from, to, weight));
        adjacencyList.get(to).add(new Edge(to, from, weight));
    }
    public void printGraph() {
        for (Integer vertexId : adjacencyList.keySet()) {
            System.out.print("Vertex " + vertexId + " is connected to: ");
            for (Edge edge : adjacencyList.get(vertexId)) {
                System.out.print(edge.getDestination() + "(w:" + edge.getWeight() + ") ");
            }
            System.out.println();
        }
    }
    public void dijkstra(int start) {
        if (!vertices.containsKey(start)) {
            System.out.println("Starting vertex " + start + " not found.");
            return;
        }
        // Since the vertices are represented by ID, we'll determine the max size of the array
        int maxId = 0;
        for (int id : vertices.keySet()) {
            if (id > maxId) maxId = id;
        }
        int numVertices = maxId + 1;
        int[] dist = new int[numVertices]; // Arrays for distances and visited vertices
        boolean[] visited = new boolean[numVertices];
        Arrays.fill(dist, Integer.MAX_VALUE); // Initialization
        dist[start] = 0;
        for (int i = 0; i < vertices.size(); i++) {
            int u = -1; // We find the vertex with the minimum distance from those not yet visited
            int minDist = Integer.MAX_VALUE;
            for (int v : vertices.keySet()) {
                if (!visited[v] && dist[v] < minDist) {
                    minDist = dist[v];
                    u = v;
                }
            }
            if (u == -1)
                break; // If we haven't found any available vertices - we leave it
            visited[u] = true;
            for (Edge edge : adjacencyList.getOrDefault(u, new ArrayList<>())) { // Edge relaxation for the neighbors of the found vertex
                int v = edge.getDestination();
                int weight = edge.getWeight();
                if (!visited[v] && dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                }
            }
        }
        System.out.println("Dijkstra Shortest Paths from Vertex " + start + ":"); // Output of results
        for (int id : vertices.keySet()) {
            String distanceStr = (dist[id] == Integer.MAX_VALUE) ? "Unreachable" : String.valueOf(dist[id]);
            System.out.println("  To Vertex " + id + " -> Distance: " + distanceStr);
        }
        System.out.println();
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