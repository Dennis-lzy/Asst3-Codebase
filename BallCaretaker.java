package application;

import java.util.List;

public class BallCaretaker {
    private List<Object> objectList;
    private Object obj;

    public void save(Ball ball){
        this.obj=ball.save();
//        objectList.add(obj);
    }

    public void undo(Ball ball){
        ball.undoToLastSave(obj);
    }
}
