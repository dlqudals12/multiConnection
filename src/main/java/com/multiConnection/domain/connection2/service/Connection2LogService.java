package com.multiConnection.domain.connection2.service;

import com.multiConnection.data.model.entity.connection1.Connection1Log;
import com.multiConnection.data.model.entity.connection2.Connection2Log;
import com.multiConnection.data.repository.connection2.Connection2LogRepository;
import com.multiConnection.domain.connection2.request.Connection2LogSaveDto;
import com.multiConnection.domain.connection2.request.Connection2LogUpdateDto;
import com.multiConnection.global.annotation.Connection1Transaction;
import com.multiConnection.global.annotation.Connection2Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;

@Service
@RequiredArgsConstructor
@Connection2Transaction(readOnly = true)
public class Connection2LogService {

    private final Connection2LogRepository connection2LogRepository;

    @Connection2Transaction
    public void register(Connection2LogSaveDto connection2LogSaveDto) {
        connection2LogRepository.save(Connection2Log.builder()
                .title(connection2LogSaveDto.getTitle())
                .contents(connection2LogSaveDto.getContents())
                .build());
    }

    @Connection2Transaction
    public void update(Connection2LogUpdateDto connection2LogUpdateDto) {
        Connection2Log connection2Log = connection2LogRepository.findById(connection2LogUpdateDto.getId()).orElseThrow(RuntimeException::new);

        connection2Log.update(connection2LogUpdateDto.getTitle(), connection2LogUpdateDto.getContents());
    }

    public List<Connection2Log> connection2Logs() {
        return connection2LogRepository.findAll(Sort.by(Sort.Direction.DESC, "updateDt"));
    }

    public Connection2Log connection2Log(Long id) {
        return connection2LogRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Connection2Transaction
    public void deleteConnection2Log(Long id) {
        connection2LogRepository.deleteById(id);
    }
}
