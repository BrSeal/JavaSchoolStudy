package main.core.cargo;

import main.model.logistic.Cargo;

import java.util.List;

interface CargoRepository {
    List<Cargo> getAll();

    Cargo get(int id);

    int save(Cargo Cargo);

    void update(Cargo Cargo);

    Cargo delete(Cargo Cargo);
}
