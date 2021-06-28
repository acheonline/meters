package ru.achernyavskiy0n.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash("Meter")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Meter implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id private Long id;
    private Integer objectId;
    private Integer sensorId;
    private Long time;
    private Double value;

    @Override
    public String toString() {
        return "Meter with sensorId: ".concat(String.valueOf(sensorId)).concat(" and value: ").concat(String.valueOf(value));
    }
}
