package pl.zti.atlas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.zti.atlas.model.Fish;
import pl.zti.atlas.repository.FishRepository;

import java.util.List;

@Service
public class FishService {

    private final FishRepository fishRepository;

    @Autowired
    public FishService(FishRepository fishRepository) {
        this.fishRepository = fishRepository;
    }

    public List<Fish> findAll() {
        return fishRepository.findAll();
    }
}
