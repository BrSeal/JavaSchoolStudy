package main.core.orderManagement.cargo;

import main.core.orderManagement.cargo.entity.Cargo;

import java.util.List;

interface CargoRepository {
    List<Cargo> getAll();

    Cargo get(int id);

    void update(Cargo Cargo);
}
