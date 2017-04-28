package com.edu.cnu.poker;

import java.util.List;
import java.util.Scanner;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.isA;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by cse on 2017-04-17.
 * CARD - rank, suit
 * DECK
 * HAND
 * EVALUATOR
 * PLAYER
 * GAME
 */
public class PokerApplication {

    public static void main(String[] args) {
        int money = 100;
        Scanner sc = new Scanner(System.in);
        int bet;
        int whoIsWin;


        System.out.println("Welcome to the game of Poker.");

        while(true){
            System.out.println("You have "+money+" dollars.");
            System.out.println("How many dollars do you want to bet?  (Enter 0 to end.)");
            do{
                bet = sc.nextInt();
                if(bet<0 || bet> money){
                    System.out.println("Your answer must be between 0 and " + money + ".");
                }
            }while(bet<0 || bet > money);
            if (bet == 0){
                break;
            }
            whoIsWin = playPoker();
            if(whoIsWin == 1){
                System.out.println("You got Money!");
                money = money + bet;
            }else if(whoIsWin == 2){
                System.out.println("You lose Money. Thanks!");
                money = money - bet;
            }else if(whoIsWin == 3){
                System.out.println("You push with Dealer.");
            }else{
                System.out.println("Die.");
            }
            if(money == 0){
                System.out.println("Looks like you've are out of money!");
                break;
            }
        }

        System.out.println("You leave with "+money+".");
        System.out.println("Good Bye!");


    }//main의 끝

    static int playPoker(){

        Deck deck = new Deck(1);
        Hand userHand = new Hand(deck, PokerType.FIVE);
        Hand dealerHand = new Hand(deck, PokerType.FIVE);
        Evaluator evaluator = new Evaluator();
        String userResult;
        String dealerResult;
        List<Card> userList = userHand.getCardList();
        List<Card> dealerList = dealerHand.getCardList();

        System.out.println(" ");

        int userPoint;
        int dealerPoint;

        userResult = evaluator.evaluate(userHand.getCardList()).toString();
        dealerResult = evaluator.evaluate(dealerHand.getCardList()).toString();

        Scanner sc = new Scanner(System.in);

        while(true) {
            if(userHand.getTotalCard() == 2){
                System.out.println("You can't open no more cards.");
            }else{
                System.out.print("Your cards are : ");
                Card userCard = userHand.getCard();
                System.out.println(userCard.getSuit() + " " + userCard.getRank() + " ");
                System.out.print("Dealer is showing the : ");
                Card dealerCard = dealerHand.getCard();
                System.out.println(dealerCard.getSuit() + " " + dealerCard.getRank() + " ");
            }
            System.out.println(" O(Open) / G(Go) / D(Die) ? ");
            char a;
            do{
                a = sc.nextLine().charAt(0);
                a = Character.toUpperCase(a);
                if(a != 'O' && a != 'G' && a != 'D'){
                    System.out.println("Please respond O or G or D");
                }
            }while(a != 'O' && a != 'G' && a != 'D');

            if(a == 'O'){//open
                System.out.println("Another Card is Opened...");
            }else if(a == 'G'){//go

                System.out.print("Your Card : ");
                for(int i=0;i<userList.size();i++){
                    System.out.print(userList.get(i).getSuit()+" "+userList.get(i).getRank()+" / ");
                }

                System.out.println(" ");

                System.out.print("Dealer's Card : ");
                for(int i=0;i<dealerList.size();i++){
                    System.out.print(dealerList.get(i).getSuit()+" "+dealerList.get(i).getRank()+" / ");
                }

                System.out.println(" ");

                userPoint = rankPoker(userResult);
                dealerPoint = rankPoker(dealerResult);
                System.out.println(" You : "+userResult + "\n Dealer : "+dealerResult);
                if(userPoint > dealerPoint){
                    System.out.println("You win!");
                    return 1;
                }else if(userPoint < dealerPoint){
                    System.out.println("You Lose!");
                    return 2;
                }else{
//                    System.out.println("Push.");
                    return 3;
                }
            }else{//die
                return 4;
            }


        }

    }//playPoker의 끝

    static int rankPoker(String ranking){
        if(ranking.equals("OnePair")){
            return 1;
        }else if(ranking.equals("TwoPairs")){
            return 2;
        }else if(ranking.equals("ThreeOfaKind")){
            return 3;
        }else if(ranking.equals("Straight")){
            return 4;
        }
        else if(ranking.equals("BackStraight")){
            return 5;
        }
        else if(ranking.equals("Mountian")){
            return 6;
        }
        else if(ranking.equals("Flush")){
            return 7;
        }
        else if(ranking.equals("FullHouse")){
            return 8;
        }else if(ranking.equals("FourOfaKind")){
            return 9;
        }else if(ranking.equals("StraightFlush")){
            return 10;
        }
        else if(ranking.equals("BackStraightFlush")){
            return 11;
        }
        else if(ranking.equals("RoyalStraightFlush")){
            return 12;
        }else{
            return 0;
        }

//        assertThat(ranking,is(Ranking.OnePair));

    }


}
