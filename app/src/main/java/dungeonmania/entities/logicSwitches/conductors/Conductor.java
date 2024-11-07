package dungeonmania.entities.logicSwitches.conductors;

public interface Conductor {
    public boolean isActivated();

    public void activateAdjacentSubscribers(Switch s);

    public void deactivate(Switch s);

    public void deactivateAdjacentSubscribers(Switch s);

    public boolean getJustActivated();

    public void resetJustActivated();

    public void subscribeAdjacentWire(Wire w);

}
