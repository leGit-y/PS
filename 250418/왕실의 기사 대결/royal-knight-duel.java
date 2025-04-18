import java.util.*;
import java.io.*;

public class Main {
	static int[][] chess;
	static int[][] chesswithKnight;
	static int L;
	static boolean check;

	static Set<Integer> pushedIdx ;
	static Knight[] knights;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());

		L = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		
		
		// input chess 
		chess = new int[L][L];
		chesswithKnight = new int[L][L];
		for(int i=0;i<L;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<L;j++) {
				chess[i][j] = Integer.parseInt(st.nextToken());
			}
		}

	    knights = new Knight[N+1];
		// input knights
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			
			r--;
			c--;
			Knight knight = new Knight(r, c, h, w, k);
			knights[i+1] = knight;
		
			
			locateKnight(i+1,r,c,h,w);
				
		}
				
		pushedIdx = new LinkedHashSet<>();
		
		// input order
		for(int i=0;i<Q;i++) {
			st = new StringTokenizer(br.readLine());
			int ii = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			
			// 사라진 기사에게 명령 내린 경우
			if(knights[ii].k <= 0) continue;
			
			
			pushedIdx.clear();
			
			check = true;
			willPush(knights[ii],d);
			if(!check) continue;
			
			List<Integer> list = new ArrayList<>(pushedIdx);
			list.sort(new Comparator<Integer>() {

				@Override
				public int compare(Integer o1, Integer o2) {
					// TODO Auto-generated method stub
					// 위
					if(d==0) {
						return knights[o1].r - knights[o2].r;
					}
					
					// 오른
					if(d==1) {
						return knights[o2].c - knights[o1].c;
					}
					
					// 아래
					if(d==2) {
						return knights[o2].r - knights[o1].r;
					}
					
					// 왼
					if(d==3) {
						return knights[o1].c - knights[o2].c;
						
					}
					return 0;
				}
			});

			for(int j=0;j<list.size();j++) {
				int idx = list.get(j);
				
				// move	
				knights[idx].erase();
				knights[idx].move(d);
				knights[idx].mark(idx);
				
				// damage
				knights[idx].damage();
				
			}
			
			// move	(명령 받은 기사는 피해 입지 않음)
			knights[ii].erase();
			knights[ii].move(d);
			knights[ii].mark(ii);
		
		}
		
		int answer = calculate(N);
		
		bw.write(answer+"\n");
		bw.flush();
		
	}
	
	private static void locateKnight(int idx, int r, int c, int h, int w) {
		for(int i=r;i<r+h;i++) {
			for(int j=c;j<c+w;j++) {
				chesswithKnight[i][j] = idx;
			}
		}
		
	}
	
	private static int calculate(int N) {
		int answer = 0;
		
		for(int i=1;i<=N;i++) {
			if(knights[i].k <= 0) continue;
			
			answer += (knights[i].init_k - knights[i].k);
			
		}
		return answer;
	}
	
	private static void willPush(Knight moveKnight, int d) {
		if(!canMove(moveKnight, d)) {
			check = false;
			return;
		}
		
		// 위
		if(d==0) {
			int i = moveKnight.r-1;
			for(int j = moveKnight.c; j < moveKnight.c + moveKnight.w; j++) {
				
				int target = chesswithKnight[i][j];
				if(target != 0) {
					pushedIdx.add(target);
					willPush(knights[target], d);
				}
			}
		}
		
		// 오른
		else if(d==1) {
			int j = moveKnight.c+ moveKnight.w;
			
			for(int i = moveKnight.r; i < moveKnight.r + moveKnight.h; i++) {
				int target = chesswithKnight[i][j];
				if(target != 0) {
					pushedIdx.add(target);
					willPush(knights[target], d);
				}
					
			}
		}
		
		// 아래
		else if(d==2) {
			int i = moveKnight.r+ moveKnight.h;
			
			for(int j = moveKnight.c; j < moveKnight.c + moveKnight.w; j++) {
				int target = chesswithKnight[i][j];
				if(target != 0) {
					pushedIdx.add(target);
					willPush(knights[target], d);
				}
			}
		}
		
		// 왼
		else if(d==3) {
			int j = moveKnight.c-1;
			for(int i = moveKnight.r; i < moveKnight.r + moveKnight.h; i++) {
				int target = chesswithKnight[i][j];
				if(target != 0) {
					pushedIdx.add(target);
					willPush(knights[target], d);
				}
			}
		}
		return;
	}
	
	private static boolean canMove(Knight moveKnight, int d) {
		
		// 위
		if(d==0) {
			int i = moveKnight.r - 1;
			if(i < 0) return false;
			
			for(int j = moveKnight.c; j < moveKnight.c + moveKnight.w; j++) {
				if(chess[i][j] == 2)
					return false;
			}
		}
		
		// 오른
		else if(d==1) {
			int j = moveKnight.c+moveKnight.w;
			if(j >= L) return false;
			
			for(int i = moveKnight.r; i < moveKnight.r + moveKnight.h; i++) {
				if(chess[i][j] == 2)
					return false;
			}
		}
		
		// 아래
		else if(d==2) {
			int i = moveKnight.r + moveKnight.h;
			if(i >= L) return false;
			
			for(int j = moveKnight.c; j < moveKnight.c + moveKnight.w; j++) {
				if(chess[i][j] == 2)
					return false;
			}
		}
		
		// 왼
		else if(d==3) {
			int j = moveKnight.c-1;
			if(j < 0) return false;
			for(int i = moveKnight.r; i < moveKnight.r + moveKnight.h; i++) {
				if(chess[i][j] == 2)
					return false;
			}
		}
		return true;
		
	}
	
	private static class Knight{
		int init_k;
		int r,c;
		int h,w;
		int k;
		
		public Knight(int r, int c, int h, int w, int k) {
			this.init_k = k;
			this.r = r;
			this.c = c;
			this.h = h;
			this.w = w;
			this.k = k;
		}
		
		private void move(int d) {
			// 위
			if(d==0) {
				r--;
			}
			
			// 오른
			else if(d==1) {
				c++;
			}
			
			// 아래
			else if(d==2) {
				r++;
			}
			
			// 왼
			else if(d==3) {
				c--;
			}
			
		}
		
		private void erase() {
			for(int i=r;i<r+h;i++) {
				for(int j=c; j<c+w;j++) {
					chesswithKnight[i][j] = 0;
				}
			}
			
		}
		
		private void mark(int idx) {
			for(int i=r;i<r+h;i++) {
				for(int j=c; j<c+w;j++) {
					chesswithKnight[i][j] = idx;
				}
			}
			
		}
		
		private void damage() {
			int cnt = 0;
			
			for(int i=r;i<r+h;i++) {
				for(int j=c;j<c+w;j++) {
					if(chess[i][j] == 1) {
						cnt++;
					}
				}
			}
			
			k -= cnt;
		}
	}

}
