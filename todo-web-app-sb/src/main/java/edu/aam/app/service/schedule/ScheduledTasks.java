package edu.aam.app.service.schedule;

import edu.aam.app.model.Todo;
import edu.aam.app.model.User;
import edu.aam.app.service.notification.INotificationService;
import edu.aam.app.service.todo.ITodoService;
import edu.aam.app.service.user.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
@EnableScheduling
public class ScheduledTasks {
    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY HH:mm:ss");
    private static final SimpleDateFormat targetDateFormat = new SimpleDateFormat("dd-MM-YYYY");

    @Autowired
    INotificationService notificationService;

    @Autowired
    ITodoService todoService;

    @Autowired
    IUserService userService;

    @Scheduled(fixedRate = 24 * 60 * 60 * 1000)
    public void todoRemainder() {
        List<User> users = userService.getAllUsers();
        if (users.size() > 0) {
            for (User user : users) {
                List<Todo> todos = todoService.getTodosByUserNameAndStatus(user.getEmail(), false);
                for (Todo todo : todos) {
                    long diff = daysBetween(new Date(), todo.getTargetDate());
                    if ( diff >= 0  && diff < 2) {
                        String link = "" + todo.getId();
                        String message = "Todo # "+ todo.getDescription() + " is due on "+ targetDateFormat.format(todo.getTargetDate());
                        log.info("Current Time: ({}) , Message: ({})", dateFormat.format(new Date()), message);
                        notificationService.save(user, message, link);
                    }
                }
            }
        }
    }

    private int daysBetween(Date d1, Date d2){
        return (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
    }
}
