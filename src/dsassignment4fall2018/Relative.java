/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsassignment4fall2018;

/**
 * @version Fall2018
 * @author clatulip
 */
public class Relative implements Comparable<Relative> {

    private String name;
    private String spouse;
    private int yearBirth;
    private int yearDeath;

    /**
     * @param name field
     * @param spouse field
     * @param yearBirth field
     * @param yearDeath field
     */
    public Relative(String name, String spouse, int yearBirth, int yearDeath) {
        this.name = name;
        this.spouse = spouse;
        this.yearBirth = yearBirth;
        this.yearDeath = yearDeath;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name field
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return spouse
     */
    public String getSpouse() {
        return spouse;
    }

    /**
     * @param spouse set
     */
    public void setSpouse(String spouse) {
        this.spouse = spouse;
    }

    /**
     * @return yearBirth
     */
    public int getYearBirth() {
        return yearBirth;
    }

    /**
     * @param yearBirth set
     */
    public void setYearBirth(int yearBirth) {
        this.yearBirth = yearBirth;
    }

    /**
     * @return yearDeath
     */
    public int getYearDeath() {
        return yearDeath;
    }

    /**
     * @param yearDeath set
     */
    public void setYearDeath(int yearDeath) {
        this.yearDeath = yearDeath;
    }

    @Override
    public String toString() {
        return "FamMember{" + "name=" + name + ", spouse=" + spouse
                + ", yearBirth=" + yearBirth + ", yearDeath=" + yearDeath + '}';
    }

    /**
     * @param o other
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        Relative fm = (Relative) o;
        return (this.name.equalsIgnoreCase(fm.getName())) && (this.yearBirth == fm.getYearBirth());
    }

    @Override
    public int compareTo(Relative o) {
        return this.yearBirth - o.getYearBirth();
    }

}
