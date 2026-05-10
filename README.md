Assignment 4: Graph Traversal and Representation System

Name: Sultan Yesmagzam

Group: IT-2502

In this assignment, I implemented a graph representation system using an Adjacency List. The primary goal was to understand how vertices and edges interact within a non-linear data structure. I created a Vertex class to represent unique nodes and an Edge class to handle connections

The "backbone" of my implementation is the $adjacencyList$ field in the Graph class, which I defined as a Map<Integer, List<Edge>>. This structure is highly efficient because it allows us to quickly access only the neighbors of a specific vertex rather than scanning a whole matrix. To build the graph, I implemented $addVertex(Vertex v)$ to register new nodes and addEdge to establish connections. In my code, for every edge added from $A$ to $B$, I also added one from $B$ to $A$ to represent an undirected graph, ensuring that the traversal could flow naturally in both directions

Algorithm A: Breadth-First Search (BFS)For the first traversal method, I implemented Breadth-First Search (BFS).
Time Complexity: $O(V+E)$.  The logic of BFS is to explore the graph layer by layer. In my bfs(int start) method, the key component is a Queue<Integer>. I used this queue to keep track of the "frontier" of the search. When we visit a vertex, we don't immediately dive deep; instead, we add all its unvisited neighbors to the queue. I also used a Set<Integer> visited to prevent infinite loops. The "heart" of this algorithm is the while(!queue.isEmpty()) loop, which ensures that we process every reachable node. BFS is particularly powerful because it finds the shortest path in terms of the number of edges, as it exhausts all possibilities at distance $1$ before moving to distance $2$.

Algorithm B: Depth-First Search (DFS)For the second method, I implemented Depth-First Search (DFS).
Time Complexity: $O(V+E)$.  Unlike BFS, DFS goes as deep as possible along a branch before backtracking. In my implementation, I chose the recursive approach using a helper method dfsHelper(int current, Set<Integer> visited). Here, the "stack" is handled implicitly by the Java call stack. The logic is simple yet deep: as soon as we find a neighbor that hasn't been visited, we immediately call the function again for that neighbor. This continues until we hit a "dead end" (a vertex with no unvisited neighbors), at which point the recursion naturally backtracks. DFS is the ideal choice for tasks like detecting cycles or solving puzzles where you need to explore every possible path to its conclusion. 

Graph Size	    Traversal Type	    Execution Time (ns)

Small           (10 Vertices)	       BFS	1,918,700

Small            (10 Vertices)	DFS	347,000
Medium (30 Vertices)	BFS	1,061,200
Medium (30 Vertices)	DFS	1,193,300
Large (100 Vertices)	BFS	2,440,200
Large (100 Vertices)	DFS	1,781,700
