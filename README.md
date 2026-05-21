Assignment 4: Graph Traversal and Representation System

Name: Sultan Yesmagzam

Group: IT-2502

In this assignment, I implemented a graph representation system using an Adjacency List. My goal was to understand how vertices and edges interact within a non-linear data structure , so I created a Vertex class to represent unique nodes and an Edge class to handle connections

The "backbone" of my implementation is the $adjacencyList$ field in the Graph class, which I defined as a Map<Integer, List<Edge>>. This structure is highly efficient because it allows us to quickly access only the neighbors of a specific vertex rather than scanning a whole matrix. To build the graph, I implemented $addVertex(Vertex v)$ to register new nodes and addEdge to establish connections. In my code, for every edge added from $A$ to $B$, I also added one from $B$ to $A$ to represent an undirected graph, ensuring that the traversal could flow naturally in both directions

1. Algorithm A: Breadth-First Search (BFS)For the first traversal method, I implemented Breadth-First Search (BFS).
Time Complexity: $O(V+E)$.  The logic of BFS is to explore the graph layer by layer. In my bfs(int start) method, the key component is a Queue<Integer>. I used this queue to keep track of the "frontier" of the search. When we visit a vertex, we don't immediately dive deep; instead, we add all its unvisited neighbors to the queue. I also used a Set<Integer> visited to prevent infinite loops. The "heart" of this algorithm is the while(!queue.isEmpty()) loop, which ensures that we process every reachable node. BFS is particularly powerful because it finds the shortest path in terms of the number of edges, as it exhausts all possibilities at distance $1$ before moving to distance $2$.

2. Algorithm B: Depth-First Search (DFS)For the second method, I implemented Depth-First Search (DFS).
Time Complexity: $O(V+E)$.  Unlike BFS, DFS goes as deep as possible along a branch before backtracking. In my implementation, I chose the recursive approach using a helper method dfsHelper(int current, Set<Integer> visited). Here, the "stack" is handled implicitly by the Java call stack. The logic is simple yet deep: as soon as we find a neighbor that hasn't been visited, we immediately call the function again for that neighbor. This continues until we hit a "dead end" (a vertex with no unvisited neighbors), at which point the recursion naturally backtracks. DFS is the ideal choice for tasks like detecting cycles or solving puzzles where you need to explore every possible path to its conclusion. 

Here are the results :

| Graph Size | Traversal Type |  Execution Time (ns) |
| :--- | :--- | :--- | 
| Small(10 Vertices) |  BFS | 1,918,700 | 
| Small(10 Vertices) | DFS | 	347,000 |
| Medium(30 Vertices) |  BFS | 1,061,200 | 
| Medium(30 Vertices) | DFS |1,193,300 |
| Large (100 Vertices)|  BFS |2,440,200 | 
| Large (100 Vertices)| DFS | 1,781,700 |

5. Screenshots:

![Image alt](https://github.com/Sula-The-Light/ADS_4/blob/master/Снимок%20экрана%202026-05-10%20200939.png)

In the end , this project allowed me to see how theoretical complexity $O(V+E)$ behaves in a live environment. On the Small Graph, I noticed that DFS was significantly faster (347k ns vs 1.9M ns). This is likely because the recursive calls for a small set of data have less overhead than initializing a LinkedList for a Queue in BFS. However, as the graph size grew to 100 vertices, the times became more comparable, demonstrating that both algorithms scale linearly with the number of vertices and edges.  

Now about the differnce between theoretical and practical performance

Theoretically, BFS and DFS should have very similar performance since both visit every node and edge once. In my experiments, the fluctuations (like BFS being faster on the 30-vertex graph but slower on the 100-vertex graph) are likely due to how the JVM optimizes code execution during runtime. Another factor is the graph structure: since I used a sequential edge connection ($i \rightarrow i+1$), the DFS recursion depth was linear, which is very efficient for the CPU cache.

Also , i had one of the main challenges duirng process - it was about managing the adjacency list for an undirected graph. 

Initially, I only added the edge in one direction, which caused my BFS and DFS to stop prematurely. I had to ensure that addEdge properly accounted for both the source and destination to make the graph fully traversable. Additionally, using System.nanoTime() taught me that initial runs are often slower due to class loading, which is why the "Small Graph" BFS time actually looks higher than the "Medium Graph" BFS time.


-----------------------------------------------------------------------------------------------------------------------------------------
# BONUS TASK : Dijkstra’s Algorithm (Shortest Path) 

The main goal was to expand the existing data structure to work with edge weights and implement Dijkstra's algorithm, a classic tool for finding shortest paths

Evolution of the data structure :
The first step was to modernize the foundation of the Edge class project. In order for the graph to stop being "uniform" and be able to take into account the cost of moving between nodes, a weight field was added to the edge description. This required a corresponding adjustment of the graph logic: now the addEdge method not only creates a link, but also assigns it a specific weighting factor, while maintaining flexibility for those parts of the program where weight is not required 

Implementation of the search algorithm :

The key task was to implement Dijkstra's algorithm, strictly adhering to the principle of simplicity. Instead of using complex structures such as a priority queue , I used an approach based on the use of regular arrays and basic loops. 

The process of finding the shortest path can be divided into 3 logical stages:

1st one was Preparation. The idea was about initialization of an array of distances, where the initial vertex gets the value "0", and the rest — "infinity", which symbolizes their initial inaccessibility  

The 2nd one is Path selection - at each iteration , the algorithm scans the array , selecting a vertex that has not yet been visited with the minimum accumulated distance

And the last one , Relaxation. For the selected vertex, all its neighbors are checked. If the path through the current vertex turns out to be "cheaper" than the already known one, the value of the shortest distance is updated

Screenshots:

![Image alt](https://github.com/Sula-The-Light/ADS_4/blob/master/Снимок%20экрана%202026-05-21%20215808.png)


![Image alt](https://github.com/Sula-The-Light/ADS_4/blob/master/Снимок%20экрана%202026-05-21%20215821.png)


