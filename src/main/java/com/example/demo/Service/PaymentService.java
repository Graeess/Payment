package com.example.demo.Service;
import com.example.demo.Entity.AppUser;
import com.example.demo.Entity.Payments;
import com.example.demo.Model.PaymentRequest;
import com.example.demo.Repository.PaymentRepository;
import com.example.demo.Repository.UserRepository;  // Добавьте импорт для UserRepository
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Data
@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;  // Добавьте зависимость для UserRepository

    @Autowired
    public PaymentService(PaymentRepository paymentRepository, UserRepository userRepository) {
        this.paymentRepository = paymentRepository;
        this.userRepository = userRepository;  // Инициализируем UserRepository
    }

    // Метод для сохранения платежа
    public void savePayment(PaymentRequest paymentRequest) {
        // Ищем пользователя по имени
        AppUser user = userRepository.findByUsername(paymentRequest.getUser());

        // Проверяем, существует ли пользователь
        if (user == null) {
            throw new RuntimeException("User not found with username: " + paymentRequest.getUser());
        }

        // Создаем новый объект Payment
        Payments payment = new Payments();
        payment.setUser(user);
        payment.setAmount(paymentRequest.getAmount());
        payment.setPaymentMethod(paymentRequest.getPaymentMethod());


        paymentRepository.save(payment);
    }
}



