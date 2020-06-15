/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package offline4;

/**
 *
 * @author Asus
 */
public class State {
    
    int board1[];
    int board2[];
    int additionalTurns;
    int captured;
    int turn;
    
    public State(){
        
        this.board1 = new int[7];
        this.board2 = new int[7];
        this.additionalTurns = 0;
        this.captured = 0;
        this.turn = 0; //it's the max's player's move by default
        
        for(int i=0;i<6;i++){
            this.board1[i] = 4;
            this.board2[i] = 4;
        }
        
        this.board1[6] = 0;
        this.board2[6] = 0;
    }
    
    public State(int b1[], int b2[], int t){
        this.board1 = new int[7];
        this.board2 = new int[7];
        this.turn = t;
        
        for(int i=0;i<7;i++){
            this.board1[i] = b1[i];
            this.board2[i] = b2[i];
        }
    }
    
    public State(int b1[], int b2[], int at, int cap, int t){
        this.board1 = new int[7];
        this.board2 = new int[7];
        this.additionalTurns = at;
        this.captured = cap;
        this.turn = t;
        
        for(int i=0;i<7;i++){
            this.board1[i] = b1[i];
            this.board2[i] = b2[i];
        }
    }
    
}
