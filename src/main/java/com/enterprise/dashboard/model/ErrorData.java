package com.enterprise.dashboard.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

/**
 * The type Error data.
 */
@Entity(name = "ErrorData")
@Table(name = "ERROR_DATA")
public class ErrorData {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    @Column(name = "ID")
    private Integer id;

    @Column(name = "MESSAGE")
    private String message;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "DTTM")
    private Date dttm;

    @ManyToOne
    private Application application;

    /**
     * Instantiates a new Error data.
     */
    public ErrorData() {
    }

    /**
     * Instantiates a new Error data.
     *
     * @param message     the message
     * @param description the description
     * @param application the application
     */
    public ErrorData(String message, String description, Application application) {
        this.message = message;
        this.description = description;
        this.application = application;
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
     * Gets message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets message.
     *
     * @param message the message
     */
    public void setMessage(String message) {
        this.message = message;
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
     * Gets application.
     *
     * @return the application
     */
    public Application getApplication() {
        return application;
    }

    /**
     * Sets application.
     *
     * @param application the application
     */
    public void setApplication(Application application) {
        this.application = application;
    }

    /**
     * Gets dttm.
     *
     * @return the dttm
     */
    public Date getDttm() {
        return dttm;
    }

    /**
     * Sets dttm.
     *
     * @param dttm the dttm
     */
    public void setDttm(Date dttm) {
        this.dttm = dttm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ErrorData)) return false;
        ErrorData errorData = (ErrorData) o;
        return Objects.equals(getId(), errorData.getId()) &&
                Objects.equals(getMessage(), errorData.getMessage()) &&
                Objects.equals(getDescription(), errorData.getDescription()) &&
                Objects.equals(getApplication(), errorData.getApplication());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getMessage(), getDescription(), getApplication());
    }
}
