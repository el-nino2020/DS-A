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

    @org.junit.Test
    public void testDFS() {
        int n = 5;
        Graph<Character> graph = new Graph<>(n);
        char c = 'A';
        for (int i = 0; i < n; i++) {
            graph.setVertexData(i, c);
            c++;
        }
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);

        /*
        相当于A-B、B-C、A-C、B-D、B-E
        可以用这个网站看图：https://csacademy.com/app/graph_editor/
         */
        graph.showGraph();


        System.out.println("DFS的结果为：");
        /*
            从A开始执行DFS，结果应该为A B C D E
         */
        System.out.println(Graph.DFS(graph,0));

    }

    @org.junit.Test
    public void testBFS() {
        int n = 7;
        Graph<Character> graph = new Graph<>(n);
        char c = 'A';
        for (int i = 0; i < n; i++) {
            graph.setVertexData(i, c);
            c++;
        }
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(0, 3);
        graph.addEdge(1, 4);
        graph.addEdge(2, 5);
        graph.addEdge(3, 6);

        /*
        相当于A-B、A-C、A-D、B-E、C-F、D-G
        可以用这个网站看图：https://csacademy.com/app/graph_editor/
         */
        graph.showGraph();


        System.out.println("BFS的结果为：");
        /*
            从A开始执行DFS，结果应该为A B C D E F G
         */
        System.out.println(Graph.BFS(graph,0));

    }
}
