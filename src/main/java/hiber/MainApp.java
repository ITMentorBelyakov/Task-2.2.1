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

      userService.add(new User("Дмитрий", "Шаповалов", "user1@mail.ru", new Car("Chevrolet Impala Sport " +
                                                                                                      " Sedan Hardtop ", "1967")));
      userService.add(new User("Максим", "Шестернев", "user2@mail.ru", new Car("Land Rover Defender", "90")));
      userService.add(new User("Михаил", "Латыпов", "user3@mail.ru", new Car("Lotus", "Theory-1")));
      userService.add(new User("Дмитрий", "Беляков", "user4@mail.ru", new Car("Лицо", "Лиза Василенко")));



      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car ="+ user.getUserCar());
      }

      System.out.println("Мы искали " + userService.getUserFromCar("Lotus", "Theory-1"));

      context.close();
   }
}
