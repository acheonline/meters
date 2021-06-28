package ru.achernyavskiy0n.util;

import lombok.experimental.UtilityClass;
import ru.achernyavskiy0n.model.Meter;

@UtilityClass
public class TestData {

    public static final Meter METER_1 = Meter.builder()
            .objectId(90)
            .sensorId(901)
            .time(902000111L)
            .value(9.0)
            .build();

    public static final Meter METER_2 = Meter.builder()
            .objectId(90)
            .sensorId(801)
            .time(802000111L)
            .value(8.0)
            .build();
}
