package main.global.board;

import main.core.driver.DriverRepository;
import main.core.vehicle.VehicleRepository;
import main.global.board.DTO.BoardInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service

public class BoardServiceImpl implements BoardService {

    private final BoardInfo info;
//    private final DriverRepository driverRepository;
//    private final VehicleRepository vehicleRepository;


    @Autowired
    public BoardServiceImpl(BoardInfo info, DriverRepository driverRepository, VehicleRepository vehicleRepository) {
        this.info = info;
//        this.driverRepository = driverRepository;
//        this.vehicleRepository = vehicleRepository;

//        initBoardInfo();
    }

//    public void initBoardInfo(){
//        driverRepository.getAll().forEach(d->{
//            info.addDriver();
//            if(d.getCurrentOrder()!=null) info.addAssignedDriver();
//        });
//
//        vehicleRepository.getAll().forEach(v->{
//            info.addVehicle();
//            if(v.getCurrentOrder()!=null) info.addAssignedVehicle();
//            if(!v.isOk()) info.incrementBrokenVehiclesCount();
//        });
//    }

    public BoardInfoDTO getInfo() {
        return info.toDto();
    }
}
