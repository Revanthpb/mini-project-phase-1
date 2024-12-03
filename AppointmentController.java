package chatbot.revan;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/chatbot")
public class AppointmentController {

    private final AppointmentRepository appointmentRepository;

    public AppointmentController(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("message", "Hello! How can I assist you today?");
        return "index";
    }

    // Bot Interaction - Post user input and generate dynamic bot responses
    @PostMapping("/chat")
    public String chat(@RequestParam String userMessage, Model model) {
        String botResponse;

        if (userMessage.equalsIgnoreCase("hi") || userMessage.equalsIgnoreCase("hello")) {
            botResponse = "Hello! How can I help you?";
        } else if (userMessage.equalsIgnoreCase("schedule an appointment")) {
            botResponse = "Yeah sure! Please provide the title and the date.";
        } else {
            botResponse = "Sorry, I didn't understand that. Can you please repeat?";
        }

        model.addAttribute("userMessage", userMessage);
        model.addAttribute("botMessage", botResponse);
        return "index";
    }

    // Schedule an appointment
    @PostMapping("/schedule")
    public String scheduleAppointment(@RequestParam String title, @RequestParam String dateTime, Model model) {
        LocalDateTime appointmentTime = LocalDateTime.parse(dateTime); // Convert string to LocalDateTime
        Appointment appointment = new Appointment();
        appointment.setTitle(title);
        appointment.setDateTime(appointmentTime);
        appointmentRepository.save(appointment);

        model.addAttribute("botMessage", "Your appointment for " + title + " has been scheduled for " + dateTime);
        return "index";
    }

    // View all appointments
    @GetMapping("/view")
    public String viewAppointments(Model model) {
        List<Appointment> appointments = appointmentRepository.findAll();
        model.addAttribute("appointments", appointments);
        return "view";
    }

    // Delete an appointment by ID
    @GetMapping("/delete/{id}")
    public String deleteAppointment(@PathVariable Long id) {
        appointmentRepository.deleteById(id);
        return "redirect:/chatbot/view";
    }
}
