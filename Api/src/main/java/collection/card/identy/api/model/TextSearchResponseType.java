package collection.card.identy.api.model;

import java.util.List;

public class TextSearchResponseType {
    private List<ComcBasketballDTO> cardList;

    public List<ComcBasketballDTO> getCardList() {
        return cardList;
    }

    public void setCardList(List<ComcBasketballDTO> cardList) {
        this.cardList = cardList;
    }
}
