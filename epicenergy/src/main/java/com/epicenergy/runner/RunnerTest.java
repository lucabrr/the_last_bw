package com.epicenergy.runner;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.epicenergy.entity.Comune;
import com.epicenergy.entity.Indirizzo;
import com.epicenergy.entity.User;
import com.epicenergy.enums.RagioneSociale;
import com.epicenergy.payload.RegisterDto;
import com.epicenergy.repository.IComuneDAO;
import com.epicenergy.repository.IndirizzoRepository;
import com.epicenergy.repository.UserRepository;
import com.epicenergy.service.AuthServiceImpl;
import com.epicenergy.service.ProvinciaAndComuneService;
import com.github.javafaker.Faker;

@Component
public class RunnerTest implements CommandLineRunner {
	@Autowired
	ProvinciaAndComuneService rs;
	@Autowired
	AuthServiceImpl user_auth;
	@Autowired
	IComuneDAO com_repo;
	@Autowired
	UserRepository user_repo;
	@Autowired
	IndirizzoRepository ind_repo;

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Running...");

		// rs.salvaProvinceNelDB();
		// rs.salvaComuniNelDB();

		// Crea 50 utenti con Java faker per test
		// for (int i = 0; i < 10; i++) {
		// System.out.println("User " + i);
		// Faker fk = Faker.instance();
		// Indirizzo ind = Indirizzo.builder()
		// .via(fk.address().streetAddress())
		// .civico(Integer.parseInt(fk.address().streetAddressNumber()))
		// .localita(fk.address().secondaryAddress())
		// .cap(fk.address().countryCode()).build();
		// Comune com = this.com_repo.getRandomComune();
		// ind.setComune(com);
		// ind_repo.save(ind);
		// User u = User.builder()
		// .name(fk.name().firstName())
		// .lastname(fk.name().lastName())
		// .username(fk.name().username())
		// .email(fk.internet().emailAddress())
		// .password("qwerty")
		// .ragioneSociale(RagioneSociale.PRVT)
		// .telefono(fk.phoneNumber().phoneNumber())
		// .indirizzo(ind)
		// .build();
		// this.user_repo.save(u);
		// }

		// for (int i = 0; i < 10; i++) {
		// System.out.println("P.A. " + i);
		// Faker fk = Faker.instance();
		// Indirizzo ind = Indirizzo.builder().via(fk.address().streetAddress())
		// .civico(Integer.parseInt(fk.address().streetAddressNumber()))
		// .localita(fk.address().secondaryAddress())
		// .cap(fk.address().countryCode()).build();
		// Comune com = this.com_repo.getRandomComune();
		// ind.setComune(com);
		// ind_repo.save(ind);
		// User u = User.builder()
		// .fatturatoAnnuale(fk.number().randomDouble(2, 10000, 10000000))
		// .dataInserimento(LocalDateTime.now())
		// .dataUltimoContatto(fk.date().past(1825, 10, TimeUnit.DAYS))
		// .cognomeContatto(fk.name().lastName())
		// .emailContatto(fk.internet().emailAddress())
		// .nomeContatto(fk.name().firstName())
		// .partitaIva(fk.business().creditCardNumber())
		// .pec(fk.internet().emailAddress())
		// .telefonoContatto(fk.phoneNumber().phoneNumber())
		// .name(fk.name().firstName())
		// .lastname(fk.name().lastName())
		// .username(fk.name().username())
		// .email(fk.internet().emailAddress())
		// .password("qwerty")
		// .ragioneSociale(RagioneSociale.PA)
		// .telefono(fk.phoneNumber().phoneNumber())
		// .indirizzo(ind)
		// .build();
		// this.user_repo.save(u);
		// }

		// for (int i = 0; i < 10; i++) {
		// System.out.println("S.A.S. " + i);
		// Faker fk = Faker.instance();
		// Indirizzo ind = Indirizzo.builder().via(fk.address().streetAddress())
		// .civico(Integer.parseInt(fk.address().streetAddressNumber()))
		// .localita(fk.address().secondaryAddress())
		// .cap(fk.address().countryCode()).build();
		// Comune com = this.com_repo.getRandomComune();
		// ind.setComune(com);
		// ind_repo.save(ind);
		// User u = User.builder()
		// .fatturatoAnnuale(fk.number().randomDouble(2, 10000, 10000000))
		// .dataInserimento(LocalDateTime.now())
		// .dataUltimoContatto(fk.date().past(1825, 10, TimeUnit.DAYS))
		// .cognomeContatto(fk.name().lastName())
		// .emailContatto(fk.internet().emailAddress())
		// .nomeContatto(fk.name().firstName())
		// .partitaIva(fk.business().creditCardNumber())
		// .pec(fk.internet().emailAddress())
		// .telefonoContatto(fk.phoneNumber().phoneNumber())
		// .name(fk.name().firstName())
		// .lastname(fk.name().lastName())
		// .username(fk.name().username())
		// .email(fk.internet().emailAddress())
		// .password("qwerty")
		// .ragioneSociale(RagioneSociale.SAS)
		// .telefono(fk.phoneNumber().phoneNumber())
		// .indirizzo(ind)
		// .build();
		// this.user_repo.save(u);
		// }

		// for (int i = 0; i < 10; i++) {
		// System.out.println("S.P.A. " + i);
		// Faker fk = Faker.instance();
		// Indirizzo ind = Indirizzo.builder().via(fk.address().streetAddress())
		// .civico(Integer.parseInt(fk.address().streetAddressNumber()))
		// .localita(fk.address().secondaryAddress())
		// .cap(fk.address().countryCode()).build();
		// Comune com = this.com_repo.getRandomComune();
		// ind.setComune(com);
		// ind_repo.save(ind);
		// User u = User.builder()
		// .fatturatoAnnuale(fk.number().randomDouble(2, 10000, 10000000))
		// .dataInserimento(LocalDateTime.now())
		// .dataUltimoContatto(fk.date().past(1825, 10, TimeUnit.DAYS))
		// .cognomeContatto(fk.name().lastName())
		// .emailContatto(fk.internet().emailAddress())
		// .nomeContatto(fk.name().firstName())
		// .partitaIva(fk.business().creditCardNumber())
		// .pec(fk.internet().emailAddress())
		// .telefonoContatto(fk.phoneNumber().phoneNumber())
		// .name(fk.name().firstName())
		// .lastname(fk.name().lastName())
		// .username(fk.name().username())
		// .email(fk.internet().emailAddress())
		// .password("qwerty")
		// .ragioneSociale(RagioneSociale.SPA)
		// .telefono(fk.phoneNumber().phoneNumber())
		// .indirizzo(ind)
		// .build();
		// this.user_repo.save(u);
		// }

		// for (int i = 0; i < 10; i++) {
		// System.out.println("S.R.L. " + i);
		// Faker fk = Faker.instance();
		// Indirizzo ind = Indirizzo.builder().via(fk.address().streetAddress())
		// .civico(Integer.parseInt(fk.address().streetAddressNumber()))
		// .localita(fk.address().secondaryAddress())
		// .cap(fk.address().countryCode()).build();
		// Comune com = this.com_repo.getRandomComune();
		// ind.setComune(com);
		// ind_repo.save(ind);
		// User u = User.builder()
		// .fatturatoAnnuale(fk.number().randomDouble(2, 10000, 10000000))
		// .dataInserimento(LocalDateTime.now())
		// .dataUltimoContatto(fk.date().past(1825, 10, TimeUnit.DAYS))
		// .cognomeContatto(fk.name().lastName())
		// .emailContatto(fk.internet().emailAddress())
		// .nomeContatto(fk.name().firstName())
		// .partitaIva(fk.business().creditCardNumber())
		// .pec(fk.internet().emailAddress())
		// .telefonoContatto(fk.phoneNumber().phoneNumber())
		// .name(fk.name().firstName())
		// .lastname(fk.name().lastName())
		// .username(fk.name().username())
		// .email(fk.internet().emailAddress())
		// .password("qwerty")
		// .ragioneSociale(RagioneSociale.SRL)
		// .telefono(fk.phoneNumber().phoneNumber())
		// .indirizzo(ind)
		// .build();
		// this.user_repo.save(u);
		// }

	}
}
