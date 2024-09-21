package com.test.model;

public class Employee {
    private int empId;
    private String fname;
    private String country;

    public Employee() {
    }

    public Employee(int empId, String fname, String country) {
        this.empId = empId;
        this.fname = fname;
        this.country = country;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empId=" + empId +
                ", fname='" + fname + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}