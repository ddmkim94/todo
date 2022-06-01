package com.kdm.todo.service;

import com.kdm.todo.domain.File;
import com.kdm.todo.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class FileService {

    private final FileRepository fileRepository;

    public Long saveFile(File file) {
        fileRepository.save(file);
        return file.getFileId().getId();
    }

    public List<File> findByTodoId(Long todoId) {
        return fileRepository.findByTodoId(todoId);
    }
}
