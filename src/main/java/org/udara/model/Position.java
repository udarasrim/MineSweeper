package org.udara.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@AllArgsConstructor
@Getter
public class Position {
    private final int row;
    private final int col;
}
