package ru.team.up.notify.sup.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SupParameter<T> {

    private String name;
    private T value;

}
