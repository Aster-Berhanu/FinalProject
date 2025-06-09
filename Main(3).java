import java.util.*;

class Medication {
    String name;
    String type;

    Medication(String name, String type) {
        this.name = name;
        this.type = type;
    }
}

class Patient {
    String name;
    List<Medication> meds = new ArrayList<>();

    static String[][] conflictPairs = {
            {"A", "B"},
            {"B", "C"},
            {"C", "D"},
            {"D", "E"}
    };

    Patient(String name) {
        this.name = name;
    }

    boolean hasConflict(String newType) {
        for (Medication existing : meds) {
            String existingType = existing.type;
            for (String[] pair : conflictPairs) {
                if ((pair[0].equals(newType) && pair[1].equals(existingType)) || 
                    (pair[1].equals(newType) && pair[0].equals(existingType))) {
                    System.out.println("Conflict: " + newType + " conflicts with existing " + existingType);
                    return true;
                }
            }
        }
        return false;
    }

    void addMedication(String name, String type) {
        if (hasConflict(type)) {
            System.out.println("Cannot add " + name + " (" + type + ") due to conflict.");
            return;
        }
        Medication newMed = new Medication(name, type);
        meds.add(newMed);
        System.out.println("Added " + name + " (" + type + ") for " + this.name);
    }

    void showMedications() {
        System.out.println(name + "'s Medications:");
        if (meds.isEmpty()) {
            System.out.println(" - No medications");
        } else {
            for (Medication m : meds) {
                System.out.println(" - " + m.name + " (" + m.type + ")");
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Patient> patients = new ArrayList<>();

        while (true) {
            System.out.println("\n1. Add New Patient");
            System.out.println("2. Add Medication for Patient");
            System.out.println("3. Show Patient Medications");
            System.out.println("4. Show All Patients");
            System.out.println("5. Exit");
            System.out.print("Choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                System.out.print("Enter patient name: ");
                String name = scanner.nextLine();
                patients.add(new Patient(name));
                System.out.println("Added patient: " + name);

            } else if (choice == 2) {
                if (patients.isEmpty()) {
                    System.out.println("No patients available. Add a patient first.");
                    continue;
                }
                System.out.println("Select patient:");
                for (int i = 0; i < patients.size(); i++) {
                    System.out.println((i + 1) + ". " + patients.get(i).name);
                }
                System.out.print("Enter patient number: ");
                int patientIndex = scanner.nextInt() - 1;
                scanner.nextLine();

                if (patientIndex < 0 || patientIndex >= patients.size()) {
                    System.out.println("Invalid patient number.");
                    continue;
                }

                System.out.print("Enter medication name: ");
                String name = scanner.nextLine();
                System.out.print("Enter medication type: ");
                String type = scanner.nextLine().toUpperCase();

                patients.get(patientIndex).addMedication(name, type);

            } else if (choice == 3) {
                if (patients.isEmpty()) {
                    System.out.println("No patients available.");
                    continue;
                }
                System.out.println("Select patient:");
                for (int i = 0; i < patients.size(); i++) {
                    System.out.println((i + 1) + ". " + patients.get(i).name);
                }
                System.out.print("Enter patient number: ");
                int patientIndex = scanner.nextInt() - 1;
                scanner.nextLine();

                if (patientIndex < 0 || patientIndex >= patients.size()) {
                    System.out.println("Invalid patient number.");
                    continue;
                }

                patients.get(patientIndex).showMedications();

            } else if (choice == 4) {
                if (patients.isEmpty()) {
                    System.out.println("No patients available.");
                } else {
                    System.out.println("All Patients:");
                    for (Patient p : patients) {
                        System.out.println(" - " + p.name);
                    }
                }

            } else if (choice == 5) {
                break;
            } else {
                System.out.println("Invalid choice.");
            }
        }

        scanner.close();
        System.out.println("Program terminated.");
    }
}