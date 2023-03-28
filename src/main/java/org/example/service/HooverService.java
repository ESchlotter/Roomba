package org.example.service;
import lombok.extern.log4j.Log4j2;
import org.example.model.Hoover;
import org.example.model.HooverInput;
import org.example.model.HooverRecord;
import org.example.model.HooverResult;
import org.example.repository.HooverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Log4j2
public class HooverService {
    private final HooverRepository hooverRepository;

    @Autowired
    public HooverService(HooverRepository hooverRepository) {
        this.hooverRepository = hooverRepository;
    }

    public HooverResult runHoover(HooverInput input) {
        int[] roomSize = input.getRoomSize();
        int[] initialCoords = input.getCoords();
        List<int[]> patches = input.getPatches();
        String instructions = input.getInstructions();
        if (roomSize == null || initialCoords == null || patches == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "All inputs need to be valid");
        }

        // Create a new hoover object with the initial coordinates
        Hoover hoover = new Hoover(initialCoords);

        // Clean up any patches of dirt at the initial hoover position
        int patchesCleaned = cleanPatches(hoover, patches, 0);

        // Move the hoover according to the instructions
        for (char instruction : instructions.toCharArray()) {
            switch (instruction) {
                case 'N':
                    log.info("Hoover moving north");
                    hoover.moveNorth(roomSize[0]);
                    break;
                case 'S':
                    log.info("Hoover moving south");
                    hoover.moveSouth();
                    break;
                case 'E':
                    log.info("Hoover moving east");
                    hoover.moveEast(roomSize[1]);
                    break;
                case 'W':
                    log.info("Hoover moving west");
                    hoover.moveWest();
                    break;
                default:
                    throw new IllegalArgumentException("Invalid instruction: " + instruction);
            }

            // Clean up any patches of dirt at the new hoover position
            patchesCleaned = cleanPatches(hoover, patches, patchesCleaned);
        }

        // Save the input and output to the database
        HooverResult result = new HooverResult(hoover.getCoords(), patchesCleaned);

        hooverRepository.save(new HooverRecord(input, result));

        // Return the result as a new HooverResult object
        return result;
    }

    protected int cleanPatches(Hoover hoover, List<int[]> patches, int patchesCleaned) {
        int[] hooverCoords = hoover.getCoords();
        for (int i = 0; i < patches.size(); i++) {
            int[] patch = patches.get(i);
            if (hooverCoords[0] == patch[0] && hooverCoords[1] == patch[1]) {
                log.info("Cleaning patch at x:{}, y:{}",hooverCoords[0], hooverCoords[1]);
                patchesCleaned++;
                log.info("Patches cleaned: {}", patchesCleaned);
                patches.remove(i);
            }
        }
        return patchesCleaned;
    }

    public List<HooverRecord> getAllRecords() {
        List<HooverRecord> records = hooverRepository.findAll();
        return records;
    }

}
