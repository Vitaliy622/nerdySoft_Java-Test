package com.neardySoftTest.neardy.services;

import com.neardySoftTest.neardy.models.Coordinates;
import com.neardySoftTest.neardy.repository.CoordinateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CoordinateServiceImpl {
    @Autowired
    private CoordinateRepository coordinateRepository;

    private boolean existsById(Long id) {
        return coordinateRepository.existsById(id);
    }

    public void updateCoordinates(Coordinates coordinates) throws Exception {
        if (!existsById(coordinates.getId())) {
            throw new Exception("Cannot find User with id: " + coordinates.getId());
        }
        coordinateRepository.save(coordinates);
    }


    public void deleteCoordinates(Long id) throws Exception {
        if (!existsById(id)) {
            throw new Exception("Cannot find Coordinates with id: " + id);
        } else {
            coordinateRepository.deleteById(id);
        }
    }


    public Long count() {
        return coordinateRepository.count();
    }


    public List<Coordinates> findAll(int pageNumber, int rowPerPage) {
        List<Coordinates> users = new ArrayList<>();
        Pageable sortedByLastUpdateDesc = PageRequest.of(pageNumber - 1, rowPerPage,
                Sort.by("id").ascending());
        coordinateRepository.findAll(sortedByLastUpdateDesc).forEach(users::add);
        return users;
    }


    public Coordinates findById(long id) {
        return coordinateRepository.findById(id).orElse(null);
    }


    public Coordinates save(Coordinates coordinate) throws Exception {
        if (coordinate.getId() != null && existsById(coordinate.getId())) {
            throw new Exception("Coordinate with id: " + coordinate.getId() + " already exists");
        }
        return coordinateRepository.save(coordinate);
    }
    public List<Coordinates>getAll(){
        return (List<Coordinates>) coordinateRepository.findAll();
    }
}
