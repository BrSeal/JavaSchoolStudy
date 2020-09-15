package main.global.board;

import main.global.board.DTO.BoardInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/board")
public class BoardController {

    private final BoardService service;

    @Autowired
    public BoardController(BoardService service) {
        this.service = service;
    }

    @GetMapping("/")
    public BoardInfoDTO getBoardInfo() {
        return service.getInfo();
    }

}
