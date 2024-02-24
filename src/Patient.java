public class Patient {
    String name;
    String surname;
    String DOB;
    int age;
    long id;

    public void setId (long id){
        this.id = id;
    }
    public long getId (){return id;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    Patient(String name, String DOB, int age, String surname){
        this.name = name;
        this.age = age;
        this.DOB = DOB;
        this.surname = surname;
        this.id = Integer.MIN_VALUE;
    }
    Patient (){ this.id = Integer.MIN_VALUE;}



}
