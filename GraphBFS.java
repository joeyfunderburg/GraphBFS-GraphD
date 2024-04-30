import java.util.*;

// class to represent a graph + breadth first search
class GraphBFS {
  int vertices;
  int[][] adjMatrix;

  // constructor
  public GraphBFS(int vertices) {
    this.vertices = vertices;
    adjMatrix = new int[vertices][vertices];
  }

  // adds edge between two vertices
  public void addEdge(int i, int j) {
    adjMatrix[i][j] = 1;
    // for undirected graph
    adjMatrix[j][i] = 1;
  }

  // BFS algorithm
  public List<Integer> bfs(int source) {
    List<Integer> list = new ArrayList<>();
    Queue<Integer> queue = new LinkedList<>();
    boolean[] isVisited = new boolean[vertices];

    // mark, add, and enqueue source node
    isVisited[source] = true;
    list.add(source);
    queue.add(source);

    // process node in queue until empty
    while (!queue.isEmpty()) {
      int current = queue.poll();
      // iterate through neighbors of the current node
      for (int neighbor = 0; neighbor < vertices; neighbor++) {
        if (adjMatrix[current][neighbor] == 1 && !isVisited[neighbor]) {
          isVisited[neighbor] = true;
          list.add(neighbor);
          queue.add(neighbor);
        }
      }
    }
    // return the list containing the BFS traversal order
    return list;
  }

  // main
  public static void main(String[] args) {
    // creating a graph and adding edges
    GraphBFS graph = new GraphBFS(5);
    graph.addEdge(0, 1);
    graph.addEdge(0, 2);
    graph.addEdge(1, 3);
    graph.addEdge(2, 4);

    // performing BFS traversal from source node 0
    List<Integer> bfsTraversal = graph.bfs(0);
    System.out.println("BFS Traversal from source node 0: " + bfsTraversal);
  }
}

// represents a node in the graph
class Node {
  String value;
  List<Node> adjacents;

  // constructor to create a node with a given value
  public Node(String value) {
    this.value = value;
    this.adjacents = new ArrayList<>();
  }

  // adds an edge from this node to another
  public void addEdge(Node node) {
    adjacents.add(node);
  }
}
