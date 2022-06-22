package ultra.lich.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;

public class RunnableGameAction extends AbstractGameAction {

    private Runnable runnable;

    public RunnableGameAction(Runnable runnable) {
        this.runnable = runnable;
    }

    public void update() {
        this.runnable.run();
        isDone = true;
    }
}