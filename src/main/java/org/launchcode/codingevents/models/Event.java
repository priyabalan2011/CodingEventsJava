package org.launchcode.codingevents.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Objects;

public class Event {
    private int id;
    private static int nextId=1;
    @NotBlank(message = "Username is required")
    @Size(min=3,max=12)
    private String name;

    @Size(max=300, message = "Description is too long.")
    private String description;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email")
    private String contactEmail;

    public Event(String name,String description,String contactEmail) {
        this.name = name;
        this.description=description;
        this.contactEmail=contactEmail;
        this.id=nextId;
        nextId++;
    }
    public Event()
    {
        this.id = nextId;
        nextId++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Event event)) return false;
        return id == event.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}