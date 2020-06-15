/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package offline4;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 *
 * @author Asus
 */
public class Offline4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {

        State currentState = new State();
        
        int win;
        Random rand = new Random();
        //for(int depth = 2;depth<8;depth++){
            
            //System.out.println("\n\n\nDepth :"+depth);
            for(int i=1;i<=4;i++){
                for(int j=1;j<=4;j++){
                    currentState = new State();
                    win = 0;
                    
                    for(int g=0,depth=2;g<100;g++,depth++){


                        //System.out.println(maxPlayerDepth+" "+minPlayerDepth);
                        while(!Algo.ifTerminalState(currentState)){
                            minimaxReturn bestMove;

                            //run the minimax algorithm to figure out the best move
                            if(currentState.turn==0)
                                bestMove = Algo.alphaBeta(currentState, depth, -10000007, 10000007,i);
                            else
                                bestMove = Algo.alphaBeta(currentState, depth, -10000007, 10000007,j);

                            //bestMove.binselected is the best move at this stage
                            if(currentState.turn ==0)
                                currentState = GamePlay.maxMove(currentState, bestMove.binSelected);
                            else
                                currentState = GamePlay.minMove(currentState, bestMove.binSelected);


                        }

                        //we have reached the terminal state
                        for(int k=0;k<6;k++){
                            currentState.board1[6] += currentState.board1[k];
                            currentState.board2[6] += currentState.board2[k];
                        }

            //            System.out.println("Max player's storage: "+currentState.board1[6]);
            //            System.out.println("Min Players's storage: "+currentState.board2[6]);
                        //System.out.println("Heuristic "+i+" vs Heuristic "+j);
                        if(currentState.board1[6]>currentState.board2[6]){
                            win++;
                           // System.out.println("Max player wins");
                        }
    //                    else
    //                        System.out.println("Min player wins");
                        
                    }
                    System.out.println("Heuristic "+i+" vs Heuristic "+j);
                    System.out.println("Max win percentage: "+win);

                }
            }
  //      }
  
            System.out.println("\n\n\n\n\n\nAfter reordering: ");
            for(int i=1;i<=4;i++){
                for(int j=1;j<=4;j++){
                    currentState = new State();
                    win = 0;
                    
                    for(int g=0,depth=2;g<100;g++,depth++){


                        //System.out.println(maxPlayerDepth+" "+minPlayerDepth);
                        while(!Algo.ifTerminalState(currentState)){
                            minimaxReturn bestMove;

                            //run the minimax algorithm to figure out the best move
                            if(currentState.turn==0)
                                bestMove = Algo.alphaBeta(currentState, depth, -10000007, 10000007,i);
                            else
                                bestMove = Algo.alphaBeta(currentState, depth, -10000007, 10000007,j);

                            //bestMove.binselected is the best move at this stage
                            if(currentState.turn ==0)
                                currentState = GamePlay.maxMove(currentState, bestMove.binSelected);
                            else
                                currentState = GamePlay.minMove(currentState, bestMove.binSelected);


                        }

                        //we have reached the terminal state
                        for(int k=0;k<6;k++){
                            currentState.board1[6] += currentState.board1[k];
                            currentState.board2[6] += currentState.board2[k];
                        }

            //            System.out.println("Max player's storage: "+currentState.board1[6]);
            //            System.out.println("Min Players's storage: "+currentState.board2[6]);
                        //System.out.println("Heuristic "+i+" vs Heuristic "+j);
                        if(currentState.board1[6]>currentState.board2[6]){
                            win++;
                           // System.out.println("Max player wins");
                        }
    //                    else
    //                        System.out.println("Min player wins");
                        
                    }
                    System.out.println("Heuristic "+i+" vs Heuristic "+j);
                    System.out.println("Max win percentage: "+win);

                }
            }

    }
    
    
    
}
