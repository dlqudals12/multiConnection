package com.multiConnection.domain.connection1.connection1Log.service;

import com.multiConnection.data.model.entity.connection1.Connection1Log;
import com.multiConnection.data.repository.connection1.Connection1LogRepository;
import com.multiConnection.domain.connection1.connection1Log.dto.request.Connection1LogSaveDto;
import com.multiConnection.domain.connection1.connection1Log.dto.request.Connection1LogUpdateDto;
import com.multiConnection.global.annotation.Connection1Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Connection1Transaction(readOnly = true)
public class Connection1LogService {

    private final Connection1LogRepository connection1LogRepository;

    @Connection1Transaction
    public void register(Connection1LogSaveDto connection1LogSaveDto) {
        connection1LogRepository.save(Connection1Log.builder()
                .title(connection1LogSaveDto.getTitle())
                .contents(connection1LogSaveDto.getContents())
                .build());
    }

    @Connection1Transaction
    public void update(Connection1LogUpdateDto connection1LogUpdateDto) {
        Connection1Log connection1Log = connection1LogRepository.findById(connection1LogUpdateDto.getId()).orElseThrow(RuntimeException::new);

        connection1Log.update(connection1LogUpdateDto.getTitle(), connection1LogUpdateDto.getContents());
    }

    public List<Connection1Log> connection1Logs() {
        return connection1LogRepository.findAll(Sort.by(Sort.Direction.DESC, "updateDt"));
    }

    public Connection1Log connection1Log(Long id) {
        return connection1LogRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Connection1Transaction
    public void deleteConnection1Log(Long id) {
        connection1LogRepository.deleteById(id);
    }
}
