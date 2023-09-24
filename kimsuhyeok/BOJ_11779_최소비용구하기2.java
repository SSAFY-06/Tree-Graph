import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 도시의 개수 : N
 * 버스의 개수 : M
 *
 *
 */
public class Main {

    static class Node implements Comparable<Node>{
        int to;
        int weight;
        public Node(int to, int weight){
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight-o.weight;
        }
    }
    static int N,M;
    static int[] dist; //거리 배열
    static int[] prev;  //직전 도시 저장할 배열
    static boolean[] visited;
    static List<Node>[] list;
    static int start, end;
    static final int INF = 1000000001;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        list = new ArrayList[N+1];
        for(int i=1;i<=N;i++){
            list[i] = new ArrayList<>();
        }

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            list[from].add(new Node(to, weight));
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        dist = new int[N+1];
        prev = new int[N+1];
        visited = new boolean[N+1];
        Arrays.fill(dist, INF);

        dijkstra();

        System.out.println(dist[end]);

        List<Integer> routes = new ArrayList<>();

        //역추적
        int trace = end;

        while(trace!=0){
            routes.add(trace);
            trace = prev[trace];
        }

        System.out.println(routes.size());

        for(int i= routes.size()-1;i>=0;i--){
            System.out.print(routes.get(i)+" ");
        }

    }

    public static void dijkstra(){
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.offer(new Node(start,0));
        dist[start] = 0;
        prev[start] = 0;

        while(!queue.isEmpty()){
            Node cur = queue.poll();

            if(!visited[cur.to]){
                visited[cur.to] = true;
            }
            else continue;

            for(int i=0;i<list[cur.to].size();i++){
                Node next = list[cur.to].get(i);
                if(dist[next.to]>dist[cur.to]+next.weight){
                    dist[next.to] = dist[cur.to]+next.weight;
                    queue.offer(new Node(next.to, dist[next.to]));
                    prev[next.to] = cur.to;
                }
            }
        }
    }

}
