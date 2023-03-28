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
public class HooverResult {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    @JsonIgnore
    private Long id;

    private int[] coords;
    private int patches;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public HooverResult(int[] coords, int patches) {
        this.coords = coords;
        this.patches = patches;
    }

    public int[] getCoords() {
        return coords;
    }

    public int getPatches() {
        return patches;
    }

    public void setCoords(int[] coords) {
        this.coords = coords;
    }

    public void setPatches(int patches) {
        this.patches = patches;
    }
}
