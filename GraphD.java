import java.util.*;

// Represents a node in the graph
class NodeD {
  String value; // Value held by the node
  List<Edge> adjacents; // List of adjacent nodes (edges)
  boolean visited; // Flag to track if the node has been visited during Dijkstra's algorithm
  int distance; // Distance from the start node
  NodeD previous; // Previous node on the shortest path from the start node

  // Constructor to create a node with a given value
  public NodeD(String value) {
    this.value = value;
    this.adjacents = new ArrayList<>();
    this.visited = false;
    this.distance = Integer.MAX_VALUE;
    this.previous = null;
  }

  // Adds an edge from this node to another with a given weight
  public void addEdge(NodeD node, int weight) {
    adjacents.add(new Edge(node, weight));
  }
}

// Represents an edge between two nodes with a weight
class Edge {
  NodeD destination;
  int weight;

  public Edge(NodeD destination, int weight) {
    this.destination = destination;
    this.weight = weight;
  }
}

public class GraphD {
  // Implementing Dijkstra's algorithm
  public static void dijkstra(NodeD start) {
    PriorityQueue<NodeD> queue = new PriorityQueue<>(Comparator.comparingInt(node -> node.distance));
    start.distance = 0;
    queue.add(start);

    while (!queue.isEmpty()) {
      NodeD current = queue.poll();
      current.visited = true;

      for (Edge edge : current.adjacents) {
        NodeD neighbor = edge.destination;
        int newDistance = current.distance + edge.weight;
        if (!neighbor.visited && newDistance < neighbor.distance) {
          neighbor.distance = newDistance;
          neighbor.previous = current;
          queue.add(neighbor);
        }
      }
    }
  }

  // Print shortest path and total cost from start node to target node
  public static void printShortestPath(NodeD target) {
    if (target.distance == Integer.MAX_VALUE) {
      System.out.println("No path to reach the target node.");
      return;
    }

    System.out.print("Shortest Path: ");
    Stack<String> stack = new Stack<>();
    int totalCost = target.distance; // Store the distance before traversing
    while (target != null) {
      stack.push(target.value);
      target = target.previous;
    }
    while (!stack.isEmpty()) {
      System.out.print(stack.pop());
      if (!stack.isEmpty()) {
        System.out.print(" -> ");
      }
    }
    System.out.println("\nTotal Cost: " + totalCost);
  }

  public static void main(String[] args) {
    // Creating nodes
    NodeD A = new NodeD("A");
    NodeD B = new NodeD("B");
    NodeD C = new NodeD("C");
    NodeD D = new NodeD("D");
    NodeD E = new NodeD("E");

    // Adding weighted edges
    A.addEdge(B, 4);
    A.addEdge(C, 2);
    B.addEdge(D, 5);
    C.addEdge(D, 1);
    C.addEdge(E, 3);
    D.addEdge(E, 7);

    // Running Dijkstra's algorithm
    dijkstra(A);

    // Testing shortest path
    NodeD target = E;
    printShortestPath(target); // Output: Shortest Path: A -> C -> E / Total Cost: 5

    target = D;
    printShortestPath(target); // Output: Shortest Path: A -> C -> D / Total Cost: 3
  }
}
