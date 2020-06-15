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
public class Heuristics {
    
    public static int heuristic1(State state){
        
        return (state.board1[6] - state.board2[6]);
    }
    
    public static int heuristic2(State state){
        
        int max=0,min=0;
        for(int i=0;i<6;i++){
            max += state.board1[i];
            min += state.board2[i];
        }
        
        return 2*(state.board1[6]-state.board2[6]) + 3*(max-min);
    }
    public static int heuristic3(State state){
        
        int max=0,min=0;
        for(int i=0;i<6;i++){
            max += state.board1[i];
            min += state.board2[i];
        }
        
        return 3*(state.board1[6]- state.board2[6]) + 4*(max-min) + 5*state.additionalTurns;
    }
    public static int heuristic4(State state){
        
        return heuristic3(state) + 7*state.captured;
    }
}
