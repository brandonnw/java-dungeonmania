package dungeonmania.entities.logicSwitches;

public interface Conductor {
    public boolean isActivated();

    public void turnOff();

    public void turnOn();

    public void addSubscriber(Conductor conductor);

    public boolean justActivated();

    public void updateJustActivated();
}
