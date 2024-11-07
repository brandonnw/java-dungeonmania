package dungeonmania.entities.logicSwitches;

public interface Conductor {
    public boolean isActivated();

    public void deactivate(Switch s);

    public void subscribe(Wire wire);

    public boolean justActivated();

    public void resetJustActivated();

    public void activateAdjacentSubscriber(Switch s);

    public void deactivateAdjacentSubscriber(Switch s);
}
