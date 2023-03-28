package org.example.controller;

import javassist.tools.web.BadHttpRequest;
import org.example.model.HooverInput;
import org.example.model.HooverRecord;
import org.example.model.HooverResult;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

@Validated
public interface HooverControllerSpec {

    @PostMapping(
            value = "/clean",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<HooverResult> cleanRoom(@RequestBody @Valid HooverInput hooverInput) throws BadHttpRequest;

    @GetMapping(
            value = "/records",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<List<HooverRecord>> getRecords() throws BadHttpRequest;

}
