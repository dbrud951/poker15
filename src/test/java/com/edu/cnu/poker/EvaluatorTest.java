package com.edu.cnu.poker;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by cse on 2017-04-17.
 */
public class EvaluatorTest {

    @Test
    public void SUIT가_5개가동일하고_RANK_5개가_1_10_11_12_13이면_로얄스트레이트플러쉬이다() {
        Evaluator evaluator = new Evaluator();
        List<Card> cardList = Arrays.asList(
                new Card(1,Suit.CLUBS),
                new Card(10,Suit.CLUBS),
                new Card(11,Suit.CLUBS),
                new Card(12,Suit.CLUBS),
                new Card(13,Suit.CLUBS)
        );
        Ranking result = evaluator.evaluate(cardList);
        assertThat(result, is(Ranking.RoyalStraightFlush));
    }

    @Test
    public void RANK가_2개_3개가_동일하면_풀하우스다() {
        Evaluator evaluator = new Evaluator();
        List<Card> cardList = Arrays.asList(
                new Card(2,Suit.DIAMONDS),
                new Card(2,Suit.HEARTS),
                new Card(3,Suit.DIAMONDS),
                new Card(3,Suit.CLUBS),
                new Card(3,Suit.DIAMONDS)
        );
        Ranking result = evaluator.evaluate(cardList);
        assertThat(result, is(Ranking.FullHouse));
    }

    @Test
    public void SUIT가_5개가동일하면_플러쉬다() {
        Evaluator evaluator = new Evaluator();
        List<Card> cardList = Arrays.asList(
                new Card(1,Suit.CLUBS),
                new Card(4,Suit.CLUBS),
                new Card(8,Suit.CLUBS),
                new Card(13,Suit.CLUBS),
                new Card(2,Suit.CLUBS)
        );
        Ranking result = evaluator.evaluate(cardList);
        assertThat(result, is(Ranking.Flush));
    }

    @Test
    public void RANK_5개가_1_10_11_12_13이면_마운틴이다() {
        Evaluator evaluator = new Evaluator();
        List<Card> cardList = Arrays.asList(
                new Card(1,Suit.DIAMONDS),
                new Card(10,Suit.HEARTS),
                new Card(11,Suit.DIAMONDS),
                new Card(12,Suit.CLUBS),
                new Card(13,Suit.HEARTS)
        );
        Ranking result = evaluator.evaluate(cardList);
        assertThat(result, is(Ranking.Mountian));
    }
    @Test
    public void RANK가_연속적인_숫자이면_스트레이트다() {
        Evaluator evaluator = new Evaluator();
        List<Card> cardList = Arrays.asList(
                new Card(5,Suit.DIAMONDS),
                new Card(4,Suit.HEARTS),
                new Card(3,Suit.DIAMONDS),
                new Card(6,Suit.CLUBS),
                new Card(2,Suit.DIAMONDS)
        );
        Ranking result = evaluator.evaluate(cardList);
        assertThat(result, is(Ranking.Straight));
    }
    @Test
    public void RANK가_A로_시작하는_연속적인_숫자이면_백스트레이트다() {
        Evaluator evaluator = new Evaluator();
        List<Card> cardList = Arrays.asList(
                new Card(5,Suit.DIAMONDS),
                new Card(4,Suit.HEARTS),
                new Card(3,Suit.DIAMONDS),
                new Card(1,Suit.CLUBS),
                new Card(2,Suit.DIAMONDS)
        );
        Ranking result = evaluator.evaluate(cardList);
        assertThat(result, is(Ranking.BackStraight));
    }

    @Test
    public void Rank_2개가_동일한게_두쌍이면_투페어다() {
        Evaluator evaluator = new Evaluator();
        List<Card> cardList = Arrays.asList(
                new Card(4,Suit.CLUBS),
                new Card(4,Suit.DIAMONDS),
                new Card(13,Suit.SPADES),
                new Card(13,Suit.HEARTS),
                new Card(2,Suit.CLUBS)
        );
        Ranking result = evaluator.evaluate(cardList);
        assertThat(result, is(Ranking.TwoPairs));
    }

    @Test
    public void Rank_2개가_동일한게_한쌍이면_원페어다() {
        Evaluator evaluator = new Evaluator();
        List<Card> cardList = Arrays.asList(
                new Card(4,Suit.CLUBS),
                new Card(4,Suit.DIAMONDS),
                new Card(8,Suit.SPADES),
                new Card(13,Suit.HEARTS),
                new Card(2,Suit.CLUBS)
        );
        Ranking result = evaluator.evaluate(cardList);
        assertThat(result, is(Ranking.OnePair));
    }




}