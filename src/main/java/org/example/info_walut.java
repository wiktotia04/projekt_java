package org.example;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "info_walut")
public class info_walut {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String walutaDb;
    private double wartoscDb;

    // Gettery i settery
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWalutaDb() {
        return walutaDb;
    }

    public void setWalutaDb(String walutaDb) {
        this.walutaDb = walutaDb;
    }

    public double getWartoscDb() {
        return wartoscDb;
    }

    public void setWartoscDb(double wartoscDb) {
        this.wartoscDb = wartoscDb;
    }
}
