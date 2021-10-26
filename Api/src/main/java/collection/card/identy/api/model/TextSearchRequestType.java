package collection.card.identy.api.model;

import java.util.List;

public class TextSearchRequestType {
    private String title;
    private KeyWordDTO keyWords;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public KeyWordDTO getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(KeyWordDTO keyWords) {
        this.keyWords = keyWords;
    }
}
