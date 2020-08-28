package main.core.cargo;

import main.core.cargo.entity.Cargo;
import org.jetbrains.annotations.Nullable;

import java.util.List;

interface CargoRepository {
    List<Cargo> getAll();

    @Nullable
    Cargo get(int id);

    int save(Cargo Cargo);

    void update(Cargo Cargo);

    Cargo delete(Cargo Cargo);
}
