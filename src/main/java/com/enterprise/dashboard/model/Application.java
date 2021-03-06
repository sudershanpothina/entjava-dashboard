package com.enterprise.dashboard.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * The type Application.
 */
@Entity(name = "Applications")
@Table(name = "APPLICATIONS")
public class Application {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "TEAM_ID")
    private String teamId;

    @OneToMany(mappedBy = "application", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<ErrorData> errorDataSet = new HashSet<>();

    /**
     * Instantiates a new Application.
     */
    public Application() {
    }

    /**
     * Instantiates a new Application.
     *
     * @param name        the name
     * @param description the description
     * @param teamId      the team id
     */
    public Application(String name, String description, String teamId) {
        this.name = name;
        this.description = description;
        this.teamId = teamId;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }


    /**
     * Gets team id.
     *
     * @return the team id
     */
    public String getTeamId() {
        return teamId;
    }

    /**
     * Sets team id.
     *
     * @param teamId the team id
     */
    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    /**
     * Gets error data set.
     *
     * @return the error data set
     */
    public Set<ErrorData> getErrorDataSet() {
        return errorDataSet;
    }

    /**
     * Sets error data set.
     *
     * @param errorDataSet the error data set
     */
    public void setErrorDataSet(Set<ErrorData> errorDataSet) {
        this.errorDataSet = errorDataSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Application)) return false;
        Application that = (Application) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getDescription(), that.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDescription());
    }

    @Override
    public String toString() {
        return "Application{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
