import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/** 나이트의 이동
 * 
 * 체스판 위에 나이트가 놓여져 있고, 나이트가 한번에 이동할 수 있는 칸이 주어져있다,
 * 나이트가 이동하려고 하는 칸이 주어질때 몇번 움직이면 해당칸에 도착할지 계산해라.
 * 
 * 입력:
 * 맨첫줄 : 테케의 갯수
 * 첫줄에 체스판 한변의 길이 l이 주어진다. 체스판은 lxl 크기이다.
 * 체스판의 각 칸은 두수의 쌍{0,...,l-1}{0,...,l-1}으로 나타낼 수 있다.
 * 둘째줄과 셋째줄에는 나이트가 현재 있는 칸, 나이트가 이동하려고 하는 칸이 주어진다.
 * 
 * 출력:
 * 각 테케마다 나이트가 최소 몇번만에 이동할 수 있는지 출력
 *
 * 풀이:
 * 갈수있는 방향이 주어지고, 최소 몇번움직이면 도착할 수 있을지 찾고있으므로 bfs를 통해
 * 경로탐색하면 될듯(레벨별 탐색을 통해 거리를 저장하고!)
 */

public class BOJ_7562 {
	static int[] dx = {-2,-2,-1,-1,1,1,2,2};
	static int[] dy = {-1,1,-2,2,-2,2,-1,1};
	static boolean[][] map;
	static int[] end = new int[2];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for(int t=0; t<tc; t++) {
			//체스판 한변의 길이 l
			int l = Integer.parseInt(br.readLine());
			map = new boolean[l][l];

			//나이트가 현재 있는 칸
			st = new StringTokenizer(br.readLine());
			int startX = Integer.parseInt(st.nextToken());
			int startY = Integer.parseInt(st.nextToken());
			map[startX][startY] = true;

			//나이트가 이동하려고 하는 칸
			st = new StringTokenizer(br.readLine());
			end[0] = Integer.parseInt(st.nextToken());
			end[1] = Integer.parseInt(st.nextToken());

			//bfs
			sb.append(bfs(startX,startY)).append('\n');
		}
		System.out.println(sb);
	}

	// end[]에 도착하는 최단거리를 구하는 메소드
	private static int bfs(int x, int y) {
		int dis = 0;
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {x,y});

		//레벨탐색하여 몇번째 레벨에서 값을 찾는지 저장(=거리가 된다)
		while(!q.isEmpty()) {
			int size = q.size(); //이거 안하면 큐 사이즈가 바뀌어버려서 또 돌아버림
			for(int k=0; k<size; k++) {
				int[] arr = q.poll();
				//도착점 도착했을때 종료
				if(arr[0] == end[0] && arr[1] == end[1]) return dis;
				for(int i=0; i<8; i++) {
					int nx = arr[0]+dx[i];
					int ny = arr[1]+dy[i];
					if(nx<0||ny<0||nx>=map.length||ny>=map.length||map[nx][ny]) continue;
					//방문한적 없는 노드라면 방문후 큐에 넣어서 이어서 경로탐색함
					map[nx][ny] = true;
					q.offer(new int[] {nx,ny});
				}
			}
			dis++;
		}

		return 0;
	}
}
