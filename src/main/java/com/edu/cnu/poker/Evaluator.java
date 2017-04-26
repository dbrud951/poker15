package com.edu.cnu.poker;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cse on 2017-04-17.
 */
public class Evaluator {
    public Ranking evaluate(List<Card> cardList) {

        Map<Suit, Integer> tempMap = new HashMap<Suit, Integer>();
        Map<Integer,Integer> tempMap2 = new HashMap<Integer, Integer>();

        for (Card card : cardList) {
            if (tempMap.containsKey(card.getSuit())) {
                Integer count = tempMap.get(card.getSuit());
                count = new Integer(count.intValue() + 1);
                tempMap.put(card.getSuit(), count);
            } else {
                tempMap.put(card.getSuit(), new Integer(1));
            }

            if(tempMap2.containsKey(card.getRank())){
                Integer count = tempMap2.get(card.getRank());
                count = new Integer(count.intValue() + 1);
                tempMap2.put(card.getRank(),count);
            }else{
                tempMap2.put(card.getRank(),new Integer(1));
            }

        }

        for(Integer key2 : tempMap2.keySet()) {
            for (Suit key : tempMap.keySet()) {
                if (tempMap.get(key) == 5) {
                    if (tempMap2.containsKey(1) && tempMap2.containsKey(10) && tempMap2.containsKey(10) && tempMap2.containsKey(12) && tempMap2.containsKey(13)) {
                        return Ranking.RoyalStraightFlush;
                    }
                }
            }
        } // 로얄스트레이트플러쉬

        for (Suit key : tempMap.keySet()) {
            if (tempMap.get(key) == 5) {
                return Ranking.Flush;
            }
        } // 플러쉬

        for(Integer key : tempMap2.keySet()) {
            if (tempMap2.containsKey(1) && tempMap2.containsKey(10) && tempMap2.containsKey(11) && tempMap2.containsKey(12) && tempMap2.containsKey(13)) {
                return Ranking.Mountian;
            }
        } // 마운틴

        int sameCard = 0; //카드 한쌍(숫자 같은거) 갯수 세는거
        for(Integer key : tempMap2.keySet()){
            if(tempMap2.get(key) == 2){
                sameCard++;
            }
        }

        if(sameCard == 1){
            return Ranking.OnePair;
        }else if(sameCard == 2){
            return Ranking.TwoPairs;
        }

        return Ranking.Nothing;
    }
}
