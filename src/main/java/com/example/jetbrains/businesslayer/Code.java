package com.example.jetbrains.businesslayer;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.GeneratedValue;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import static javax.persistence.GenerationType.SEQUENCE;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "codes")
@JsonPropertyOrder({
    "code",
    "date"
})
public class Code {
    @Id
    @SequenceGenerator(
        name = "student_sequence",
        sequenceName = "student_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = SEQUENCE,
        generator = "student_sequence"
    )
    @Column(
        name = "id",
        updatable = false
    )
    @JsonIgnore
    private Long id;

    @Column(
        name = "code",
        columnDefinition = "TEXT"
    )
    private String code;

    @Column(name = "date")
    private String date;

    public Code(String code, String date) {
        this.code = code;
        this.date = date;
    }
}
