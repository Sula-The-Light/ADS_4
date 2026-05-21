public class Edge {
    private int source;
    private int destination;
    private int weight; // Added edge weight
    public Edge(int source, int destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }
    public int getSource() {
        return source;
    }
    public int getDestination() {
        return destination;
    }
    public int getWeight() { // Getter for weight
        return weight;
    }
    @Override
    public String toString() {
        return "Edge{" +
                "source=" + source +
                ", destination=" + destination +
                ", weight=" + weight +
                '}';
    }
}