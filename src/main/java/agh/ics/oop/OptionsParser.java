package agh.ics.oop;

import java.util.Arrays;

public class OptionsParser {
    public MoveDirection[] parse(String[] instructions) {

        String[] parsed = Arrays.stream(instructions).filter(OptionsParser::IsValidDirectionString).toArray(String[]::new);
        return Arrays.stream(parsed).map(OptionsParser::StringToDirection).toArray(MoveDirection[]::new);
    }

    private static MoveDirection StringToDirection(String string) {

        return switch (string)
                {
                    case "l", "left" -> MoveDirection.LEFT;
                    case "b", "backward" -> MoveDirection.BACKWARD;
                    case "r", "right" -> MoveDirection.RIGHT;
                    default -> MoveDirection.FORWARD;
                };
    }

    private static boolean IsValidDirectionString(String string) {

        return string.equals("f") || string.equals("forward") ||
                string.equals("b") || string.equals("backward") ||
                string.equals("l") || string.equals("left") ||
                string.equals("r") || string.equals("right");
    }
}