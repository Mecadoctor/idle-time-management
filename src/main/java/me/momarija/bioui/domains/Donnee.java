package me.momarija.bioui.domains;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Donnee {

    @Id
    @GeneratedValue
	private Long id;

    private Date date;

    private float x;

    private float x2;

    private int enginId;

	public Long getId() {
		return id;
    }

	public void setId(Long id) {
		this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getX2() {
        return x2;
    }

    public void setX2(float x2) {
        this.x2 = x2;
    }

    public int getEnginId() {
        return enginId;
    }

    public void setEnginId(int enginId) {
        this.enginId = enginId;
    }
}
