public class Drug {
    String name;
    int bp;
    long id;

    public Drug (String name, int bp){
        this.id = Integer.MIN_VALUE;
    }
    public Drug (){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBp(int bp) {
        this.bp = bp;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getBp(){
        return bp;
    }

}
