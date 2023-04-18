public class Island implements Comparable<Island>{
int distance;
int identity;


public Island(int distance,int identity){
    this.distance=distance;
    this.identity=identity;
}

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getIdentity() {
        return identity;
    }

    public void setIdentity(int identity) {
        this.identity = identity;
    }

    @Override
    public int compareTo(Island o) {
        if(this.distance>o.distance){
            return 1;
        }
        else
            return -1;

    }
    public String toString(){
    String str = ""+this.identity;
    return str;
    }
}
