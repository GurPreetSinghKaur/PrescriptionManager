public class Prescription {
    long id;
    long patientId;
    long drug_id;
    String description;

    Prescription (){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPatientId() {
        return patientId;
    }

    public void setPatientId(long patientId) {
        this.patientId = patientId;
    }

    public long getDrug_id() {
        return drug_id;
    }

    public void setDrug_id(long drug_id) {
        this.drug_id = drug_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
