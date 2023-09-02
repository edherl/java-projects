package com.mycompany.mapa;

class Person {
    private String name;
    private String bloodType;
    private int birthYear;

    public Person(String name, String bloodType, int birthYear) {
        this.name = name;
        this.bloodType = bloodType;
        this.birthYear = birthYear;
    }

    public String getName() {
        return name;
    }

    public String getBloodType() {
        return bloodType;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public int calculateAge(int currentYear) {
        return currentYear - birthYear;
    }
}

class Test {
    private Person patient;
    private double value;

    public Test(Person patient, double value) {
        this.patient = patient;
        this.value = value;
    }

    public Person getPatient() {
        return patient;
    }

    public double getValue() {
        return value;
    }

    public void showResult(String testType, String result) {
        System.out.println(testType + " Test: " + value + " mg/dL");
        System.out.println("Result: " + result);
    }
}

class GlucoseTest extends Test {
    public GlucoseTest(Person patient, double value) {
        super(patient, value);
    }

    public void classifyResult() {
        String result = (getValue() < 100) ? "Normal Glucose" :
                        (getValue() < 126) ? "Pre-Diabetes" : "Established Diabetes";
        showResult("Glucose", result);
    }
}

class CholesterolTest extends Test {
    private double LDLQuantity;
    private double HDLQuantity;

    public CholesterolTest(Person patient, double value, double LDLQuantity, double HDLQuantity) {
        super(patient, value);
        this.LDLQuantity = LDLQuantity;
        this.HDLQuantity = HDLQuantity;
    }

    public double getLDLQuantity() {
        return LDLQuantity;
    }

    public double getHDLQuantity() {
        return HDLQuantity;
    }

    public void classifyResult() {
        String LDLResult = "";
        if (getLDLQuantity() < 100) {
            LDLResult = "LDL - Low Risk";
        } else if (getLDLQuantity() < 70) {
            LDLResult = "LDL - Medium Risk";
        } else if (getLDLQuantity() < 50) {
            LDLResult = "LDL - High Risk";
        }
        
        String HDLResult = (getPatient().calculateAge(2023) <= 19 && getHDLQuantity() > 45) ||
                            (getPatient().calculateAge(2023) > 19 && getHDLQuantity() > 40) ?
                            "HDL - Good" : "HDL - Poor";

        showResult("Cholesterol", LDLResult + " | " + HDLResult);
    }
}

class TriglyceridesTest extends Test {
    public TriglyceridesTest(Person patient, double value) {
        super(patient, value);
    }

    public void classifyResult() {
        int age = getPatient().calculateAge(2023);
        String result = (age < 10 && getValue() < 75) ||
                        (age >= 10 && age < 20 && getValue() < 90) ||
                        (age >= 20 && getValue() < 150) ? "Normal" : "High";
        showResult("Triglycerides", result);
    }
}

public class MAPA {
    public static void main(String[] args) {
        Person person = new Person("Edher Antunes", "A+", 1997);
        
        GlucoseTest glucoseTest = new GlucoseTest(person, 95);
        glucoseTest.classifyResult();
        
        CholesterolTest cholesterolTest = new CholesterolTest(person, 150.0, 55.0, 65.0);
        cholesterolTest.classifyResult();
        
        TriglyceridesTest triglyceridesTest = new TriglyceridesTest(person, 75.0);
        triglyceridesTest.classifyResult();
    }
}