package data_structures.graph;


public class Test {

    @org.junit.Test
    public void testGraph() {
        int n = 5;
        Graph<Character> graph = new Graph<>(n);
        char c = 'A';
        for (int i = 0; i < n; i++) {
            graph.setVertexData(i, c);
            c++;
        }

//        graph.showGraph();

        graph.addEdge(1, 4, 5, false);
        graph.addEdge(1, 0, 2, true);
        graph.showGraph();

    }
}
