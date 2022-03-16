package com.example.jpaap;

import com.example.jpaap.entities.Patient;
import com.example.jpaap.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class JpaApApplication implements CommandLineRunner {
    @Autowired
    private PatientRepository patientRepository;
    public static void main(String[] args) {
        SpringApplication.run(JpaApApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        patientRepository.save(
                new Patient(null, "Johnathan", new Date(), false, 56));
        patientRepository.save(
                new Patient(null, "Isaac", new Date(2001, 10, 9), false, 100));
        patientRepository.save(
                new Patient(null, "Raphael", new Date(2001, 02, 13), true, 210));
        List<Patient> patients = patientRepository.findAll();
        patients.forEach(p -> {
            System.out.println("#####################");
            System.out.println("Id: " + p.getId());
            System.out.println("Nom: " + p.getNom());
            System.out.println("Score: " + p.getScore());
            System.out.println("Date de Naissance: " + p.getDateNaissance());
            System.out.println("Est-il malade? : " + p.isMalade());
        });

        System.out.println("#####################");
        Patient patient = patientRepository.findById(2L).orElse(null);
        if(patient != null){
            System.out.println("Id: " + patient.getId());
            System.out.println("Nom: " + patient.getNom());
            System.out.println("Score: " + patient.getScore());
            System.out.println("Date de Naissance: " + patient.getDateNaissance());
            System.out.println("Est-il malade? : " + patient.isMalade());
        }
        patient.setScore(870);
        patientRepository.save(patient);
        patientRepository.delete(patient);
    }
}
