package io.github.nevemlaci;

import java.util.Map;

public class GregtechVoltages {
    public static String[] voltages = new String[]{"LV", "MV", "HV", "EV", "IV"};
    private static Map<String, Integer> voltageMaximums = Map.of(
            "LV", 32,
            "MV", 128,
            "HV", 512,
            "EV", 2048,
            "IV", 8192
    );

    public static int getVoltageMaximum(String voltage) {
        return voltageMaximums.get(voltage);
    }
}
