package com.neardySoftTest.neardy.controller;

import com.neardySoftTest.neardy.models.Coordinates;
import com.neardySoftTest.neardy.services.CoordinateServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CoordinateController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private static final int ROW_PER_PAGE = 15;

    private final CoordinateServiceImpl coordinateService;

    @Value("${msg.title}")
    private String title;

    public CoordinateController(CoordinateServiceImpl coordinateService) {
        this.coordinateService = coordinateService;
    }

    @GetMapping(value = "/allCoordinates")
    public List<Coordinates> getAllCoordinate(){
     return coordinateService.getAll();
    }

    @GetMapping(value = {"/", "/index"})
    public String index(Model model) {
        model.addAttribute("title", title);
        return "index";
    }

    @GetMapping(value = "/coordinates")
    public String getCoordinates(Model model,
                                 @RequestParam(value = "page", defaultValue = "1") int pageNumber) {
        List<Coordinates> coordinates = coordinateService.findAll(pageNumber, ROW_PER_PAGE);

        long count = coordinateService.count();
        boolean hasPrev = pageNumber > 1;
        boolean hasNext = ((long) pageNumber * ROW_PER_PAGE) < count;
        model.addAttribute("coordinates", coordinates);
        model.addAttribute("hasPrev", hasPrev);
        model.addAttribute("prev", pageNumber - 1);
        model.addAttribute("hasNext", hasNext);
        model.addAttribute("next", pageNumber + 1);
        return "coordinate-list";
    }

    @GetMapping(value = "/coordinates/{coordinateId}")
    public String getUserById(Model model, @PathVariable long coordinateId) {
        Coordinates coordinate = null;
        try {
            coordinate = coordinateService.findById(coordinateId);
            model.addAttribute("allowDelete", false);
        } catch (Exception ex) {
            model.addAttribute("errorMessage", ex.getMessage());
        }
        model.addAttribute("coordinate", coordinate);
        return "coordinate";
    }

    @GetMapping(value = {"/coordinates/add"})
    public String showAddCoordinate(Model model) {
        Coordinates coordinate = new Coordinates();
        model.addAttribute("add", true);
        model.addAttribute("coordinate", coordinate);

        return "coordinate-edit";
    }

    @PostMapping(value = "/coordinates/add")
    public String addCoordinate(Model model,
                                @RequestParam int x1,
                                @RequestParam int x2,
                                @RequestParam int x3,
                                @RequestParam int x4,
                                @RequestParam int x5,
                                @RequestParam int x6,
                                @RequestParam int y1,
                                @RequestParam int y2,
                                @RequestParam int y3,
                                @RequestParam int y4,
                                @RequestParam int y5,
                                @RequestParam int y6) {
        try {
            Coordinates newCoordinate = new Coordinates(x1, x2, x3, x4, x5, x6, y1, y2, y3, y4, y5, y6);
            coordinateService.save(newCoordinate);
            return "redirect:/coordinates/" + newCoordinate.getId();
        } catch (Exception ex) {
            String errorMessage = ex.getMessage();
            logger.error(errorMessage);
            model.addAttribute("errorMessage", errorMessage);

            model.addAttribute("add", true);
            return "coordinate-edit";
        }
    }

    @GetMapping(value = {"/coordinates/{coordinateId}/edit"})
    public String showEditCoordinate(Model model, @PathVariable long coordinateId) {
        Coordinates coordinate = null;
        try {
            coordinate = coordinateService.findById(coordinateId);
        } catch (Exception ex) {
            model.addAttribute("errorMessage", ex.getMessage());
        }
        model.addAttribute("add", false);
        model.addAttribute("coordinate", coordinate);
        return "coordinate-edit";
    }

    @PostMapping(value = {"/coordinates/{coordinateId}/edit"})
    public String updateCoordinate(Model model,
                                   @PathVariable long coordinateId,
                                   @RequestParam int x1,
                                   @RequestParam int x2,
                                   @RequestParam int x3,
                                   @RequestParam int x4,
                                   @RequestParam int x5,
                                   @RequestParam int x6,
                                   @RequestParam int y1,
                                   @RequestParam int y2,
                                   @RequestParam int y3,
                                   @RequestParam int y4,
                                   @RequestParam int y5,
                                   @RequestParam int y6
    ) {
        try {
            Coordinates coordinate = new Coordinates(x1, x2, x3, x4, x5, x6, y1, y2, y3, y4, y5, y6);
            coordinate.setId(coordinateId);
            coordinateService.updateCoordinates(coordinate);
            return "redirect:/coordinates/" + coordinate.getId();
        } catch (Exception ex) {
            String errorMessage = ex.getMessage();
            logger.error(errorMessage);
            model.addAttribute("errorMessage", errorMessage);

            model.addAttribute("add", false);
            return "coordinate-edit";
        }
    }

    @GetMapping(value = {"/coordinates/{coordinateId}/delete"})
    public String showDeleteCoordinate(
            Model model, @PathVariable long coordinateId) {
        Coordinates coordinate = null;
        try {
            coordinate = coordinateService.findById(coordinateId);
        } catch (Exception ex) {
            model.addAttribute("errorMessage", ex.getMessage());
        }
        model.addAttribute("allowDelete", true);
        model.addAttribute("coordinate", coordinate);
        return "coordinate";
    }

    @PostMapping(value = {"/coordinates/{coordinateId}/delete"})
    public String deleteCoordinateById(
            Model model, @PathVariable long coordinateId) {
        try {
            coordinateService.deleteCoordinates(coordinateId);
            return "redirect:/coordinates";
        } catch (Exception ex) {
            String errorMessage = ex.getMessage();
            logger.error(errorMessage);
            model.addAttribute("errorMessage", errorMessage);
            return "coordinate";
        }
    }
}