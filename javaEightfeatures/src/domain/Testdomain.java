package domain;

import java.util.List;

public class Testdomain {
	
	/**
	 * 
	 */
	private String name;
	private int gradeLevel;
	private double gpa;
	private String gender;
	private List<String> activaties;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getGradeLevel() {
		return gradeLevel;
	}
	public void setGradeLevel(int gradeLevel) {
		this.gradeLevel = gradeLevel;
	}
	public double getGpa() {
		return gpa;
	}
	public void setGpa(double gpa) {
		this.gpa = gpa;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public List<String> getActivaties() {
		return activaties;
	}
	public void setActivaties(List<String> activaties) {
		this.activaties = activaties;
	}

}
