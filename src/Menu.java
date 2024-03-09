import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

 class Menu {
     static Menu menu = new Menu();
private Menu (){}

     public static Menu getMenu() {
         return menu;
     }

     public void start(){
    Manager manager = new Manager();
    int choice = 0;
    int switchChoice = 0;

    Patient patient = new Patient();
    Scanner scanner = new Scanner(System.in);

    while(choice != 9) {

        System.out.println("Welcome to the Prescription Manager");
        System.out.println("Select from one of the options");
        System.out.println("1 - Select a patient");
        System.out.println("2 - Register a new patient");
        System.out.println("3 - View or modify drugs");
        System.out.println("4 - View or modify symptoms");
        System.out.println("9 - Quit the program");
        if (patient.getId() != Integer.MIN_VALUE) {
        System.out.println("Patient selected with ID -> " + patient.getId()+ ", Name: " + patient.getName() + ", Surname: " + patient.getSurname());}
        choice = scanner.nextInt();
        switch (choice) {
            case 1: //Select a patient

                if (patient.getId() != Integer.MIN_VALUE) {
                    System.out.println("Patient selected with ID -> " + patient.getId()+ ", Name: " + patient.getName() + ", Surname: " + patient.getSurname());
                    System.out.println();
                    System.out.println("Continue with the selected patient? Press Y to continue or N to select a new patient");
                    String temp = scanner.next();
                    if (temp.equalsIgnoreCase("y")) ;
                    else if (temp.equalsIgnoreCase("n")) {
                        patient = new Patient();
                        getPatientDetails(patient);}

                }   else {
                    getPatientDetails(patient);
                }
                    patient =  manager.selectPatient(patient);
                        if (patient.getId() != Integer.MIN_VALUE) {
                            int innerChoice = 0;
                            while (innerChoice != 9) {
                                System.out.println("1 - Add symptom");
                                System.out.println("2 - Add prescription");
                                System.out.println("3 - Change patient details");
                                System.out.println("4 - Delete patient");
                                System.out.println("5 - View prescription");
                                System.out.println("9 - Go to previous menu");
                                int choice2;
                                choice2 = Utility.addInt("");
                                switch (choice2) {
                                    case 1:
                                        Symptom symptom = new Symptom();
                                        addSymptom(symptom);
                                        manager.addSymptom(symptom, patient.getId());
                                        break;

                                    case 2: //add prescription
                                        System.out.println("Make sure you have filled in the symptom form before adding a prescription");
                                        //Need to add more examples of how to double check for the right prescription
                                        Prescription prescription = new Prescription();
                                        Symptom symptom1 =  manager.viewSymptom(patient.getId());

                                        Drug drug = new Drug();
                                        getDrugDetails(drug, manager);
                                        manager.viewDrug(drug);

//                                        System.out.println("Enter the name of the drug:");
//                                        while (prescription.getDrug_id() != Integer.MIN_VALUE) {
//                                        System.out.println("Drug not found, try again.");
//                                        prescription.setDrug_id(manager.selectDrug(scanner.next()));
//                                        }

                                        prescription.setDrug_id(drug.getId());
                                        //Format date & Set current date
                                        LocalDate date = LocalDate.now();
                                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                                        String formattedDate = date.format(formatter);
                                        prescription.setIssue_date(formattedDate);

                                        prescription.setExpiry_date(Utility.addDate("Provide an expiry date or press enter to skip it", true));
                                        System.out.println("Description:");
                                        prescription.setDescription(scanner.next());
                                        prescription.setPatientId(patient.getId());

                                        //need to fix when the database hold record with  null values;

                                        //Check for any medicine that might cause an issue before submitting the prescription
                                        if (symptom1.getBp() != 0 && symptom1.getBp() > drug.getBp())  System.out.println("Patient's blood pressure is "+symptom1.getBp()+" and "+drug.getName()+" may not be suitable \n");
                                        if (symptom1.getWeight() != 0 && symptom1.getWeight() < drug.getMinimum_weight()) System.out.println("Patient's weight is" + symptom1.getWeight() + "kg." + drug.getName() + " may not be suitable ("+drug.getMinimum_weight()+") \n");
                                        if (symptom1.getAlcohol_unit() != 0 && symptom1.getAlcohol_unit() > drug.getAlcohol_units() ) System.out.println("Patient consumes more alcohol than recommended ("+symptom1.getAlcohol_unit() +" units per week), "+ drug.getName() + " may not be suitable ("+drug.getAlcohol_units()+")\n");
//                                        Use Optional
                                        if (!symptom1.getHeart().isEmpty() && drug.isHeart()) System.out.println("Patient might have heart problem: "+ symptom1.getHeart());
                                        if (!symptom1.getKidney().isEmpty() && drug.isKidney()) System.out.println("Patient might have kidney problem: "+ symptom1.getKidney());
                                        if (!symptom1.getLiver().isEmpty() && drug.isLiver()) System.out.println("Patient is pregnant and " +drug.getName() + " might not be suitable");
                                        //Prescription is ready, double check again
                                        System.out.println("Prescription is ready, would you like to submit it ? Type in 'Y' to confirm or 'N' to cancel");
                                        if (scanner.next().equalsIgnoreCase("y")) manager.addPrescription(prescription);
                                        else {System.out.println("Prescription was not added ");}


                                        break;
                                    case 3://Change patient details

                                        String userInput = "";
                                        if (patient.getId() != Integer.MIN_VALUE) {
                                            userInput =  Utility.addString("Enter a new name of the patient or press 's' to skip");

                                            if(!userInput.isEmpty()) {

                                                System.out.println("New name is -> " + userInput);

                                                patient.setName(userInput);
                                            } else {
                                                System.out.println("Name skipped");
                                            }
                                            userInput = Utility.addString("Type in a new value for surname or press enter to skip");

                                            if(!userInput.isEmpty()) {
                                                System.out.println("New surname is -> " + userInput);
                                                patient.setSurname(userInput);
                                            } else {
                                                System.out.println("Surname skipped");
                                            }
                                            int tempInt = Utility.addInt("Type in a new value for age or press enter to skip");
                                            if (tempInt != Integer.MIN_VALUE) {
                                                System.out.println("New age is -> " + tempInt);
                                                patient.setAge(tempInt);
                                            } else {
                                                System.out.println("age skipped");
                                                patient.setAge(Integer.MIN_VALUE);
                                            }
                                            userInput = Utility.addDate("Type in a new value for date of birth or press enter to skip",true); //Utility.addString("Type in a new value for date of birth or press enter to skip");
                                            if(!userInput.isEmpty()) {
                                                System.out.println("New date of birth is -> " + userInput);
                                                patient.setDOB(userInput);
                                            } else {
                                                System.out.println("Date of birth skipped");
                                            }
                                            manager.updatePatient(patient);
                                        } else {System.out.println("Patient not found");}
                                        break;
                                    case 4:   break;
                                    case 5:  //View prescription
                                        String template = "---------------------------------\n" +
                                                "        PRESCRIPTION RECEIPT        \n" +
                                                "---------------------------------\n" +
                                                "Patient: %s\n" +
                                                "Doctor: %s\n" +
                                                "Date: %s\n" +
                                                "---------------------------------\n" +
                                                "Symptom: %s\n" +
                                                "Quantity: %d\n" +
                                                "Price per unit: £%.2f\n" +
                                                "Total Amount: £%.2f\n" +
                                                "Instructions on how to take medication: " +
                                                "---------------------------------\n";
                                        System.out.print(manager.viewPrescription(template, patient.getId()) );
                                        break;
                                    case 9: innerChoice = 9;
                                    break ;
                                }
                            }
                        } else if (patient.getId() == Integer.MIN_VALUE) System.out.println("No patient found with these details");
                        break;
            case 2: //Register a new patient
                    getPatientDetails(patient);
                  patient =  manager.insertPatient(patient);
                break;
            case 3: //View or modify drug
                int innerChoice = 0;
                while (innerChoice != 9) {
                    System.out.println("1 - View all drugs");
                    System.out.println("2 - Add a drug");
                    System.out.println("3 - Modify drug");
                    System.out.println("4 - Delete drug");
                    System.out.println("9 - Go to previous menu");
                    int choice2 = scanner.nextInt();
                    Drug drug = new Drug();
                    switch (choice2) {
                        case 1: // view all medicines
                          manager.viewAllDrug();
                            break;

                        case 2: //add new drug
                          if ( manager.addDrug(addDrug(drug)) != Integer.MIN_VALUE) {
                              System.out.println("Successfully added with ID number ->" + drug.getId());
                          } else {
                              System.out.println("There was a problem adding the drug");
                          }
                            break;
                        case 3:
                            String stringInput;
                             getDrugDetails(drug, manager);
                                if (drug.getId() != Integer.MIN_VALUE){
                                System.out.println("Type in a new name or press enter to skip");
                                    stringInput = scanner.next();
                                if(!stringInput.isEmpty()) {
                                    System.out.println("New name is -> " + stringInput);
                                    drug.setName(stringInput);
                                } else {
                                    System.out.println("Name skipped");
                                }
                                System.out.println("Type in a new value for BP press enter to skip");
                                int tempBP = scanner.nextInt();

                                    if(tempBP != 0) {
                                        System.out.println("New BP is -> " + tempBP);
                                        drug.setBp(scanner.nextInt());
                                    } else {
                                        System.out.println("BP skipped");
                                    }
                              }
                            manager.updateDrug(drug);
                            break;
                        case 4: ;
                            if ( getDrugDetails(drug, manager)) {
                            System.out.println("Would you like to delete '" + drug.getName()+ "' with ID '" + drug.getId()+"'");
                            System.out.println("Press Y to delete '"+drug.getName()+ "' or N to cancel ");
                            if (scanner.next().equalsIgnoreCase("y")) {
                                manager.deleteDrug(drug);
                            } else {
                                System.out.println(drug.getName()+ " was not deleted");
                            }}
                        break;
                        case 9: innerChoice = 9;
                            break ;
                    }
                }
                break;
            case 9: System.out.println("Quitting the program...");
            break;
        }
    }
}
private boolean getDrugDetails (Drug drug, Manager manager){
    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter the name of the drug:");
    String drugName = scanner.next();
    drug.setName(drugName);
    drug.setId(manager.selectDrug(drugName));
    while (drug.getId() == Integer.MIN_VALUE  ) {
        System.out.println("Drug not found, try again. Press 9 to return ");
        drugName = scanner.next();
        if (drugName.equals("9")) return false;
        drug.setId(manager.selectDrug(drugName));
        drug.setName(drugName);
    }


return true;
}

private void getPatientDetails(Patient patient){
    patient.setName(Utility.addString("Enter patient's name"));
    patient.setSurname(Utility.addString("Enter patient's surname"));
    patient.setDOB(Utility.addDate("Enter patient's date of birth as DD/MM/YYYY", false));
    //    Scanner scanner = new Scanner(System.in);
//    System.out.println("Enter patient's name");
//    patient.setName(scanner.next());
//    System.out.println("Enter patient's surname");
//    patient.setSurname(scanner.next());
//    System.out.println("Enter patient's date of birth as DD/MM/YYYY");
//    patient.setDOB(scanner.next());

}
private Symptom addSymptom (Symptom symptom){
    Scanner scanner = new Scanner(System.in);
    System.out.println("Answer the following questions:");
    System.out.println("Patient's blood pressure. e.g. 93");
    symptom.setBp(scanner.nextInt());
    System.out.println("Flue? Y or N");
    String tempResponse = scanner.next().toLowerCase();
    if (tempResponse.equals("y"))  symptom.setFlue(true);
    else if (tempResponse.equals("n"))  symptom.setFlue(false);
    System.out.println("Cold? Y or N");
    tempResponse = scanner.next().toLowerCase();
    if (tempResponse.equals("y"))  symptom.setCold(true);
    else if (tempResponse.equals("n"))  symptom.setCold(false);
    System.out.println("Pregnant? Y or N");
    tempResponse = scanner.next().toLowerCase();
    if (tempResponse.equals("y"))  symptom.setPregnancy(true);
    else if (tempResponse.equals("n"))  symptom.setPregnancy(false);

    symptom.setKidney(Utility.addString("Specify any kidney related problem. Otherwise press enter to skip"));
    symptom.setLiver(Utility.addString("Specify any liver related problem. Otherwise press enter to skip"));
    symptom.setHeart(Utility.addString("Specify any heart related problem. Otherwise press enter to skip"));
    symptom.setAlcohol_unit(Utility.addInt("Weekly alcohol intake in units"));
    symptom.setWeight(Utility.addInt("Patient weight in KG"));

    return symptom;
}
  private Drug addDrug(Drug drug){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Answer the following questions:");
        System.out.println("Drug name");
        drug.setName(scanner.next());
        drug.setBp(Utility.addInt("BP requirement? Minimum BP e.g. 58, otherwise type in 0"));
        System.out.println("Suitable for patient with kidney problems? Type in 'Y' or 'N' ");
        drug.setKidney(!scanner.next().equalsIgnoreCase("y"));
        System.out.println("Suitable for patient with liver problems? Type in 'Y' or 'N' ");
        drug.setLiver(!scanner.next().equalsIgnoreCase("y"));
        drug.setAlcohol_units(Utility.addInt("Suitability for maximum amount of alcohol recommended in units per week.\ne.g. type in '15' for 15 units a week. or type in 0 "));
        drug.setMinimum_age(Utility.addInt("Minimum recommended patient age. Otherwise type in 0"));
        drug.setMinimum_weight(Utility.addInt("Minimum recommended patient weight. Otherwise type in 0"));
        System.out.println("Suitable for pregnant patient? Type in 'Y' or 'N'");
        drug.setPregnancy(!scanner.next().equalsIgnoreCase("y"));
        System.out.println("Suitable for patient with heart problems ? Type in 'Y' or 'N'");
        drug.setHeart(!scanner.next().equalsIgnoreCase("y"));

      return drug;
    }


}
