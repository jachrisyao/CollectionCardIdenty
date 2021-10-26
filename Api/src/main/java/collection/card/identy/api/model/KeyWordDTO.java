package collection.card.identy.api.model;

import java.util.List;

public class KeyWordDTO {
    private List<String> yearKeyWords;
    private List<String> numberKeyWords;
    private List<String> otherKeyWords;

    public List<String> getYearKeyWords() {
        return yearKeyWords;
    }

    public void setYearKeyWords(List<String> yearKeyWords) {
        this.yearKeyWords = yearKeyWords;
    }

    public List<String> getNumberKeyWords() {
        return numberKeyWords;
    }

    public void setNumberKeyWords(List<String> numberKeyWords) {
        this.numberKeyWords = numberKeyWords;
    }

    public List<String> getOtherKeyWords() {
        return otherKeyWords;
    }

    public void setOtherKeyWords(List<String> otherKeyWords) {
        this.otherKeyWords = otherKeyWords;
    }
}
