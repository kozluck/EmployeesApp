package com.kozluck.EmployeesApp.domain.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "task_id")
    private int id;
    private String title;
    private String description;
    private int numberOfLeftContractors;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date deadlineDate;
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "employees_tasks",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id")
    )
    private Set<Employee> employees = new HashSet<>();
    @ManyToOne
    @JoinColumn(name = "project_id",nullable = true)
    private Project project;


    public Task() {
    }

    public Task(String description) {
        this.description = description;
    }

    public Task(String description, int numberOfLeftContractors) {
        this.description = description;
        this.numberOfLeftContractors = numberOfLeftContractors;
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

    public void setId(int id) {
        this.id = id;
    }

    public int getNumberOfLeftContractors() {
        return numberOfLeftContractors;
    }

    public void setNumberOfLeftContractors(int numberOfLeftContractors) {
        this.numberOfLeftContractors = numberOfLeftContractors;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Date getDeadlineDate() {
        return this.deadlineDate;
    }

    public LocalDateTime getConvertedDeadlineDateToLocalDateTime() {
        return this.deadlineDate.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    public void setDeadlineDate(Date deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id && numberOfLeftContractors == task.numberOfLeftContractors && Objects.equals(title, task.title) && Objects.equals(description, task.description);
    }

}
