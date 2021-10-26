package collection.card.identy.api.model;

import java.util.List;

public class OcrSearchResponseType {
    private KeyWordDTO keywords;
    private List<ComcBasketballDTO> cardList;

    public KeyWordDTO getKeywords() {
        return keywords;
    }

    public void setKeywords(KeyWordDTO keywords) {
        this.keywords = keywords;
    }

    public List<ComcBasketballDTO> getCardList() {
        return cardList;
    }

    public void setCardList(List<ComcBasketballDTO> cardList) {
        this.cardList = cardList;
    }
}
