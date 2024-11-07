package dungeonmania.entities.logicSwitches.LogicalStrategies;

public class LogicalStrategyFactory {
    public static LogicalStrategy createLogicalStrategy(String rule) {
        switch (rule) {
        case "and":
            return new AndStrategy();
        case "or":
            return new OrStrategy();
        case "xor":
            return new XorStrategy();
        case "co_and":
            return new CoAndStrategy();
        default:
            throw new IllegalArgumentException("Invalid logical rule: " + rule);
        }
    }
}
