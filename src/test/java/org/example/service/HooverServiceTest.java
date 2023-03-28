package org.example.service;

import org.example.model.Hoover;
import org.example.model.HooverInput;
import org.example.model.HooverRecord;
import org.example.model.HooverResult;
import org.example.repository.HooverRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
class HooverServiceTest {
    @Mock
    private HooverRepository hooverRepository;

    @InjectMocks
    private HooverService hooverService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        hooverService = new HooverService(hooverRepository);
    }

    @Test
    void testRunHooverWithSimpleInput() {
        List<int[]> patches = new LinkedList<>() {{
            add(new int[]{1, 0});
            add(new int[]{2, 2});
            add(new int[]{2, 3});
        }};
        HooverInput input = new HooverInput(new int[]{5, 5}, new int[]{1, 2}, patches, "NNESEESWNWW");
        HooverResult expectedResult = new HooverResult(new int[]{1, 3}, 1);
        doReturn(null).when(hooverRepository).save(any());
        HooverResult actualResult = hooverService.runHoover(input);
        verify(hooverRepository, times(1)).save(any());
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void testRunHoover() {
        // Create a sample input
        HooverInput input = new HooverInput();
        input.setRoomSize(new int[]{5, 5});
        input.setCoords(new int[]{1, 2});
        List<int[]> patches = new LinkedList<>() {{
            add(new int[]{1, 0});
            add(new int[]{2, 2});
            add(new int[]{2, 3});
        }};
        input.setPatches(patches);
        input.setInstructions("NNESEESWNWW");

        // Create a sample result
        HooverResult expected = new HooverResult(new int[]{1, 3}, 1);

        // Mock the hooverRepository.save() method to return the input and result objects
        when(hooverRepository.save(any(HooverRecord.class))).thenReturn(new HooverRecord(input, expected));

        // Run the hoover service
        HooverResult result = hooverService.runHoover(input);

        // Verify that the result matches the expected result
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void testGetAllRecords() {
        // Create some sample records
        HooverInput input1 = new HooverInput();
        input1.setRoomSize(new int[]{5, 5});
        input1.setCoords(new int[]{1, 2});
        input1.setPatches(Arrays.asList(new int[][]{{1, 0}, {2, 2}, {2, 3}}));
        input1.setInstructions("NNESEESWNWW");
        HooverResult result1 = new HooverResult(new int[]{1, 3}, 1);
        HooverRecord record1 = new HooverRecord(input1, result1);

        HooverInput input2 = new HooverInput();
        input2.setRoomSize(new int[]{3, 3});
        input2.setCoords(new int[]{0, 0});
        input2.setPatches(Arrays.asList(new int[][]{{1, 1}, {2, 2}}));
        input2.setInstructions("ESE");
        HooverResult result2 = new HooverResult(new int[]{2, 1}, 1);
        HooverRecord record2 = new HooverRecord(input2, result2);

        // Mock the hooverRepository.findAll() method to return the sample records
        when(hooverRepository.findAll()).thenReturn(Arrays.asList(record1, record2));

        // Get all records from the hoover service
        List<HooverRecord> records = hooverService.getAllRecords();

        // Verify that the number of records and the record details match the expected values
        assertThat(records.size() == 2);

        HooverRecord firstRecord = records.get(0);
        assertThat(firstRecord.getHooverInput()).isEqualTo(input1);
        assertThat(firstRecord.getHooverResult()).isEqualTo(result1);

        HooverRecord secondRecord = records.get(1);
        assertThat(secondRecord.getHooverInput()).isEqualTo(input2);
        assertThat(secondRecord.getHooverResult()).isEqualTo(result2);
    }

    @Test
    void testRunHooverNull() {
        Assertions.assertThrows(ResponseStatusException.class, () -> {
            hooverService.runHoover(new HooverInput(null, null, List.of(new int[]{0}), ""));
        });
    }

    @Test
    void testGetAllRecordsEmpty() {
        List<HooverRecord> result = hooverService.getAllRecords();
        assertEquals(new ArrayList<>(), result);
    }

    @Test
    void testCleanPatches() {
        // Create a test hoover and patches
        Hoover hoover = new Hoover(new int[]{1, 2});
        List<int[]> patches = new LinkedList<>() {{
            add(new int[]{1, 2});
            add(new int[]{3, 4});
        }};

        // Call the cleanPatches method
        int patchesCleaned = hooverService.cleanPatches(hoover, patches, 0);

        // Verify that the patches were cleaned correctly
        assertEquals(1, patchesCleaned);
        assertEquals(1, patches.size());
        assertArrayEquals(new int[]{3, 4}, patches.get(0));
    }

    @Test
    void getAllRecordsTest() {
        // Create a test record
        HooverInput input = new HooverInput(new int[]{5, 5}, new int[]{1, 2}, new LinkedList<>() {{
            add(new int[]{1, 0});
            add(new int[]{2, 2});
            add(new int[]{2, 3});
        }}, "NNESEESWNWW");
        HooverResult result = new HooverResult(new int[]{1, 3}, 1);
        HooverRecord record = new HooverRecord(input, result);

        // Mock the hoover repository to return the test record
        List<HooverRecord> records = new ArrayList<>();
        records.add(record);
        when(hooverRepository.findAll()).thenReturn(records);

        // Call the getAllRecords method
        List<HooverRecord> actualRecords = hooverService.getAllRecords();

        // Verify that the hoover repository was called
        verify(hooverRepository, times(1)).findAll();

        // Verify that the actual records match the expected records
        assertEquals(records, actualRecords);
    }
}