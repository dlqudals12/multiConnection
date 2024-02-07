package com.multiConnection.domain.connection3.service;

import com.multiConnection.data.model.entity.connection1.Connection1Log;
import com.multiConnection.data.model.entity.connection3.Connection3Log;
import com.multiConnection.data.repository.connection3.Connection3LogRepository;
import com.multiConnection.domain.connection3.request.Connection3LogSaveDto;
import com.multiConnection.domain.connection3.request.Connection3LogUpdateDto;
import com.multiConnection.global.annotation.Connection1Transaction;
import com.multiConnection.global.annotation.Connection3Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Connection3Transaction(readOnly = true)
public class Connection3LogService {

    private final Connection3LogRepository connection3LogRepository;

    @Connection3Transaction
    public void register(Connection3LogSaveDto connection3LogSaveDto) {
        connection3LogRepository.save(Connection3Log.builder()
                .title(connection3LogSaveDto.getTitle())
                .contents(connection3LogSaveDto.getContents())
                .build());
    }

    @Connection3Transaction
    public void update(Connection3LogUpdateDto connection3LogUpdateDto) {
        Connection3Log connection3Log = connection3LogRepository.findById(connection3LogUpdateDto.getId()).orElseThrow(RuntimeException::new);

        connection3Log.update(connection3LogUpdateDto.getTitle(), connection3LogUpdateDto.getContents());
    }

    public List<Connection3Log> connection3Logs() {
        return connection3LogRepository.findAll(Sort.by(Sort.Direction.DESC, "updateDt"));
    }

    public Connection3Log connection3Log(Long id) {
        return connection3LogRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Connection3Transaction
    public void deleteConnection3Log(Long id) {
        connection3LogRepository.deleteById(id);
    }
}
