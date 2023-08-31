package com.epicenergy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.epicenergy.entity.Comune;
import com.epicenergy.entity.Fatture;
import com.epicenergy.entity.Indirizzo;
import com.epicenergy.entity.User;
import com.epicenergy.enums.RagioneSociale;
import com.epicenergy.payload.RegisterDto;
import com.epicenergy.repository.IComuneDAO;
import com.epicenergy.repository.IndirizzoRepository;
import com.epicenergy.repository.UserRepository;
import com.epicenergy.service.ClientiService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.javafaker.Faker;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
class EpicenergyApplicationTests {

	@Autowired
	IComuneDAO com_repo;
	@Autowired
	IndirizzoRepository ind_repo;
	@Autowired
	UserRepository user_repo;
	@Autowired
	ClientiService cl_svc;

	User user = new User();
	Fatture fattura = new Fatture();
	Indirizzo indirizzo = new Indirizzo();
	Comune comune = new Comune();

	private void doAPICall(String endp, Object obj) {
		try {
			ObjectMapper objMap = new ObjectMapper();
			objMap.registerModule(new JavaTimeModule());
			String jsonStr = objMap.writeValueAsString(obj);

			URL endpoint = new URL(endp);
			HttpURLConnection connection = (HttpURLConnection) endpoint.openConnection();
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			connection.addRequestProperty("Content-type", "application/json");

			try (OutputStream os = connection.getOutputStream()) {
				byte[] input = jsonStr.getBytes("UTF-8");
				os.write(input, 0, input.length);
			}

			if (connection.getResponseCode() == HttpURLConnection.HTTP_CREATED) {
				BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String inputLine;
				StringBuffer res = new StringBuffer();

				while ((inputLine = br.readLine()) != null) {
					res.append(inputLine);
				}
				br.close();

				ObjectMapper objectMapper = new ObjectMapper();
				objectMapper.registerModule(new JavaTimeModule());
				this.user = objectMapper.readValue(res.toString(), User.class);
			}
			connection.disconnect();
		} catch (Exception ex) {
			System.out.println("** ERROR ** " + ex.getMessage());
		}
	}

	@BeforeAll
	public void beforeAll() {
		System.out.println("..Before All..");
		this.comune = com_repo.getRandomComune();
	}

	@Test
	void saveIndirizzoTest() {
		Faker fk = Faker.instance();

		this.indirizzo = Indirizzo.builder().via(fk.address().streetAddress())
				.civico(Integer.parseInt(fk.address().streetAddressNumber())).localita(fk.address().secondaryAddress())
				.cap(fk.address().countryCode()).build();

		this.indirizzo.setComune(this.comune);

		this.ind_repo.save(this.indirizzo);
		System.out.println("Via: " + this.indirizzo.getVia() + " -- ID: " + this.indirizzo.getId());

		assertEquals(indirizzo.getId(), this.ind_repo.findById(this.indirizzo.getId()).get().getId());
	}

	@Test
	void registerTest() {
		Faker fk = Faker.instance();

		RegisterDto regData = new RegisterDto(fk.funnyName().name(), fk.name().lastName(), fk.name().username(),
				fk.internet().emailAddress(), "qwerty", RagioneSociale.PRVT, null, fk.phoneNumber().cellPhone(),
				null,
				null, null,
				null, null, null, this.indirizzo);

		this.doAPICall("http://localhost:8080/api/auth/register", regData);
		System.out.println("ID: " + this.user.getId() + " -- " + "Username: " + this.user.getUsername());

		assertEquals(this.user.getId(), this.user_repo.findById(this.user.getId()).get().getId());
	}

	@AfterAll
	public void afterAll() {
		if (this.user.getId() != null && this.user_repo.existsById(this.user.getId())) {
			this.user_repo.deleteById(this.user.getId());
		}
		if (this.indirizzo.getId() != null && this.ind_repo.existsById(this.indirizzo.getId())) {
			this.ind_repo.deleteById(this.indirizzo.getId());
		}
		System.out.println("..After All..");
	}
}
