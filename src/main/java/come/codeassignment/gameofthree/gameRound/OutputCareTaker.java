package come.codeassignment.gameofthree.gameRound;

import java.util.ArrayList;
import java.util.List;

public class OutputCareTaker {
    List<OutputNumberMem> outputNumberMemList = new ArrayList<>();

    /**
     * Add output number to the list
     * @param state
     */
    public void add(OutputNumberMem state){
        outputNumberMemList.add(state);
    }

    /**
     * Get the output number by index
     * @param index
     * @return
     */
    public OutputNumberMem get(int index){
        return outputNumberMemList.get(index);
    }

    /**
     * Get the last element in the list
     * @return
     */
    public OutputNumberMem getLast(){
        return outputNumberMemList.get(outputNumberMemList.size() - 1);
    }
}
