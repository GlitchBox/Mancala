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
public class Algo {
    
        //returns move
        //this method uses 
       public static minimaxReturn alphaBeta(State state, int depth, int alpha, int beta, int h){
        int retVal;
        
        State currentState = new State(state.board1,state.board2, state.turn);
        minimaxReturn returnState = new minimaxReturn(-100000,-100000);
        
        if(depth == 0 || ifTerminalState(currentState)){
            
            /*if(ifTerminalState(currentState)){
                for(int i=0;i<6;i++){
                    currentState.board1[6] += currentState.board1[i];
                    currentState.board2[6] += currentState.board2[i];
                }
            }*/
            
            if(h==1)
                return new minimaxReturn(-1,Heuristics.heuristic1(currentState));
            else if(h==2)
                return new minimaxReturn(-1,Heuristics.heuristic2(currentState));
            else if(h==3)
                return new minimaxReturn(-1,Heuristics.heuristic3(currentState));
            
            return new minimaxReturn(-1,Heuristics.heuristic4(currentState));
                
        }
        
        
        //if maximizing player
        if(currentState.turn == 0){
            retVal = -10000007;
            
            //consider every possible move(6 moves)
            for(int i=0;i<6;i++){
                
                State nextMove;
                if(currentState.board1[i] != 0){
                    nextMove = GamePlay.maxMove(currentState, i);
                    int temp = alphaBeta(nextMove,depth-1, alpha, beta,h).hueristicValue;
                    
                    if(temp>retVal){
                        retVal = temp;
                        returnState.binSelected = i;
                        returnState.hueristicValue = retVal;
                    }
                }
                if(retVal>=beta){
                
                    return returnState;
                }    
                alpha = Math.max(retVal, alpha);
            }
        }
        
        //if minimizing player
        else{
            retVal = 10000007;
            
            for(int i=0;i<6;i++){
                
                State nextMove;
                if(currentState.board2[i] != 0){
                    nextMove= GamePlay.minMove(currentState, i);
                    int temp = alphaBeta(nextMove,depth-1, alpha, beta,h).hueristicValue;
                    
                    if(temp<retVal){
                        retVal = temp;
                        returnState.binSelected = i;
                        returnState.hueristicValue = retVal;
                    }
                }
                if(retVal<=alpha){
                    return returnState;
                }    
                beta = Math.min(retVal, beta);
            }
        }
        
        return returnState;
    }
    
    public static boolean ifTerminalState(State state){
        
        boolean ret = false;
        
        //if any of the player has empty bins(exclusive of the storage), then it is a terminal state
        int stonesInBins=0;
        for(int i=0;i<6;i++){
               if(state.board1[i] == 0)
                   stonesInBins++;
        }
        
        if(stonesInBins == 6)
            return true;
        
        stonesInBins=0;
        for(int i=0;i<6;i++){
               if(state.board2[i] == 0)
                   stonesInBins++;
        }
        
        if(stonesInBins == 6)
            return true;
        
        return ret;
    }
    
}
