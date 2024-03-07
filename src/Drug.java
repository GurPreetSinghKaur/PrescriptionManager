public class Drug {
    String name;
    int bp;
    long id;
    boolean kidney;
    boolean liver;
    boolean pregnancy;
    boolean heart;

    public boolean isHeart() {
        return heart;
    }

    public void setHeart(boolean heart) {
        this.heart = heart;
    }

    public boolean isPregnancy() {
        return pregnancy;
    }

    public void setPregnancy(boolean pregnancy) {
        this.pregnancy = pregnancy;
    }

    int alcohol_units;
    int minimum_weight;
    int minimum_age;


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

    public boolean isKidney() {
        return kidney;
    }

    public void setKidney(boolean kidney) {
        this.kidney = kidney;
    }

    public boolean isLiver() {
        return liver;
    }

    public void setLiver(boolean liver) {
        this.liver = liver;
    }

    public int getAlcohol_units() {
        return alcohol_units;
    }

    public void setAlcohol_units(int alcohol_units) {
        this.alcohol_units = alcohol_units;
    }

    public int getMinimum_weight() {
        return minimum_weight;
    }

    public void setMinimum_weight(int minimum_weight) {
        this.minimum_weight = minimum_weight;
    }

    public int getMinimum_age() {
        return minimum_age;
    }

    public void setMinimum_age(int minimum_age) {
        this.minimum_age = minimum_age;
    }
}
