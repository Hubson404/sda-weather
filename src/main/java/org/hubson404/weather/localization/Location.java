package org.hubson404.weather.localization;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hubson404.weather.security.SecurityUser;
import org.hubson404.weather.weather.Forecast;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Location {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String cityName;
    private int latitude;
    private int longitude;
    private String regionName;
    private String countryName;
    @CreatedDate
    private Instant createdDate;
    @CreatedBy
    private String createdBy;

    @OneToMany(mappedBy = "location")
    private List<Forecast> forecastList = new ArrayList<>();
}
