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
public class TicketS {
    private int ticketSId;
    private String descripcionTicket;
    private String estatus;
    private int clienteId;
    private String cliente;
    private int facturaId;
    
    
    public TicketS(){
    }

    public TicketS(int ticketSId, String descripcionTicket, String estatus, String cliente, int facturaId) {
        this.ticketSId = ticketSId;
        this.descripcionTicket = descripcionTicket;
        this.estatus = estatus;
        this.cliente = cliente;
        this.facturaId = facturaId;
    }

    public int getTicketSId() {
        return ticketSId;
    }

    public void setTicketSId(int ticketSId) {
        this.ticketSId = ticketSId;
    }

    public String getDescripcionTicket() {
        return descripcionTicket;
    }

    public void setDescripcionTicket(String descripcionTicket) {
        this.descripcionTicket = descripcionTicket;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public int getFacturaId() {
        return facturaId;
    }

    public void setFacturaId(int facturaId) {
        this.facturaId = facturaId;
    }

    @Override
    public String toString() {
        return "TicketS{" + "ticketSId=" + ticketSId + ", descripcionTicket=" + descripcionTicket + ", estatus=" + estatus + ", clienteId=" + clienteId + ", cliente=" + cliente + ", facturaId=" + facturaId + '}';
    }
    
    
}
