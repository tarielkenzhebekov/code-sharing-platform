package com.example.jetbrains.businesslayer;

import java.util.UUID;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;

import static javax.persistence.GenerationType.SEQUENCE;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "codes")
@JsonPropertyOrder({
    "code",
    "date",
    "time",
    "views"
})
public class Code {
    @Id
    @SequenceGenerator(
        name = "code_sequence",
        sequenceName = "code_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = SEQUENCE,
        generator = "code_sequence"
    )
    @Column(
        name = "id",
        updatable = false
    )
    @JsonIgnore
    private Long id;
    
    @Column(
        name = "uuid",
        columnDefinition = "BINARY(16)",
        updatable = false
    )
    @JsonIgnore
    private UUID uuid;

    @Column(
        name = "code",
        columnDefinition = "TEXT"
    )
    private String code;

    @Column(
        name = "date",
        length = 19
    )
    private String date;

    @Column(name = "time")
    private long time;

    @Column(name = "views")
    private long views;

    @JsonIgnore
    @Column(name = "end_time")
    private LocalDateTime endTime;

    @JsonIgnore
    @Column(name = "time_restriction")
    private boolean timeRestricted;

    @JsonIgnore
    @Column(name = "views_restriction")
    private boolean viewsRestricted;

    public Code(
            UUID uuid, 
            String code, 
            String date, 
            long time,
            long views,
            LocalDateTime endTime,
            boolean restricted) {
        this.uuid = uuid;
        this.code = code;
        this.date = date;
        this.views = views;
        this.endTime = endTime;
        this.timeRestricted = timeRestricted;
        this.viewsRestricted = viewsRestricted;
    }
}
