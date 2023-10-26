package org.launchcode.codingevents.controllers;

import jakarta.validation.Valid;
import org.launchcode.codingevents.data.EventCategoryRepository;
import org.launchcode.codingevents.data.EventRepository;
import org.launchcode.codingevents.models.Event;
import org.launchcode.codingevents.models.EventCategory;
import org.launchcode.codingevents.models.EventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("eventCategories")
public class EventCategoryController {
        @Autowired
        private EventCategoryRepository eventCategoryRepository;

        @GetMapping
        public String displayAllCategories(Model model) {
           model.addAttribute("title", "All Categories");
           model.addAttribute("categories", eventCategoryRepository.findAll());
           return "eventCategories/index";
        }

        @GetMapping("create")
        public String renderCreateEventCategoryForm(Model model) {
            model.addAttribute("title", "Create Category");
            model.addAttribute(new Event());
            model.addAttribute("types", EventType.values());
            return "eventCategories/create";
        }

         @PostMapping("create")
         public String processCreateEventCategoryForm(@ModelAttribute @Valid EventCategory eventCategory, Errors errors, Model model) {

            if (errors.hasErrors()) {
               model.addAttribute("title", "Create Category");
               model.addAttribute(new EventCategory());
               return "eventCategories/create";
            }

            eventCategoryRepository.save(eventCategory);
            return "redirect:";
         }
        @GetMapping("delete")
        public String displayDeleteEventForm(Model model) {
            model.addAttribute("title", "Delete Events");
            model.addAttribute("events", eventCategoryRepository.findAll());
            return "events/delete";
        }

        @PostMapping("delete")
        public String processDeleteEventsForm(@RequestParam(required = false) int[] eventIds) {

            if (eventIds != null) {
                for (int id : eventIds) {
                    eventCategoryRepository.deleteById(id);
                }
            }

            return "redirect:/events";
        }


}
