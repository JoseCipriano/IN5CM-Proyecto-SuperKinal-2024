/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.josecipriano.model;

/**
 *
 * @author Jose
 */
public class Encargado {
    private int encargadoId;

    public Encargado() {
    }

    public Encargado(int encargadoId) {
        this.encargadoId = encargadoId;
    }

    public int getEncargadoId() {
        return encargadoId;
    }

    public void setEncargadoId(int encargadoId) {
        this.encargadoId = encargadoId;
    }

    @Override
    public String toString() {
        return "Id: " + encargadoId;
    }







}
