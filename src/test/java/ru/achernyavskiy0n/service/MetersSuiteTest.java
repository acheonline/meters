package ru.achernyavskiy0n.service;


import lombok.SneakyThrows;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;
import ru.achernyavskiy0n.config.MetersConfiguration;
import ru.achernyavskiy0n.model.Meter;
import ru.achernyavskiy0n.util.JSONUtil;

import java.util.List;

import static ru.achernyavskiy0n.util.TestData.METER_1;
import static ru.achernyavskiy0n.util.TestData.METER_2;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MetersConfiguration.class})
public class MetersSuiteTest {

    @Autowired
    MeterService service;

    private static String jsonMeters;

    @BeforeClass
    public static void clean() {
    }

    @BeforeClass
    public static void init() {
        jsonMeters = JSONUtil.readFileAsString("src/test/resources/test_meters.json");
    }

    @Test
    public void addAllMetersToRepositoryAndGetThemAllCorrectly()  {
        int currentSize = service.getAll().size();
        service.addAll(jsonMeters);
        List<Meter> list = service.getAll();
        Assert.isTrue(list.size() == (currentSize + 2), "array size is " + list.size());
    }

    @Test
    public void getAverageMeterValueCorrect() {
        service.save(METER_1);
        service.save(METER_2);
        Assert.isTrue(service.getAverageMeterValue(METER_1.getObjectId()) != null, "no average data for objectId");
        Assert.isTrue(8.5 == service.getAverageMeterValue(METER_1.getObjectId()));
    }
}
