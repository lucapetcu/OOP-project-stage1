package entities;

import java.util.List;

public final class AnnualChange {
    private Double newSantaBudget;
    private List<Gift> newGifts;
    private List<Child> newChildren;
    private List<ChildUpdate> childUpdates;

    public AnnualChange(final Double newSantaBudget, final List<Gift> newGifts,
                        final List<Child> newChildren, final List<ChildUpdate> childUpdates) {
        this.newSantaBudget = newSantaBudget;
        this.newGifts = newGifts;
        this.newChildren = newChildren;
        this.childUpdates = childUpdates;
    }

    public Double getNewSantaBudget() {
        return newSantaBudget;
    }

    public void setNewSantaBudget(final Double newSantaBudget) {
        this.newSantaBudget = newSantaBudget;
    }

    public List<Gift> getNewGifts() {
        return newGifts;
    }

    public void setNewGifts(final List<Gift> newGifts) {
        this.newGifts = newGifts;
    }

    public List<Child> getNewChildren() {
        return newChildren;
    }

    public void setNewChildren(final List<Child> newChildren) {
        this.newChildren = newChildren;
    }

    public List<ChildUpdate> getChildUpdates() {
        return childUpdates;
    }

}
