package com.epicenergy.service;

import java.io.FileReader;
import java.io.Reader;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import com.epicenergy.entity.Comune;
import com.epicenergy.entity.Provincia;
import com.epicenergy.repository.IComuneDAO;
import com.epicenergy.repository.IProvinciaDao;

@Service
public class ProvinciaAndComuneService {
	@Autowired
	IProvinciaDao provinciaDao;
	@Autowired
	ObjectProvider<Provincia> provinciaProvider;
	@Autowired
	ResourceLoader rl;
	@Autowired
	IComuneDAO comuneDao;
	// @Autowired ObjectProvider<Comune> comuneProvider;

	public void salvaProvincia(Provincia r) {
		try {
			provinciaDao.save(r);
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
	};

	public void salvaComune(Comune c) {
		try {
			comuneDao.save(c);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public Comune creaComune(String _nome, String _nomeProvincia) {
		return Comune.builder().nome(_nome).provincia(provinciaDao.findByNome(_nomeProvincia)).build();
	}

	public Provincia creaProvincia(String sigla, String provincia, String regione) {
		return Provincia.builder().sigla(sigla).nome(provincia).regione(regione).build();
	}

	public void salvaComuniNelDB() {
		try {
			Resource fileDaLeggere = rl.getResource("classpath:comuni-italiani.csv");
			Reader lettore = new FileReader(fileDaLeggere.getFile());
			CSVParser convertitoreCsv = new CSVParser(lettore, CSVFormat.DEFAULT);
			List<CSVRecord> records = convertitoreCsv.getRecords();

			records.remove(0);
			for (CSVRecord record : records) {
				for (String valore : record.values()) {
					String[] valori = valore.split(";");
					Comune c = creaComune(valori[2], valori[3]);
					if (comuneDao.findByNome(c.getNome()) == null) {
						comuneDao.save(c);
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}

	public void salvaProvinceNelDB() {
		try {
			Resource fileDaLeggere = rl.getResource("classpath:province-italiane.csv");
			// leggo il file
			Reader lettore = new FileReader(fileDaLeggere.getFile());
			// creo il convertitore e gli passo il file
			CSVParser convertitoreCsv = new CSVParser(lettore, CSVFormat.DEFAULT);
			List<CSVRecord> records = convertitoreCsv.getRecords();
			for (CSVRecord record : records) {
				for (String valore : record.values()) {
					String[] valori = valore.split(";");
					Provincia reg = creaProvincia(valori[0], valori[1], valori[2]);
					if (!reg.getSigla().contains("Sigla")) {
						if (provinciaDao.findByNome(reg.getNome()) == null) {
							salvaProvincia(reg);
						}
					}
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}

}
