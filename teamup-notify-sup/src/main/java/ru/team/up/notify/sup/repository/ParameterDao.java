package ru.team.up.notify.sup.repository;

import ru.team.up.dto.SupParameterDto;

import java.util.List;

public interface ParameterDao {
    void add(SupParameterDto<?> parameter);

    SupParameterDto<?> findByName(String name);

    List<SupParameterDto<?>> findAll();
}