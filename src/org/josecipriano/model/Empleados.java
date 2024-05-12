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
public class Empleados {
    private int empleadoId;
    private String nombreEmpleado;
    private String apellidoEmpleado;
    private int sueldo;
    private int horaEntrada;
    private int horaSalida;
    private int cargoId;
    private int encargadoId;
    
    public Empleados(){
    
    }

    public Empleados(int empleadoId, String nombreEmpleado, String apellidoEmpleado, int sueldo, int horaEntrada, int horaSalida, int cargoId,  int encargadoId) {
        this.empleadoId = empleadoId;
        this.nombreEmpleado = nombreEmpleado;
        this.apellidoEmpleado = apellidoEmpleado;
        this.sueldo = sueldo;
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
        this.cargoId = cargoId;
        this.encargadoId = encargadoId;
    }

    public int getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(int empleadoId) {
        this.empleadoId = empleadoId;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    public String getApellidoEmpleado() {
        return apellidoEmpleado;
    }

    public void setApellidoEmpleado(String apellidoEmpleado) {
        this.apellidoEmpleado = apellidoEmpleado;
    }

    public int getSueldo() {
        return sueldo;
    }

    public void setSueldo(int sueldo) {
        this.sueldo = sueldo;
    }

    public int getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(int horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public int getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(int horaSalida) {
        this.horaSalida = horaSalida;
    }

    public int getCargoId() {
        return cargoId;
    }

    public void setCargoId(int cargoId) {
        this.cargoId = cargoId;
    }

  

    public int getEncargadoId() {
        return encargadoId;
    }

    public void setEncargadoId(int encargadoId) {
        this.encargadoId = encargadoId;
    }

    @Override
    public String toString() {
        return "Empleados{" + "empleadoId=" + empleadoId + ", nombreEmpleado=" + nombreEmpleado + ", apellidoEmpleado=" + apellidoEmpleado + ", sueldo=" + sueldo + ", horaEntrada=" + horaEntrada + ", horaSalida=" + horaSalida + ", cargoId=" + cargoId  + ", encargadoId=" + encargadoId + '}';
    }
    
    
    
    





}
