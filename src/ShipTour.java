import java.util.*;

public class ShipTour {
    int islands;
    int edjes;
    public ArrayList<Island> adjantancy[];

    public int getIslands() {
        return islands;
    }

    public void setIslands(int islands) {
        this.islands = islands;
    }

    public int getEdjes() {
        return edjes;
    }

    public void setEdjes(int edjes) {
        this.edjes = edjes;
    }

    public ArrayList<Island>[] getAdjantancy() {
        return adjantancy;
    }

    public void setAdjantancy(ArrayList<Island>[] adjantancy) {
        this.adjantancy = adjantancy;
    }

    public ShipTour(int islands){
        this.islands=islands;
        this.adjantancy = new ArrayList[islands+1];
        for (int i = 1; i <= islands; i++) {
            this.adjantancy[i] = new ArrayList<>();
        }
    }
    public void addedge(Island a, Island b) {

        adjantancy[a.getIdentity()].add(b);
        adjantancy[b.getIdentity()].add(a);
    }
    public boolean are_they_adjanceny (Island a,Island b){
        for (Island c:adjantancy[a.identity]){
            if(c.identity==b.identity){
                return true;
            }
        }
        return false;
    }
    public void shortestcycle(Island a,Island b){

        PriorityQueue<Island> islandPriorityQueue = new PriorityQueue<>();
        int []distances = new int[islands+1];
        int []parents = new int[islands+1];
        for (int i =0;i<parents.length;i++){
            distances[i]= Integer.MAX_VALUE;
            parents[i]=i;
        }
        HashMap<Integer,Boolean> storevalues = new HashMap<>();
        for(int i =0;i<islands+1;i++){
            storevalues.put(i,true);
        }
        islandPriorityQueue.add(new Island(0,a.identity));
        distances[a.identity]=0;
        while (!islandPriorityQueue.isEmpty()){
            int tempdistance = islandPriorityQueue.peek().distance;
            int tempidenityt = islandPriorityQueue.peek().identity;

            islandPriorityQueue.remove();
            for(Island c : adjantancy[tempidenityt]){
                int identity = c.getIdentity();
                int distance = c.getDistance();
                if(distance+tempdistance<distances[identity]){
                    distances[identity]= distance+tempdistance;
                    parents[identity]= tempidenityt;
                    islandPriorityQueue.add(new Island(distances[identity],identity));
                }
            }
        }
        Stack<Integer> pathstack = new Stack<>();
        int last = b.getIdentity();
        while (parents[last] != last) {
            pathstack.push(last);
            last = parents[last];
        }
        pathstack.add(a.getIdentity());
        while (!pathstack.isEmpty()) {
            int temp = pathstack.peek();
            storevalues.put(temp,false);
            pathstack.pop();
        }
        storevalues.put(a.identity,true);
        storevalues.put(b.identity,true);
        boolean connection = are_they_adjanceny(a,b);
        if(connection){
            deleteedge(a,b);
        }
        islandPriorityQueue.add(new Island(0,b.getIdentity()));
        int returndistances []= new int[islands+1];
        int parentsforreturn [] =new int[islands+1];
        for (int i =0;i<islands+1;i++){
            returndistances[i]= Integer.MAX_VALUE;
            parentsforreturn[i]=i;
        }
        returndistances[b.identity]=0;
        while (!islandPriorityQueue.isEmpty()){
            int tempdistance = islandPriorityQueue.peek().distance;
            int tempidenityt = islandPriorityQueue.peek().identity;
            boolean avaliable = storevalues.get(islandPriorityQueue.peek().identity);

            islandPriorityQueue.remove();
            for(Island c : adjantancy[tempidenityt]){
                int identity = c.getIdentity();
                int distance = c.getDistance();
                if(distance+tempdistance<returndistances[identity]&&avaliable){
                    returndistances[identity]= distance+tempdistance;
                    parentsforreturn[identity]= tempidenityt;
                    islandPriorityQueue.add(new Island(returndistances[identity],identity));
                }
            }
        }
        Stack<Integer> returnpath = new Stack<>();
        int first = a.getIdentity();
        while (parentsforreturn[first] != first) {
            returnpath.push(first);
            first = parentsforreturn[first];
        }
        returnpath.add(b.getIdentity());

        while (!returnpath.isEmpty()) {
            int temp = returnpath.peek();
            storevalues.put(temp,false);

            returnpath.pop();
        }
        storevalues.put(a.identity,false);
        storevalues.put(b.identity,false);
int count =1;
while (count<=islands){
    if (!storevalues.get(count)){
        System.out.print(count +" ");
    }
    count+=1;
}



        }
    public    void deleteedge(Island u, Island v)
    {

        for (int i = 0; i < this.adjantancy[u.getIdentity()].size(); i++)
        {
            if (adjantancy[u.getIdentity()].get(i).identity == v.identity)
            {
                adjantancy[u.getIdentity()].remove(i);
                break;
            }
        }


        for (int i = 0; i < adjantancy[v.getIdentity()].size(); i++)
        {
            if (adjantancy[v.getIdentity()].get(i).identity == u.identity)
            {
                adjantancy[v.getIdentity()].remove(i);
                break;
            }
        }
    }
}



    
