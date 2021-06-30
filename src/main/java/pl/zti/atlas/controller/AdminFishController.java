package pl.zti.atlas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.zti.atlas.exception.NotFoundException;
import pl.zti.atlas.model.Fish;
import pl.zti.atlas.service.FishService;


@RestController
@RequestMapping("/admin")
public class AdminFishController {

    private final FishService fishService;

    @Autowired
    public AdminFishController(FishService fishService) {
        this.fishService = fishService;
    }

    @PostMapping("/fish")
    public ResponseEntity<Fish> addFish(@RequestBody Fish fish) {
        try {
            Fish newFish = fishService.save(fish);
            return new ResponseEntity<>(newFish, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/fish/{id}")
    public ResponseEntity<Fish> updateFish(@PathVariable("id") String id, @RequestBody Fish fish) {
        try {
            Fish updatedFish = fishService.updateFish(id, fish);
            return new ResponseEntity<>(updatedFish, HttpStatus.OK);
        } catch(NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/fish/{id}")
    public ResponseEntity<HttpStatus> deleteFish(@PathVariable("id") String id) {
        try {
            fishService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
