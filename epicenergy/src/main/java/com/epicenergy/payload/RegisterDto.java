package com.epicenergy.payload;

import com.epicenergy.entity.Indirizzo;
import com.epicenergy.enums.RagioneSociale;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDto {
    private String name;
    private String lastname;
    private String username;
    private String email;
    private String password;
    private RagioneSociale ragioneSociale;
    private String partitaIva;
    private String telefono;
    private Double fatturatoAnnuale;
    private String pec;
    private String emailContatto;
    private String nomeContatto;
    private String cognomeContatto;
    private String telefonoContatto;
    private Indirizzo indirizzo;
}
