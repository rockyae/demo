package com.example.demo.algo;

import java.util.*;

public class MinStep {

    private static int m,n;

    private static int[][] board;

    private static List<int[]> horses = new LinkedList<>();

    /*
    输入m和n两个数，m和n表示一个m*n的棋盘。输入棋盘内的数据。棋盘中存在数字和”.”两种字符，如果是数字表示该位置是一匹马，如果是“.“表示该位置为空的，棋盘內的数字表示为该马能走的最大步数。
    例如棋盘内某个位置一个数字为k，表示该马只能移动 1~k步的距离。
棋盘内的马移动类似于中国象棋中的马移动，先在水平或者垂直方向上移动格心然后再将其移动到对角线位置。
棋盘内的马可以移动到同一个位置，同一个位置可以有多匹马。
请问能否将棋盘上所有的马移动到同一个位置，若可以请输入移动的最小步数。若不可以输出-1
输入描述
输入m和n两个数，m 和n表示一个m*n的棋盘。输入棋盘内的数据。
输出描述
能否将棋盘上所有的马移动到同一个位置，若可以请输入移动的最小步数。若不可以输出-1;
30min  43min  1h20h
2 3
21:55      22:38 23.15
     */
    //计算所有马移动到目标位置所需的最小步数
    //枚举每个位置，判断马是否可达
    public static void main(String[] args) {
        //读取输入m，n
        Scanner sc = new Scanner(System.in);
        String[] arr = sc.nextLine().split(" ");
         m = Integer.parseInt(arr[0]);
         n = Integer.parseInt(arr[1]);
         board = new int[m][n];


        //读入棋盘
        for(int i=0; i<m; i++) {
            String[] line = sc.nextLine().split("");// 2 . .
            for (int j = 0; j < n; j++) {
                if(!line[j].equals(".")){
                    //马能走的步数
                    board[i][j] = Integer.parseInt(line[j]);
                    //初始化马的位置及步数
                    horses.add(new int[]{i,j,board[i][j]});
                }
            }
        }
        System.out.println(bfs());
    }
    /**
     * 对每匹马，将当前位置入队，标记已访问，将基于当前位置的其他未访问位置入队，队列不空，出队首标记访问，step+1;
     对位置，假设马都可达，如果搜索完存在不可达，标记为不可达,存在可达位置跳出循环枚举下一个。
     位置：possible, found
     **/
    private static int bfs(){

        int minSteps = Integer.MAX_VALUE;
        //马一步的走法
        int[][] directions = new int[][]{ {-1,-2}, {-2,-1}, {-2,1}, {-1,2}, {1,-2}, {2,-1}, {2,1}, {1,2} };
        for(int i=0; i<m;i++){
            for(int j=0;j<n;j++){
                boolean possible = true;
                //每个位置的步数累计
                int steps = 0;
                for(int[] horseInfo : horses){
                    //存储访问的位置信息及使用的步数
                    Queue<int[]> queue = new ArrayDeque<>();
                    //记录单马已经访问的位置
                    Set<String> set = new HashSet<>();

                    queue.add(new int[]{horseInfo[0], horseInfo[1], 0});
                    set.add(horseInfo[0]+","+horseInfo[1]);
                    boolean found = false;
                    //possible剪枝
                    while(!queue.isEmpty() && possible){
                        int[] current = queue.poll();
                        int x = current[0];
                        int y = current[1];
                        //判断当前位置是否是目标位置
                        if(x==i&&y==j){
                            found = true;
                            //累加每匹马的已使用步数
                            steps += current[2];
                            break;
                        }

                        //基于当前位置计算下一个位置
                        for(int[] direction : directions){
                            int nx = x + direction[0];
                            int ny = y + direction[1];
                            //步数没用完
                            if(nx >= 0 && nx < m && ny >=0 && ny < n && (current[2] < horseInfo[2]) &&(!set.contains(nx+","+ny))){
                                queue.add(new int[]{nx, ny, current[2]+1});
                                set.add(nx+","+ny);
                            }
                        }
                    }
                    //存在不可达的马，这个位置就不可达
                    if(!found){
                        possible = false;
                    }
                }
                if(possible){
                    minSteps = Math.min(minSteps, steps);
                }
            }
        }
        return minSteps == Integer.MAX_VALUE ? -1 : minSteps;

    }

}
