package collection.card.identy.api.model;

import java.util.List;

public class SearchResponseType {
    private List<BasketballCardModel> resultList;

    public List<BasketballCardModel> getResultList() {
        return resultList;
    }

    public void setResultList(List<BasketballCardModel> resultList) {
        this.resultList = resultList;
    }
}
