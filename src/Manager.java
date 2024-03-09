import java.sql.*;

import static javax.management.remote.JMXConnectorFactory.connect;

public class Manager {

    public Patient insertPatient (Patient patient) {
        Patient result = new Patient();

        String SQL = "INSERT INTO patient(name,surname, age, DOB) "
                + "VALUES(?,?,?,?)";
        App app = new App();
        try (Connection conn = app.connect();
             PreparedStatement preparedStatement = conn.prepareStatement(SQL,
                     Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, patient.getName());
            preparedStatement.setString(2, patient.getSurname());
            preparedStatement.setInt(3, patient.getAge());
            preparedStatement.setString(4, patient.getDOB());
            int affectedRows = preparedStatement.executeUpdate();
            // check the affected rows
            if (affectedRows > 0) {
                // get the ID back
                try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                    if (resultSet.next()) {
                        result.setId(resultSet.getLong("id"));
                        result.setName(resultSet.getString("name"));
                        result.setSurname(resultSet.getString("surname"));
                        result.setDOB(resultSet.getString("dob"));
                        result.setAge(resultSet.getInt("age"));
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return result;
    }

    public Patient selectPatient (Patient patient) {
//long id = Integer.MIN_VALUE;
Patient result = new Patient();
           String SQL_SELECT = "SELECT * FROM patient WHERE name = ? AND surname = ? AND dob = ?";

 
        App app = new App();
                try (Connection conn = app.connect();

                    PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT)) {
                    preparedStatement.setString(1, patient.getName());
                    preparedStatement.setString(2, patient.getSurname());
                    preparedStatement.setString(3, patient.getDOB());
                    ResultSet resultSet = preparedStatement.executeQuery();

                    while (resultSet.next()) {
                        result.setId(resultSet.getLong("id"));
                        result.setName(resultSet.getString("name"));
                        result.setSurname(resultSet.getString("surname"));
                        result.setDOB(resultSet.getString("dob"));
                        result.setAge(resultSet.getInt("age"));
                    }
                } catch (SQLException e) {
                    System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return result;
    }

    public long addSymptom (Symptom symptom, long patientId) {
        long id = patientId;

        String SQL = "INSERT INTO symptom (patient_id, bp, cold, flue, pregnancy, kidney, liver, heart, alcohol_unit, weight)"
                + "VALUES (?,?,?,?,?,?,?,?,?,?)";

        App app = new App();
        try (Connection conn = app.connect();
             PreparedStatement preparedStatement = conn.prepareStatement(SQL,
                     Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, patientId);
            preparedStatement.setInt(2, symptom.getBp());
            preparedStatement.setBoolean(3, symptom.isCold());
            preparedStatement.setBoolean(4, symptom.isFlue());
            preparedStatement.setBoolean(5, symptom.isPregnancy());
            preparedStatement.setString(6, symptom.getKidney());
            preparedStatement.setString(7, symptom.getLiver());
            preparedStatement.setString(8, symptom.getHeart());
            preparedStatement.setInt(9, symptom.getAlcohol_unit());
            preparedStatement.setInt(10, symptom.getWeight());

            int affectedRows = preparedStatement.executeUpdate();
            // check the affected rows
            if (affectedRows > 0) {
                // get the ID back
                try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                    if (resultSet.next()) {
                        id = resultSet.getLong("id");
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return id;
    }

    public long selectDrug (String name) {
        long id = Integer.MIN_VALUE;
        String SQL_SELECT = "SELECT * FROM drug WHERE name = ?";
        App app = new App();
        try (Connection conn = app.connect();
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT)) {
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                if (resultSet.getString("name").equals(name)) {
                    return resultSet.getLong("id");
                }
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }
    public Drug viewDrug(long id) //Need another method to get drug information ?
     {
         Drug drug = new Drug();
         String sql = "SELECT * FROM drug WHERE id = ?";
         App app = new App();
         //Create a prepared statement
         try (Connection conn = app.connect();
          PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
             preparedStatement.setLong(1,id);
             ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
           drug.setBp(resultSet.getInt("bp"));
           drug.setAlcohol_units(resultSet.getInt("alcohol_units"));
           drug.setMinimum_weight(resultSet.getInt("minimum_units"));
           drug.setMinimum_age(resultSet.getInt("minimum_age"));
           drug.setKidney(resultSet.getBoolean("kidney"));
           drug.setLiver(resultSet.getBoolean("liver"));
           drug.setPregnancy(resultSet.getBoolean("pregnancy"));
           drug.setHeart(resultSet.getBoolean("heart"));
            }
         } catch (SQLException e) {
             throw new RuntimeException(e);
         }


         return drug;
     }
    public void viewAllDrug() {
        String SQL_SELECT = "SELECT * FROM drug";

        App app = new App();
        try (Connection conn = app.connect();
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
            System.out.print("NAME - "+resultSet.getString("name") + ", ");
            System.out.print("DRUG ID - "+resultSet.getString("id"));
            System.out.println("\nMay not be suitable for patients that have problems with:");

            System.out.print(((resultSet.getInt("BP") > 130) ? "- High BP ("+resultSet.getInt("BP")+")" :""));
            System.out.print(((resultSet.getBoolean("kidney")) ? "- Kidney" :"")) ;
            System.out.print(((resultSet.getBoolean("liver")) ? " - Liver" :""));
            System.out.print(((resultSet.getBoolean("heart")) ? " - Heart" :""));


            System.out.println("\nALCOHOL - "+ ((resultSet.getInt("alcohol_units") != 0) ? resultSet.getInt("alcohol_units") +" UNITS PER WEEK" : "N/A"));
            System.out.println("MINIMUM RECOMMENDED WEIGHT - "+ ((resultSet.getInt("minimum_weight") > 0) ? resultSet.getInt("minimum_weight") +" KG" : "N/A"));
            System.out.println("MINIMUM RECOMMENDED AGE - "+ ((resultSet.getInt("minimum_age") > 0) ? resultSet.getInt("minimum_age") +" Years" : "N/A"));
            System.out.println("SUITABLE FOR PREGNANT - "+ ((resultSet.getBoolean("pregnancy")) ? "Yes": "No"));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public long addPrescription (Prescription prescription) {
        long id = prescription.getPatientId();

        String SQL = "INSERT INTO prescription (patient_id, drug_id, description, issue_date, expiry_date, active)"
                + "VALUES (?,?,?,?,?,?)";

        App app = new App();
        try (Connection conn = app.connect();
             PreparedStatement preparedStatement = conn.prepareStatement(SQL,
                     Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1,prescription.getPatientId());
            preparedStatement.setLong(2,prescription.getDrug_id());
            preparedStatement.setString(3,prescription.getDescription());
            preparedStatement.setString(4,prescription.getIssue_date());
            preparedStatement.setString(5,prescription.getExpiry_date());
            preparedStatement.setBoolean(6,prescription.isActive());

            int affectedRows = preparedStatement.executeUpdate();
            // check the affected rows
            if (affectedRows > 0) {
                // get the ID back
                try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                    if (resultSet.next()) {
                        id = resultSet.getLong("id");
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return id;
    }

    public long addDrug (Drug drug) {
        String sql = "INSERT INTO drug (kidney, liver, pregnancy, name, bp, minimum_age, alcohol_units, minimum_weight, heart) "
        + "VALUES (?,?,?,?,?,?,?,?,?)";

        App app = new App();
        try (Connection conn = app.connect();
             PreparedStatement preparedStatement = conn.prepareStatement(sql,
                     Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setBoolean(1, drug.isKidney());
            preparedStatement.setBoolean(2, drug.isLiver());
            preparedStatement.setBoolean(3, drug.isPregnancy());
            preparedStatement.setBoolean(9, drug.isHeart());
            preparedStatement.setString(4, drug.getName());
            preparedStatement.setInt(5, drug.getBp());
            preparedStatement.setInt(6, drug.getMinimum_age());
            preparedStatement.setInt(7, drug.getAlcohol_units());
            preparedStatement.setInt(8, drug.getMinimum_weight());

            System.out.println(preparedStatement);
            int affectedRows = preparedStatement.executeUpdate();
            // check the affected rows
            if (affectedRows > 0) {
                // get the ID back
                try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                    if (resultSet.next()) {
                        drug.setId(resultSet.getLong("id"));
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return drug.getId();
    }
    public boolean deleteDrug (Drug drug) {
        // Create the SQL delete query
        String deleteQuery = "DELETE FROM drug WHERE name = ? AND id = ?";
        Connection connection = new App().connect();

        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {

            preparedStatement.setString(1, drug.getName());
            preparedStatement.setLong(2, drug.getId());
            // Execute the delete query
            int rowsAffected = preparedStatement.executeUpdate();

            // Check the number of rows affected to determine if the delete was successful
            if (rowsAffected > 0) {
                System.out.println("Record with ID " + drug.getId() + " deleted successfully.");
            } else {
                System.out.println("No record found with ID " + drug.getId() + ". Nothing deleted.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return false;
    }
    public int updateDrug (Drug drug) {
//        var sql  = "UPDATE drug "
//                + "SET name = ?, bp = ? "
//                + "WHERE id = ?";
        var sql = "UPDATE drug SET ";
        if (!drug.getName().isEmpty()) {sql += "name = '" + drug.getName()+ "' ";}
        if (drug.getBp() != 0) {sql += ", bp = " + drug.getBp();}

        sql += " WHERE id = ?";

        int affectedRows = 0;
        App app = new App();
        try (var conn  = app.connect();
             var preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setLong(1, drug.getId());
            affectedRows = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return affectedRows;
    }

    public int updatePatient (Patient patient) {
//        var sql  = "UPDATE drug "
//                + "SET name = ?, bp = ? "
//                + "WHERE id = ?";
        var sql = "UPDATE patient SET ";
        if (!patient.getName().isEmpty()) {sql += "name = '" + patient.getName()+ "' ";}
        if (!patient.getSurname().isEmpty()) {sql += ", surname = '" + patient.getSurname()+"' ";}
        if (!patient.getDOB().isEmpty()) {sql += ", dob = '" + patient.getDOB()+"' ";}
        if (patient.getAge() != Integer.MIN_VALUE) {sql += ", age = " + patient.getAge();}
        sql += " WHERE id = ?";;
System.out.println(sql);
        int affectedRows = 0;
        App app = new App();
        try (var conn  = app.connect();
             var preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setLong(1, patient.getId());
            affectedRows = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return affectedRows;
    }

    public String viewPrescription (String template, Long id) {
        String receiptContent ="";
        boolean isNotFound = true;
        String SQL_SELECT = "SELECT patient.name, patient.id, prescription.description, patient.age, drug.name AS drugname, drug.bp FROM patient INNER JOIN prescription ON patient.id = prescription.patient_id INNER JOIN drug ON drug.id = prescription.drug_id WHERE patient.id = ?";

        App app = new App();
        try (Connection conn = app.connect();
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT)) {
             preparedStatement.setLong(1, id);

             ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                // Replace placeholders with actual data

                isNotFound = false;
                 receiptContent = String.format(template, resultSet.getString("name"), "Charles", "12/01/2024",
                        resultSet.getString("drugname"), 33, 2.1, 4.1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
if (isNotFound) {return "No Prescription found \n";};
return  receiptContent;
    }

    public Symptom viewSymptom(long id){
        Symptom symptom = new Symptom();
        String sql = "SELECT * FROM symptom WHERE symptom.patient_id = ? ORDER BY id DESC LIMIT 1;";
        App app = new App();
        try (Connection conn = app.connect();
            PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                // Replace placeholders with actual data
                symptom.setKidney(resultSet.getString("kidney"));
                symptom.setLiver(resultSet.getString("liver"));
                symptom.setHeart(resultSet.getString("heart"));
                symptom.setAlcohol_unit(resultSet.getInt("alcohol_unit"));
                symptom.setWeight(resultSet.getInt("weight"));
                symptom.setPregnancy(resultSet.getBoolean("pregnancy"));
                symptom.setCold(resultSet.getBoolean("cold"));
                symptom.setFlue(resultSet.getBoolean("flue"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    return symptom;
    }
}
