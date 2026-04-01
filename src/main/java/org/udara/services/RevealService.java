package org.udara.services;

import org.udara.model.Grid;
import org.udara.model.Position;

public interface RevealService {

    int reveal(Grid grid, Position position);
}
