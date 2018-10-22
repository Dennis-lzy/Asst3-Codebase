package application;

import java.util.ArrayList;
import java.util.List;

public class Memento {
    private List<Ball> state = new ArrayList<>();

    public Memento(List<Ball> stateToSave) {
        state = stateToSave;
    }

    // accessible by outer class only
    public List<Ball> getSavedState() {
        return state;
    }
}
