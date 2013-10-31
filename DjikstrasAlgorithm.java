///Store the weight in y, and the index in x
public static void FindShortestPath(int[][] weights, ArrayList<Point> points, int startIdx, int endIdx)
{
    PriorityQueue<Point> pq = new PriorityQueue<Point>(points.size(), new Comparator<Point>() {
        public int compare(Point p1, Point p2)
    {
        return p1.y - p2.y;
    }
    });

    int[] distancesFromStart = new int[points.size()];
    boolean[] visited = new boolean[points.size()];

    for(int i = 0; i < points.size(); i++)
    {
        if(i == startIdx)
        {
            distancesFromStart[i] = 0;
        }
        else
        {
            distancesFromStart[i] = Integer.MAX_VALUE;
        }

        visited[i] = false;
    }

    pq.add(new Point(startIdx, 0));

    while(pq.size() > 0)
    {
        Point curPoint = pq.poll();
        for(int i = 0; i < points.size(); i++)
        {
            if(visited[i] || weights[curPoint.x][i] == Integer.MAX_VALUE || i == curPoint.x)
            {
                //We've already visited, or there's no connection, or it's this node
                continue;
            }
            else
            {
                distancesFromStart[i] = (int)Math.min(distancesFromStart[i], weights[curPoint.x][i] + distancesFromStart[curPoint.x]);
                pq.add(new Point(i, distancesFromStart[i]));
            }
        }

        visited[curPoint.x] = true;
    }

    int dist = distancesFromStart[endIdx];
    System.out.println(dist);
}

