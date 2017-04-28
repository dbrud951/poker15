package com.edu.cnu.poker;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cse on 2017-04-17.
 */
public class Hand {

    private Deck deck;
    private PokerType pokerType;
    private List<Card> cardList;

    public Hand(Deck deck, PokerType pokerType) {
        this.deck = deck;
        this.pokerType = pokerType;
        cardList = new ArrayList<Card>();
        for (int i = 0; i < pokerType.getNumberOfCard(); i++) {
            cardList.add(deck.drawCard());
        }
    }

    public int getTotalCard() {
        return cardList.size();
    }

    public Card getCard(){
        if(cardList.size()!=0){
            return cardList.remove(0);
        }else{
            System.out.println("더 이상 카드가 없습니다.");
            return null;
        }
    }

    public Card showCard(int i){
        return cardList.get(i);
    }

    public List<Card> getCardList(){
        return cardList;
    }

}
