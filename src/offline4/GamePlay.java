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
public class GamePlay {
    
    public static State maxMove(State state, int selectedBin){
        
                State ret;
                
                int freeTurn = state.additionalTurns;
                int captured = state.captured;
                int turn = 1; //the next turn is supposed to be opponent's
        
                int b1[] = new int[7];
                int b2[] = new int[7];
                
                for(int j=0;j<7;j++){
                    b1[j] = state.board1[j];
                    b2[j] = state.board2[j];
                }
                
                //the bin isn't empty
                if(b1[selectedBin] != 0){
                    int overflowThreshold = 6 - selectedBin;
//                    System.out.println("overflowThresh: "+overflowThreshold+
//                            "selectedBin: "+selectedBin);
                    
                    //if there are more than overflowThreshold stones in the bin
                    //the rest of the stones(after transfering 1 stone to each bin and the storage)
                    //will be distributed among bins belonging to the opponent
                    if(b1[selectedBin] > overflowThreshold){
                        
                        int stones = b1[selectedBin];
                        b1[selectedBin] = 0;
                        int whichBoard;
                        
                        //distribute the stones among max player's bins
                        for(int j = selectedBin+1;j<7;j++){          
                            b1[j]++;
                        }
                        stones -= overflowThreshold;
                        
                        //1.then distribute the rest of the stones among my opponent's bins
                        //2.if there are stones left after doing the aforementioned thing
                        //distribute stones among my bins
                        //repeat the cycle(1 and 2) if there are more stones
                        
                        //whichBoard == 1 means, stones are gonna be distributed among
                        //opponent's bins
                        whichBoard = 1;
                        while(stones>0){
                            
                            if(whichBoard == 1){
                                
                                if(stones>6){
                                    
                                    for(int k=0;k<6;k++){
                                        b2[k]++;
                                    }
                                    stones -= 6;
                                }
                                else{
                                    for(int k=0;k<stones;k++)
                                        b2[k]++;
                                    stones = 0;
                                }
                                
                                whichBoard = 0;
                            }
                            else{
                                
                                if(stones>7){
                
                                    for(int k=0;k<7;k++){
                                        b1[k]++;
                                    }
                                    stones -= 7;
                                }
                                else if(stones<7){
                                    
                                    int k;
                                    for(k=0;k<stones-1;k++){
                                        b1[k]++;
                                    }
                                    
                                    //if the last stone falls into an empty bin
                                    if(b1[k] == 0){
                                        captured+= (b2[k] + 1); //captured
                                        b1[6] += (b2[k] + 1);
                                        b2[k] = 0;
                                    }
                                    else{
                                        b1[k]++;
                                    }
                                    stones = 0;
                                }
                                //if there are only 7 stones left
                                //and the stones are to be distributed among
                                //max player's bins, then the last stone will fall into
                                //max player's storage, hence the next turn would be 
                                //max player's
                                else{
                                    
                                    for(int i=0;i<7;i++){
                                        b1[i]++;
                                    }
                                    turn = 0;
                                    freeTurn++; //free turn
                                    stones -= 7;
                                }
                                
                                whichBoard = 1;
                                
                            }
                        }        
                    }
                    //there aren't enough stones to reach the storage
                    else if(b1[selectedBin] < overflowThreshold){
                        
                        int stones = b1[selectedBin];
                        b1[selectedBin] = 0;
                        
//                        System.out.println("b2[selectedBin]: "+b2[selectedBin]);
//                        System.out.println("selectedBin+1 to selectedBin+stones: "+
//                                (selectedBin+1)+" "+(selectedBin+stones));
                        int j;
                        for(j=selectedBin+1;j<selectedBin+stones;j++){
                            b1[j]++;
                        }
                        
                        //the last stone falls into an empty bin
                        //stones from opponent's corresponding bins are captured
                        if(b1[j] == 0){
                            captured += (b2[j] + 1); //captured
                            b1[6] += (b2[j] + 1);
                            b2[j] = 0;
                        }
                        else
                            b1[j]++;
                    }
                    
                    //the last stone falls into max player's storage
                    else{
                        
                        b1[selectedBin] = 0;
                        for(int j=selectedBin+1;j<7;j++)
                            b1[j]++;
                        turn = 0;
                        freeTurn++; //free turn
                    }
                }
        
                ret = new State(b1,b2,freeTurn, captured, turn);
                return ret;
    }
    
    public static State minMove(State state, int selectedBin){
        
                State ret;
                
                int freeTurn = state.additionalTurns;
                int captured = state.captured;
                int turn = 0; //the next turn is supposed to be opponent's
        
                int b1[] = new int[7];
                int b2[] = new int[7];
                
                for(int j=0;j<7;j++){
                    b1[j] = state.board1[j];
                    b2[j] = state.board2[j];
                }
                
                //the bin isn't empty
                if(b2[selectedBin] != 0){
                    int overflowThreshold = 6 - selectedBin;
                    
                    //if there are more than overflowThreshold stones in the bin
                    //the rest of the stones(after transfering 1 stone to each bin and the storage)
                    //will be distributed among bins belonging to the max player
                    if(b2[selectedBin] > overflowThreshold){
                        
                        int stones = b2[selectedBin];
                        b2[selectedBin] = 0;
                        int whichBoard;
                        
                        //distribute the stones among min player's bins
                        for(int j = selectedBin+1;j<7;j++){          
                            b2[j]++;
                        }
                        stones -= overflowThreshold;
                        
                        //1.then distribute the rest of the stones among my opponent's bins
                        //2.if there are stones left after doing the aforementioned thing
                        //distribute stones among my bins
                        //repeat the cycle(1 and 2) if there are more stones
                        
                        //whichBoard == 0 means, stones are gonna be distributed among
                        //max player's bins
                        whichBoard = 0;
                        while(stones>0){
                            
                            if(whichBoard == 0){
                                
                                if(stones>6){

                                    for(int k=0;k<6;k++){
                                        b1[k]++;
                                    }
                                    stones -= 6;
                                }
                                else{
                                    for(int k=0;k<stones;k++)
                                        b1[k]++;
                                    stones = 0;
                                }
                                
                                whichBoard = 1;
                            }
                            else{
                                
                                if(stones>7){
                                    
                                    for(int k=0;k<7;k++){
                                        b2[k]++;
                                    }
                                    stones -= 7;
                                }
                                else if(stones<7){
                                    
                                    
                                    int k;
                                    for(k=0;k<stones-1;k++){
                                        b2[k]++;
                                    }
                                    
                                    //if the last stone falls into an empty bin
                                    if(b2[k] == 0){
                                        captured -= (b1[k] + 1);
                                        b2[6] += (b1[k] + 1);
                                        b1[k] = 0;
                                    }
                                    else{
                                        b2[k]++;
                                    }
                                    
                                    stones = 0;
                                }
                                //if there are only 7 stones left
                                //and the stones are to be distributed among
                                //min player's bins, then the last stone will fall into
                                //min player's storage, hence the next turn would be 
                                //min player's
                                else{
                                    
                                    for(int i=0;i<7;i++){
                                        b2[i]++;
                                    }
                                    turn = 1;
                                    freeTurn--;
                                    stones -= 7;
                                }
                                
                                whichBoard = 0;
                                
                            }
                        }        
                    }
                    
                    //there aren't enough stones to reach the storage
                    else if(b2[selectedBin] < overflowThreshold){
                        
                        int stones = b2[selectedBin];
                        b2[selectedBin] = 0;
                        
                        int j;
                        for(j=selectedBin+1;j<selectedBin+stones;j++){
                            b2[j]++;
                        }
                        
                        //the last stone falls into an empty bin
                        //stones from opponent's corresponding bins are captured
                        if(b2[j] == 0){
                            captured -= (b1[j] + 1); //captured
                            b2[6] += (b1[j] + 1);
                            b1[j] = 0;
                        }
                        else
                            b2[j]++;
                    }
                    
                    //the last stone falls into min player's storage
                    else{
                        b2[selectedBin] = 0;
                        for(int j=selectedBin+1;j<7;j++)
                            b2[j]++;
                        turn = 1;
                        freeTurn--;
                    }
                }
        
                ret = new State(b1,b2,freeTurn, captured,turn);
                return ret;
    }
    
}
