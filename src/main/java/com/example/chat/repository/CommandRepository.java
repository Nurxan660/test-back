package com.example.chat.repository;

import com.example.chat.entity.Command;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommandRepository extends CrudRepository<Command,Long> {
    List<Command> findByUserId(Long id);
}
