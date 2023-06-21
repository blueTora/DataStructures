import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static ArrayList<Node> nodes;
    private static int iter;

    public static class Node{

        public int leftChildIndex;
        public int rightChildIndex;
        public int key;
        public int parentIndex;

        public Node(){
            this.key = -1 ;
            this.parentIndex = -1 ;
            this.leftChildIndex = -1 ;
            this.rightChildIndex = -1 ;
        }
    }

    public static int findRoot(int n){
        int i;
        for (i = 0 ; i < n ; i++) {
            if (nodes.get(i).parentIndex == -1)
                return i;
        }
        return i;
    }

    public static void walkBack(int index){
        Node node = nodes.get(index);

        if (node.leftChildIndex != -1){
            walkBack(node.leftChildIndex);
        }

        node.key = iter++;

        if (node.rightChildIndex != -1){
            walkBack(node.rightChildIndex);
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = Integer.parseInt(scan.nextLine());

        nodes = new ArrayList<>();
        iter = 1;

        for (int i = 0; i < n; i++) {
            nodes.add(new Node());
        }

        for (int i = 0; i < n; i++) {
            String[] temp = scan.nextLine().trim().split(" ");

            int index = Integer.parseInt(temp[0])-1;
            int left = Integer.parseInt(temp[1])-1;
            int right = Integer.parseInt(temp[2])-1;

            Node node = nodes.get(index);

            if (right != -2){
                nodes.get(right).parentIndex = index;
                node.rightChildIndex = right;
            }
            if (left != -2){
                nodes.get(left).parentIndex = index;
                node.leftChildIndex = left;
            }
        }

        walkBack( findRoot(n) );

        for (int i = 0; i < n; i++) {
            System.out.print(nodes.get(i).key + " ");
        }
    }
}
