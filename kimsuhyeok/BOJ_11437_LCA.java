import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 세그먼트 트리
 * 연속된 구간의 데이터의 합을 가장 빠르고 간단하게 구할 수 있는 트리
 * 루트 노드에 모든 원소를 더한 값이 들어가는 방식
 */
public class Main {
    
    static List<Integer>[] list;
    static int[] parent, depth;
    static int N,M;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine());
        
        parent = new int[N+1];
        depth = new int[N+1];
        list = new ArrayList[N+1];
        for(int i=1;i<=N;i++){
            list[i] = new ArrayList<>();
        }
        
        for(int i=0;i<N-1;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            list[a].add(b);
            list[b].add(a);
        }
        
        init(1,1,0);
        
        M = Integer.parseInt(br.readLine());
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            System.out.println(LCA(a,b));
        }
        
    }
    
    public static void init(int cur, int h, int pa){
        depth[cur] = h;
        parent[cur] = pa;
        for(int next: list[cur]){
            if(next != pa){
                init(next, h+1, cur);
            }
        }
    }
    
    public static int LCA(int a, int b){
        int ah = depth[a];
        int bh = depth[b];
        
        while(ah > bh){
            a = parent[a];
            ah--;
        }
        
        while(bh>ah){
            b = parent[b];
            bh--;
        }
        
        while(a!=b){
            a = parent[a];
            b = parent[b];
        }
        return a;
    }

}
