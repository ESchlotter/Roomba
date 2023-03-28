package org.example.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;

@EqualsAndHashCode
@Entity
@NoArgsConstructor
public class HooverInput {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    @JsonIgnore
    private Long id;

    private int[] roomSize;
    private int[] coords;

    @ElementCollection
    private List<int[]> patches;
    private String instructions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public HooverInput(int[] roomSize, int[] coords, List<int[]> patches, String instructions) {
        this.roomSize = roomSize;
        this.coords = coords;
        this.patches = patches;
        this.instructions = instructions;
    }

    public int[] getRoomSize() {
        return roomSize;
    }

    public int[] getCoords() {
        return coords;
    }

    public List<int[]> getPatches() {
        return patches;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setRoomSize(int[] roomSize) {
        this.roomSize = roomSize;
    }

    public void setCoords(int[] coords) {
        this.coords = coords;
    }

    public void setPatches(List<int[]> patches) {
        this.patches = patches;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("HooverInput{");
        sb.append("id=").append(id);
        sb.append(", roomSize=").append(Arrays.toString(roomSize));
        sb.append(", coords=").append(Arrays.toString(coords));
        sb.append(", patches=").append(patches);
        sb.append(", instructions='").append(instructions).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
