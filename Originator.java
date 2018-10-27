package application;

import java.util.ArrayList;
import java.util.List;

public class Originator {

    private List<Ball> state = new ArrayList<>();

    public void setState(List<Ball> state) {
        this.state = state;
        System.out.println("Originator: Setting state to " + state);
    }

    public List<Ball> getState(){
        return state;
    }

    public Memento saveToMemento() {
        System.out.println("Originator: Saving to Memento.");
        return new Memento(state);
    }

    public void restoreFromMemento(Memento memento) {
        state = memento.getSavedState();
        System.out.println("Originator: State after restoring from Memento: " + state);

    }
}
