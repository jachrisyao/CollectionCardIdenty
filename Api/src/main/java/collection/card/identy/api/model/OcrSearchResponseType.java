package collection.card.identy.api.model;

import java.util.List;

public class OcrSearchResponseType {
    private String keywords;
    private List<ComcBasketballDTO> cardList;

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public List<ComcBasketballDTO> getCardList() {
        return cardList;
    }

    public void setCardList(List<ComcBasketballDTO> cardList) {
        this.cardList = cardList;
    }
}
