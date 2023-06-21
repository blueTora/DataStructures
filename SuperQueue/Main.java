import java.util.*;

public class Main {

    public static class Request{
        public long t;
        public long d;

        public Request(long d, long t){
            this.t = t;
            this.d = d;
        }
    }

    public static class Manager{

        private LinkedList<Request> requests;

        public Manager(){
            requests = new LinkedList<>();
        }

        public void add (long d, long t){
            Request req = new Request(d,t);
            requests.add(req);
        }

        public void remove(long t){
            int i = 0;
            while(t > 0){
                Request req = requests.get(i);
                if ( req.t > t){
                    req.t -= t;
                    t = 0;
                } else {
                    t -= req.t;
                    req.t = 0;
                }
                i++;
            }
        }

        public long getHead(){

            Iterator iter = requests.iterator();
            while (iter.hasNext()){
                Request req = (Request) iter.next();
                if (req.t > 0) {
                    return req.d;
                } else {
                    iter.remove();
                }
            }
            return 0;
        }
    }


    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        String qq = scan.nextLine();
        long q = Long.parseLong(qq.trim());

        Manager manager = new Manager();

        long sub = 0;

        for (long i = 0; i < q ; i++) {
            String request = scan.nextLine();
            String[] s = request.trim().split(" ");
            switch (s[0]){
                case "+":
                    long d = Long.parseLong(s[1]);
                    long t = Long.parseLong(s[2]);
                    manager.add(d,t);
                    break;
                case "-":
                    long tt = Long.parseLong(s[1]);
                    sub += tt;
                    break;
                case "?":
                    manager.remove(sub);
                    sub = 0;

                    long head = manager.getHead();
                    if (head == 0)
                        System.out.println("empty");
                    else
                        System.out.println(head);
                    break;
            }
        }
    }
}
