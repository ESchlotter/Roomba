package org.example.controller;

import javassist.tools.web.BadHttpRequest;
import lombok.extern.log4j.Log4j2;
import org.example.model.HooverInput;
import org.example.model.HooverRecord;
import org.example.model.HooverResult;
import org.example.service.HooverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
@Validated
@RequestMapping("/hoover")
public class HooverController implements HooverControllerSpec {

    private final HooverService hooverService;

    @Autowired
    public HooverController(HooverService hooverService) {
        this.hooverService = hooverService;
    }

    @Override
    public ResponseEntity<HooverResult> cleanRoom(HooverInput hooverInput) {
        log.info("Running hoover with input '{}'", hooverInput);
        HooverResult hooverResult = hooverService.runHoover(hooverInput);

        log.info("Successfully ran hoover with details '{}'", hooverResult);
        return ResponseEntity.ok(hooverResult);
    }

    @Override
    public ResponseEntity<List<HooverRecord>> getRecords() throws BadHttpRequest {
        log.info("Request for all records so far");
        List<HooverRecord> allRecords = hooverService.getAllRecords();
        log.info("Successfully retrieved records: {}", allRecords);
        return ResponseEntity.ok(allRecords);
    }

}
