package application;

import java.util.ArrayList;
import java.util.List;

/**
 * This class acts as the Memento Caretaker
 */

public class BallCaretaker {
    private List<Memento> mementoList = new ArrayList<Memento>();

    /**
     * Add memento state to list
     * @param state
     */

    public void add(Memento state){
        mementoList.add(state);
    }

    /**
     * Retrive a memento state from list
     * @param index
     * @return
     */
    public Memento get(int index){
        return mementoList.get(index);
    }
}
