package org.example.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@EqualsAndHashCode
@ToString
@Entity
@NoArgsConstructor
public class HooverRecord {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    @JsonIgnore
    private Long id;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "hoover_input_id")
    private HooverInput hooverInput;
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "hoover_result_id")
    private HooverResult hooverResult;

    public HooverResult getHooverResult() {
        return hooverResult;
    }

    public void setHooverResult(HooverResult hooverResult) {
        this.hooverResult = hooverResult;
    }

    public HooverInput getHooverInput() {
        return hooverInput;
    }

    public void setHooverInput(HooverInput hooverInput) {
        this.hooverInput = hooverInput;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // TODO: add more information like date etc
    public HooverRecord(HooverInput hooverInput, HooverResult hooverResult) {
        this.hooverInput = hooverInput;
        this.hooverResult = hooverResult;
    }

}
