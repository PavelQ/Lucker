package ru.qupol;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by Pavel on 26.07.2015.
 */
public class Game {
    List<Player> players;
    int bet;

    Player player1;
    Player player2;

    public Game( Player player1, int bet) {
        this.bet = bet;
        this.player1 = player1;
    }

    public   void  play(){


            Scanner scanner = new Scanner(System.in);
            System.out.print("ставка:");
            int bet = scanner.nextInt();
           // scanner.close();
            player1.setBallance(player1.getBallance() - bet);
            player2.setBallance(player2.getBallance() - bet);
            int winner = getWinner(2) + 1;
            if (winner == 1) {
                player1.setBallance(player1.getBallance() + bet * 2);
            } else {
                player2.setBallance(player2.getBallance() + bet * 2);
            }
            StringBuilder builder;
            builder = new StringBuilder();
            builder.append("игрок ").append(player1.getName()).append(" имеет баланс ").append(player1.getBallance());
            System.out.println(builder.toString());

            builder = new StringBuilder();
            builder.append("игрок ").append(player2.getName()).append(" имеет баланс ").append(player2.getBallance());
            System.out.println(builder.toString());

    }

    private  int getWinner(int count){
        Random random = new Random();
        return random.nextInt(count);
    }

    public void join(Player player) {
        setPlayer2(player);
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }
}
