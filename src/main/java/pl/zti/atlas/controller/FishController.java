package pl.zti.atlas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.zti.atlas.exception.NotFoundException;
import pl.zti.atlas.model.Fish;
import pl.zti.atlas.service.FishService;

import java.util.List;
import java.util.Optional;

@RestController
public class FishController {

    private final FishService fishService;

    @Autowired
    public FishController(FishService fishService) {
        this.fishService = fishService;
    }

    @GetMapping("/fish/all")
    public ResponseEntity<List<Fish>> getAll() {
        try {
            List<Fish> allFishes = fishService.findAll();
            return new ResponseEntity<>(allFishes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/fish/{id}")
    public ResponseEntity<Fish> getFishById(@PathVariable("id") String id) {
        return fishService.findById(id)
            .map(fish -> new ResponseEntity<>(fish, HttpStatus.OK))
            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/fish")
    public ResponseEntity<List<Fish>> findFishes(@RequestParam String name, @RequestParam String species,
         @RequestParam String waterType) {
        try {
            List<Fish> foundFishes = fishService.findFishes(name, species, waterType);
            return new ResponseEntity<>(foundFishes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
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

    @PutMapping("/fish")
    public ResponseEntity<Fish> updateFish(@RequestBody Fish fish) {
        try {
            Fish updatedFish = fishService.updateFish(fish);
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
