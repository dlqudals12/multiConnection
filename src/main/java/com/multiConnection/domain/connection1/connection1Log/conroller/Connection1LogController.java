package com.multiConnection.domain.connection1.connection1Log.conroller;

import com.multiConnection.data.model.entity.connection1.Connection1Log;
import com.multiConnection.domain.connection1.connection1Log.dto.request.Connection1LogSaveDto;
import com.multiConnection.domain.connection1.connection1Log.dto.request.Connection1LogUpdateDto;
import com.multiConnection.domain.connection1.connection1Log.service.Connection1LogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Connection1 Log")
@RestController
@RequiredArgsConstructor
@RequestMapping("/apis/v1/connection1-log")
public class Connection1LogController {

    private final Connection1LogService connection1LogService;

    @Operation(summary = "로그 등록", description = "로그 등록")
    @PostMapping("")
    public ResponseEntity<Object> register(@RequestBody Connection1LogSaveDto connection1LogSaveDto) {
        connection1LogService.register(connection1LogSaveDto);
        return ResponseEntity.ok().body(null);
    }

    @Operation(summary = "로그 수정", description = "로그 수정")
    @PatchMapping("")
    public ResponseEntity<Object> update(@RequestBody Connection1LogUpdateDto connection1LogUpdateDto) {
        connection1LogService.update(connection1LogUpdateDto);
        return ResponseEntity.ok().body(null);
    }

    @Operation(summary = "로그 조회",description = "로그 조회")
    @GetMapping("")
    public ResponseEntity<List<Connection1Log>> connection1Logs() {
        return ResponseEntity.ok().body(connection1LogService.connection1Logs());
    }


    @Operation(summary = "로그 상세",description = "로그 상세")
    @Parameters({
            @Parameter(name = "id", description = "id", in = ParameterIn.PATH)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Connection1Log> connection1Log(@PathVariable Long id) {
        return ResponseEntity.ok().body(connection1LogService.connection1Log(id));
    }


    @Operation(summary = "로그 상세",description = "로그 상세")
    @Parameters({
            @Parameter(name = "id", description = "id", in = ParameterIn.PATH)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Connection1Log> delete(@PathVariable Long id) {
        connection1LogService.deleteConnection1Log(id);
        return ResponseEntity.ok().body(null);
    }
}
