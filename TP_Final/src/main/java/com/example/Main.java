package com.example;

import com.example.repository.ReservationRepository;
import com.example.repository.ReservationRepositoryImplement;
import com.example.repository.SalleRepository;
import com.example.repository.SalleRepositoryImplement;
import com.example.service.ReservationService;
import com.example.service.ReservationServiceImplement;
import com.example.service.SalleService;
import com.example.service.SalleServiceImplement;
import com.example.test.TestScenarios;
import com.example.util.DataInitializer;
import com.example.util.PerformanceReport;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== APPLICATION DE RÉSERVATION DE SALLES ===");

        // Création de l'EntityManagerFactory
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestion-reservations");
        EntityManager em = emf.createEntityManager();

        try {
            // Initialisation des repositories et services!
            SalleRepository salleRepository = new SalleRepositoryImplement(em);
            SalleService salleService = new SalleServiceImplement(em, salleRepository);

            ReservationRepository reservationRepository = new ReservationRepositoryImplement(em);
            ReservationService reservationService = new ReservationServiceImplement(reservationRepository);

            // Menu principale3
            Scanner scanner = new Scanner(System.in);
            boolean exit = false;

            while (!exit) {
                System.out.println("\n=== MENU PRINCIPAL ===");
                System.out.println("1. Initialiser les données de test");
                System.out.println("2. Exécuter les scénarios de test");
                System.out.println("3. Exécuter le script de migration");
                System.out.println("4. Générer un rapport de performance");
                System.out.println("5. Quitter");
                System.out.print("Votre choix: ");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Consommer la nouvelle ligne

                switch (choice) {
                    case 1:
                        // Initialiser les données de test
                        DataInitializer dataInitializer = new DataInitializer(emf);
                        dataInitializer.initializeData();
                        break;

                    case 2:
                        // Exécuter les scénarios de test
                        TestScenarios testScenarios = new TestScenarios(emf, salleService, reservationService);
                        testScenarios.runAllTests();
                        break;

                    case 3:
                        // Exécuter le script de migration
                        System.out.println("Cette fonctionnalité nécessite une base de données externe.");
                        System.out.print("Voulez-vous continuer avec une simulation? (o/n): ");
                        String confirm = scanner.nextLine();

                        if (confirm.equalsIgnoreCase("o")) {
                            System.out.println("Simulation de la migration...");
                            System.out.println("Dans un environnement réel, utilisez la classe DatabaseMigrationTool.");
                            System.out.println("Exemple: DatabaseMigrationTool.main(args);");
                        }
                        break;

                    case 4:
                        // Générer un rapport de performance
                        PerformanceReport performanceReport = new PerformanceReport(emf);
                        performanceReport.runPerformanceTests();
                        break;

                    case 5:
                        // Quitter
                        exit = true;
                        System.out.println("Au revoir !");
                        break;

                    default:
                        System.out.println("Choix invalide. Veuillez réessayer.");
                }
            }

        } finally {
            em.close();
            emf.close();
        }
    }
}
