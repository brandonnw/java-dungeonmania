package dungeonmania.entities.logicSwitches;

public interface Conductor {
    public boolean isActivated();

    public void activateAdjacentSubscribers(Switch s);

    public void deactivate(Switch s);

    public void deactivateAdjacentSubscribers(Switch s);

    public void subscribe(Wire wire);

    public boolean justActivated();

    public void resetJustActivated();

}
