package ru.achernyavskiy0n.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.achernyavskiy0n.model.Meter;
import ru.achernyavskiy0n.repository.MeterRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class MeterService {

    @Autowired
    MeterRepository repository;

    @SneakyThrows
    public void addAll(String meters) {
        log.info("Saving all from JSON document: {}", meters);
        List<Meter> meterJsonList = new ObjectMapper().readValue(meters, new TypeReference<List<Meter>>() {
        });
        log.info("Parsed JSON document to Object List: {} and almost saved", meterJsonList);
        repository.saveAll(meterJsonList);
    }

    public List<Meter> getAll() {
        log.info("Almost got all meter data as a list from data storage");
        return new ArrayList<>((Collection<? extends Meter>) repository.findAll());
    }

    public Double getAverageMeterValue(@NonNull Integer objectId) {
        log.info("Getting average meter data storage with objectId: {}", objectId);
        List<Meter> list = (List<Meter>) repository.findAll();
        log.info("got a list from data: {}", list);
        return list.stream()
                .filter(meter -> meter.getObjectId().equals(objectId))
                .mapToDouble(Meter::getValue)
                .average()
                .orElse(Double.NaN);
    }

    public List<Meter> getAllMeterMeasuresInTimeBoundsBySensorIdAndObjectId(@NonNull Integer sensorId,
                                                                            @NonNull Integer objectId,
                                                                            @NonNull Long fromTime,
                                                                            @NonNull Long toTime) {
        log.info("Getting all meters statistics in time bounds - from {} and to {}, and filtered by sensorId {} and objectId: {}",
                fromTime, toTime, sensorId, objectId);

        return ((List<Meter>) repository.findAll()).stream()
                .filter(m -> m.getSensorId().equals(sensorId))
                .filter(meter -> meter.getObjectId().equals(objectId))
                .filter(meter -> inTimeBounds(meter, fromTime, toTime))
                .collect(Collectors.toList());
    }

    public Map<Integer, Double> getAverageMeterValueFromEachObject() {
        log.info("Getting average data Value from each meter from each Object and converting to Map");
        return ((List<Meter>) repository.findAll()).stream()
                .collect(Collectors.groupingBy(
                        Meter::getObjectId, Collectors.averagingDouble(Meter::getValue)));
    }

    public Meter save(Meter meter) {
        return repository.save(meter);
    }

    /**
     * check if meter data is inbounds
     *
     * @return true if inbounds
     */
    private boolean inTimeBounds(Meter m, Long fromTime, Long toTime) {
        return (m.getTime() >= fromTime) && (m.getTime() <= toTime);
    }
}
