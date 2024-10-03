package org.fitness_center.console_UI;

import org.fitness_center.core.database.jpa.JpaAgeGroupRepository;
import org.fitness_center.core.database.jpa.JpaFitnessCentersRepository;
import org.fitness_center.core.database.jpa.JpaWorkoutsRepository;
import org.fitness_center.core.services.MemberCardRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

@Component
public class CreateMemberCardUIAction implements UIAction {

@Autowired private JpaAgeGroupRepository ageGroupRepository;

@Autowired private JpaFitnessCentersRepository fitnessCentersRepository;
@Autowired private JpaWorkoutsRepository workoutsRepository;


    @Autowired
    private MemberCardRegistrationService service;


    @Override
    public void execute() throws ParseException {

        System.out.println("Enter client personal code: ");
        Scanner scanner = new Scanner(System.in);
        String client = scanner.nextLine();

        System.out.println("ID, Age Group" +
                            "1,   'CHILD" +
                            "2,   'ADULT" +
                            "3,   'SENIOR'");

        System.out.println("Enter ID of age group: ");
        Long ageGroup = scanner.nextLong();

        System.out.println(("ID, Fitness Center" +
                "1, 'IMANTA'" +
                "2,'AKROPOLE'" +
                "3, 'SAGA'" +
                "4, 'RIGA_PLAZA'" +
                "5, 'ZOLITUDE'"));
        System.out.println("Enter ID of fitness center: ");
        Long fitnessCenter = scanner.nextLong();

        System.out.println(("ID, Workout" +
                "1, 'GYM'" +
                "2, 'SWIMMING_POOL'" +
                "3, 'GROUP_CLASSES'"));
        System.out.println("Enter ID of workout: ");
        Long workout = scanner.nextLong();



        System.out.println("Enter a contract date (in format YYYY-MM-DD): ");
        String termOfContract = scanner.nextLine();

        MemberCardRegistrationRequest addMemberCardRequest = new MemberCardRegistrationRequest(client, ageGroup,
                workout, fitnessCenter, termOfContract);
        MemberCardRegistrationResponse response = service.execute(addMemberCardRequest);

        if (response.hasErrors()) {
            response.getErrors().forEach(coreError ->
                    System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage())
            );
            System.out.println("Member card not saved!");
        } else {
            System.out.println("Client has a member card now!");
            System.out.println("Enjoy your fitness.");
        }
    }

    private Date parseDate(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date format. Please use YYYY-MM-DD.", e);
        }
    }
}