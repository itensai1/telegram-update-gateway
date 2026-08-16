package com.tensai.telegram.controller;

import com.tensai.telegram.dto.command.CmsCommand;
import com.tensai.telegram.service.internal.InternalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/internal")
@RequiredArgsConstructor
public class InternalController {
    private final InternalService internalService;

    @PostMapping("/command")
    public ResponseEntity<Void> receiveCommand(@Valid @RequestBody CmsCommand command) {

        internalService.dispatch(command);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/file/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileId){

        Resource resource = internalService.getFileResource(fileId);

        return ResponseEntity.ok().body(resource);
    }
}
