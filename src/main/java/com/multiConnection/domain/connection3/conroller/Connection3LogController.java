package com.multiConnection.domain.connection3.conroller;

import com.multiConnection.data.model.entity.connection1.Connection1Log;
import com.multiConnection.data.model.entity.connection3.Connection3Log;
import com.multiConnection.domain.connection3.request.Connection3LogSaveDto;
import com.multiConnection.domain.connection3.request.Connection3LogUpdateDto;
import com.multiConnection.domain.connection3.service.Connection3LogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Connection3 Log")
@RestController
@RequiredArgsConstructor
@RequestMapping("/apis/v1/connection3-log")
public class Connection3LogController {

    private final Connection3LogService connection3LogService;

    @Operation(summary = "로그 등록", description = "로그 등록")
    @PostMapping("")
    public ResponseEntity<Object> register(@RequestBody Connection3LogSaveDto connection3LogSaveDto) {
        connection3LogService.register(connection3LogSaveDto);
        return ResponseEntity.ok().body(null);
    }

    @Operation(summary = "로그 수정", description = "로그 수정")
    @PatchMapping("")
    public ResponseEntity<Object> update(@RequestBody Connection3LogUpdateDto connection3LogUpdateDto) {
        connection3LogService.update(connection3LogUpdateDto);
        return ResponseEntity.ok().body(null);
    }

    @Operation(summary = "로그 조회",description = "로그 조회")
    @GetMapping("")
    public ResponseEntity<List<Connection3Log>> connection3Logs() {
        return ResponseEntity.ok().body(connection3LogService.connection3Logs());
    }


    @Operation(summary = "로그 상세",description = "로그 상세")
    @Parameters({
            @Parameter(name = "id", description = "id", in = ParameterIn.PATH)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Connection3Log> connection3Log(@PathVariable Long id) {
        return ResponseEntity.ok().body(connection3LogService.connection3Log(id));
    }


    @Operation(summary = "로그 상세",description = "로그 상세")
    @Parameters({
            @Parameter(name = "id", description = "id", in = ParameterIn.PATH)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Connection1Log> delete(@PathVariable Long id) {
        connection3LogService.deleteConnection3Log(id);
        return ResponseEntity.ok().body(null);
    }
}
