package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("Maxim", "Maximov", "user1@mail.ru",
              new Car("BMW", 456876)));

      userService.add(new User("Dasha", "Smirnova", "user2@mail.ru"));

      userService.add(new User("Ben", "Elezky", "user3@mail.ru",
              new Car("Ferrari", 666999)));

      userService.add(new User("Denis", "Korcheganov", "user4@mail.ru",
              new Car("Lada", 123456)));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = " + user.getCar());
         System.out.println();
      }

      User findUser = userService.findUserByCar("Ferrari", 666999);
      System.out.printf(
              "Find User:\n  ID = %d\n First Name = %s\n Last Name = %s\n Email = %s\n  Car = %s",
              findUser.getId(),
              findUser.getFirstName(),
              findUser.getLastName(),
              findUser.getEmail(),
              findUser.getCar()
      );

      context.close();
   }
}
