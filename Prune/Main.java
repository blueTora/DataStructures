import java.util.*;

class Main {
    private int V;
    private LinkedList<Integer> adj[];
    private static int[][] mane;
    private static HashMap<Integer , HashMap<Integer, Boolean>> markedMane;
    private static int maneIndex = 0;

    Main(int v) {
        V = v;

        adj = new LinkedList[v];
        for (int i=0; i<v; ++i)
            adj[i] = new LinkedList();
    }

    void addEdge(int v,int w) {
        adj[v].add(w);
        adj[w].add(v);

        if(v<w){
            markedMane.get(v).putIfAbsent(w, false);
        } else {
            markedMane.get(w).putIfAbsent(v, false);
        }
    }

    void BFS(int s) {
        boolean visited[] = new boolean[V];

        LinkedList<Integer> queue = new LinkedList<>();

        visited[s]=true;
        queue.add(s);

        while (queue.size() != 0) {
            s = queue.poll();

            Iterator<Integer> i = adj[s].listIterator();
            while (i.hasNext()) {

                int n = i.next();
                if (!visited[n]) {
                    visited[n] = true;

                    if(s<n){
                        markedMane.get(s).put(n, true);
                    } else {
                        markedMane.get(n).put(s, true);
                    }
                    maneIndex++;

                    queue.add(n);
                }
            }
        }
    }

    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int m = scan.nextInt();

        mane = new int[m][2];
        markedMane = new HashMap<>();

        Main g = new Main(n);

        for (int i = 0; i < m; i++) {
            mane[i][0] = scan.nextInt();
            mane[i][1] = scan.nextInt();

            markedMane.putIfAbsent(mane[i][0]-1, new HashMap<>());
            markedMane.putIfAbsent(mane[i][1]-1, new HashMap<>());

            g.addEdge(mane[i][0]-1, mane[i][1]-1);
        }

        g.BFS(0);
        System.out.println(maneIndex);

        for(int i = 0 ; i<m ; i++){
            int x = mane[i][0];
            int y = mane[i][1];
            int xx,yy;

            if(x<y){
                xx = x-1;
                yy = y-1;
            } else {
                xx = y-1;
                yy = x-1;
            }

            if (markedMane.get(xx).get(yy)){
                System.out.print((i+1) + " ");
            }
        }
    }
}