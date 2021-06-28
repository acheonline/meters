package ru.achernyavskiy0n.controller;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.achernyavskiy0n.model.Meter;
import ru.achernyavskiy0n.service.MeterService;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class MetersController {

    @Autowired
    MeterService service;

    /**
     * Saves meter statistics from JSON document
     *
     * @param meters - JSON document with meter statistis
     * @return HTTP OK STATUS
     */
    @PostMapping("/api/save")
    @ResponseBody
    public ResponseEntity<HttpStatus> addAll(@NonNull @RequestBody String meters) {
        service.addAll(meters);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Health check method - dummy
     *
     * @return String with checking result
     */
    @GetMapping("/healthcheck")
    public String getHealthCheck() {
        return "{ \"check isWorking\" : true }";
    }

    @GetMapping("/all")
    public List<Meter> getAll() {
        return service.getAll();
    }

    /**
     * Get average Value of all Meters from specific Object, by objectId
     *
     * @param objectId - id of Object
     * @return Average result in double
     */
    @GetMapping("/api/latest")
    public Double getAverageMeterValue(@RequestParam("id") Integer objectId) {
        return service.getAverageMeterValue(objectId);
    }

    /**
     * Get all measures from sensorId, objectId in bounds of fromTime to toTime
     *
     * @param sensorId - id of sensor
     * @param objectId - id of Object
     * @param fromTime - start bound of time
     * @param toTime   - final bound of time
     * @return list of Meters
     */
    @GetMapping("/api/history")
    @ResponseBody
    public List<Meter> getAllMeterMeasuresInTimeBoundsBySensorIdAndObjectId(@RequestParam("sensorId") @NonNull Integer sensorId,
                                                                            @RequestParam("objectId") @NonNull Integer objectId,
                                                                            @RequestParam("from") @NonNull Long fromTime,
                                                                            @RequestParam("to") @NonNull Long toTime) {
        return service.getAllMeterMeasuresInTimeBoundsBySensorIdAndObjectId(sensorId, objectId, fromTime, toTime);
    }

    /**
     * Get average meter metric Value form each Object and convert to Map
     *
     * @return map
     */
    @GetMapping("/api/avg")
    public Map<Integer, Double> getAverageMeterValueFromEachObject() {
        return service.getAverageMeterValueFromEachObject();
    }

}
