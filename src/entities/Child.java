package entities;


import enums.Category;
import enums.Cities;

import java.util.List;

public final class Child {
    private int id;
    private String lastName;
    private String firstName;
    private int age;
    private Cities city;
    private List<Double> niceScore;
    private List<Category> giftPreferences;

    public Child(final int id, final String lastName, final String firstName, final int age,
                 final Cities city, final List<Double> niceScore,
                 final List<Category> giftPreferences) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.age = age;
        this.city = city;
        this.niceScore = niceScore;
        this.giftPreferences = giftPreferences;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(final int age) {
        this.age = age;
    }

    public Cities getCity() {
        return city;
    }

    public void setCity(final Cities city) {
        this.city = city;
    }

    public List<Double> getNiceScore() {
        return niceScore;
    }

    public void setNiceScore(final List<Double> niceScore) {
        this.niceScore = niceScore;
    }

    public List<Category> getGiftPreferences() {
        return giftPreferences;
    }

}
