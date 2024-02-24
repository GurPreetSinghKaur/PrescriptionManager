public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
Manager manager = new Manager();
Patient patient = new Patient("Raja", "01/01/26", 2, "Singh");
//
//       System.out.println(manager.selectPatient(patient));
//
//Symptom symptom = new Symptom(54, true, false, true);
//
//
//manager.addSymptom(symptom, 10);
Menu menu = new Menu();
menu.start();

    }



}