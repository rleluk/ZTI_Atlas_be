package pl.zti.atlas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.zti.atlas.model.Fish;
import pl.zti.atlas.service.FishService;

import java.util.List;

@RestController
public class AtlasController {

    private final FishService fishService;

    @Autowired
    public AtlasController(FishService fishService) {
        this.fishService = fishService;
    }

    @GetMapping("/fish")
    public List<Fish> getAll() {
        return fishService.findAll();
    }

}
