package com.multiConnection.domain.connection2.conroller;

import com.multiConnection.data.model.entity.connection1.Connection1Log;
import com.multiConnection.data.model.entity.connection2.Connection2Log;
import com.multiConnection.domain.connection2.request.Connection2LogSaveDto;
import com.multiConnection.domain.connection2.request.Connection2LogUpdateDto;
import com.multiConnection.domain.connection2.service.Connection2LogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Connection2 Log")
@RestController
@RequiredArgsConstructor
@RequestMapping("/apis/v1/connection2-log")
public class Connection2LogController {

    private final Connection2LogService connection2LogService;

    @Operation(summary = "로그 등록", description = "로그 등록")
    @PostMapping("")
    public ResponseEntity<Object> register(@RequestBody Connection2LogSaveDto connection2LogSaveDto) {
        connection2LogService.register(connection2LogSaveDto);
        return ResponseEntity.ok().body(null);
    }

    @Operation(summary = "로그 수정", description = "로그 수정")
    @PatchMapping("")
    public ResponseEntity<Object> update(@RequestBody Connection2LogUpdateDto connection2LogUpdateDto) {
        connection2LogService.update(connection2LogUpdateDto);
        return ResponseEntity.ok().body(null);
    }

    @Operation(summary = "로그 조회",description = "로그 조회")
    @GetMapping("")
    public ResponseEntity<List<Connection2Log>> connection1Logs() {
        return ResponseEntity.ok().body(connection2LogService.connection2Logs());
    }


    @Operation(summary = "로그 상세",description = "로그 상세")
    @Parameters({
            @Parameter(name = "id", description = "id", in = ParameterIn.PATH)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Connection2Log> connection1Log(@PathVariable Long id) {
        return ResponseEntity.ok().body(connection2LogService.connection2Log(id));
    }


    @Operation(summary = "로그 상세",description = "로그 상세")
    @Parameters({
            @Parameter(name = "id", description = "id", in = ParameterIn.PATH)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Connection1Log> delete(@PathVariable Long id) {
        connection2LogService.deleteConnection2Log(id);
        return ResponseEntity.ok().body(null);
    }
}
