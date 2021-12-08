package entities;


import enums.Category;

import java.util.List;

public final class ChildUpdate {
    private int id;
    private Double newNiceScore;
    private List<Category> newGiftPreferences;

    public ChildUpdate(final int id, final Double newNiceScore,
                       final List<Category> newGiftPreferences) {
        this.id = id;
        this.newNiceScore = newNiceScore;
        this.newGiftPreferences = newGiftPreferences;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public Double getNewNiceScore() {
        return newNiceScore;
    }

    public List<Category> getNewGiftPreferences() {
        return newGiftPreferences;
    }

}
