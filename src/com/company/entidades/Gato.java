package com.company.entidades;
import com.company.dao.GatoDAO;

import javax.persistence.Entity;

@Entity
public class Gato extends Animal{

    private int cantidadVidasRestantes;

    public int getCantidadVidasRestantes() {
        return cantidadVidasRestantes;
    }

    public void setCantidadVidasRestantes(int cantidadVidasRestantes) {
        this.cantidadVidasRestantes = cantidadVidasRestantes;
    }

    @Override
    public String toString() {
        return "Gato{" +
                "idAnimal=" + idAnimal +
                ", nombre='" + nombre + '\'' +
                ", edad=" + edad +
                ", color='" + color + '\'' +
                ", cantidadVidasRestantes=" + cantidadVidasRestantes +
                '}';
    }

    public void guardar() {
        GatoDAO.guardar(this);
    }
}