public class Symptom {
    int bp;
    boolean flue;
    boolean cold;
    boolean pregnancy;
    String kidney;
    String liver;
    String heart;
    int alcohol_units;
    int weight;

    public String getKidney() {
        return kidney;
    }

    public void setKidney(String kidney) {
        this.kidney = kidney;
    }

    public String getLiver() {
        return liver;
    }

    public void setLiver(String liver) {
        this.liver = liver;
    }

    public String getHeart() {
        return heart;
    }

    public void setHeart(String heart) {
        this.heart = heart;
    }

    public int getAlcohol_units() {
        return alcohol_units;
    }

    public void setAlcohol_units(int alcohol_units) {
        this.alcohol_units = alcohol_units;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Symptom (int bp, boolean flue, boolean cold, boolean pregnancy) {
    this.bp = bp;
    this.flue = flue;
    this.cold = cold;
    this.pregnancy = pregnancy;
    }
public Symptom () {}
    public int getBp() {
        return bp;
    }

    public void setBp(int bp) {
        this.bp = bp;
    }

    public boolean isFlue() {
        return flue;
    }

    public void setFlue(boolean flue) {
        this.flue = flue;
    }

    public boolean isCold() {
        return cold;
    }

    public void setCold(boolean cold) {
        this.cold = cold;
    }

    public boolean isPregnancy() {
        return pregnancy;
    }

    public void setPregnancy(boolean pregnancy) {
        this.pregnancy = pregnancy;
    }
}
