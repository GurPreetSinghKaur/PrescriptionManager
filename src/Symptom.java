public class Symptom {
    int bp;
    boolean flue;
    boolean cold;
    boolean pregnancy;

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
