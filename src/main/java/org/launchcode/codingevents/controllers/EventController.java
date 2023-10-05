package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.data.EventData;
import org.launchcode.codingevents.models.Event;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Chris Bay
 */
@Controller
@RequestMapping("events")
public class EventController {
    @GetMapping
    public String displayAllEvents(Model model)
    {
//        List<String> events=new ArrayList<>();
//        events.add("Code with pride");
//        events.add("Strange loop");
//        events.add("Apple  WWDC");
//        events.add("SpringOne Platform");
//        model.addAttribute(events);
        // model.addAttribute("events",events);
        model.addAttribute("events", EventData.getAll());
        return "events/index";

    }
    //lives at /events/create
    @GetMapping("create")
    public String renderCreateEventForm()
    {
        return "events/create";
    }

    //lives at /events/create
    @PostMapping("create")
    public String createEvent(@ModelAttribute Event newEvent)
    {
        //events.add(new Event(eventName,eventDescription));
        EventData.add(newEvent);
        return "redirect:/events"; //it redirects to the main directory /events
    }
    @GetMapping("delete")
    public String renderDeleteEventForm(Model model) {
        model.addAttribute("title", "Delete Event");
        model.addAttribute("events", EventData.getAll());
        return "events/delete";
    }
    @PostMapping("delete")
    public String processDeleteEventsForm(@RequestParam(required = false) int[] eventIds) {

        if (eventIds != null) {
            for (int id : eventIds) {
                EventData.remove(id);
            }
        }

        return "redirect:/events";
    }

    @GetMapping("edit/{eventId}")
    public String displayEditForm(Model model, @PathVariable int eventId){
        Event eventToEdit = EventData.getById(eventId);
        model.addAttribute("event", eventToEdit);
        String title = "Edit Event " + eventToEdit.getName() + " (id=" + eventToEdit.getId() + ")";
        model.addAttribute("title", title );
        return "events/edit";
    }
    @PostMapping("edit")
    public String processEditForm(int eventId, String name, String description) {
        Event eventToEdit = EventData.getById(eventId);
        eventToEdit.setName(name);
        eventToEdit.setDescription(description);
        return "redirect:/events";
    }

}
