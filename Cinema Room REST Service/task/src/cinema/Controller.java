package cinema;

import cinema.exception_handling.NoRightsException;
import cinema.exception_handling.NumberException;
import cinema.exception_handling.SeatsNotNumberException;
import cinema.exception_handling.TokenNotFoundException;
import cinema.tickets.*;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * контроллер запросов
 */
@RestController
public class Controller {
    private final int total_rows = 9;
    private final int total_columns = 9;
    private final MyConfig myConfig = new MyConfig();
    private final Ticket[] available_seats = myConfig.getArrTicket();
    private final List<TokenAndTicket> listPurchasedTickets = new ArrayList<>();
    private final InformTicket informSeats = new InformTicket(total_rows,
            total_columns, available_seats);

    /**
     * GET запрос
     *
     * @return возвращает JSON массив всех мест
     */
    @GetMapping("/seats")
    public InformTicket getInformSeats() {
        return informSeats;
    }

    /**
     * запрос на покупку билета
     * если место занято или некорректно введено возвращает error
     *
     * @param seats введенное место в виде JSON объекта Seats
     * @return если место было свободно возвращает JSON объект с токеном(
     * числом типа ######-####-####-####-######)
     */
    @PostMapping("/purchase")
    public TokenAndTicket getInformSeatsAndPrice(@RequestBody Seats seats) {
        TokenAndTicket tokenAndTicket;
        if (seats.getRow() < 1 || seats.getRow() > 9
                || seats.getColumn() < 1 || seats.getColumn() > 9) {
            throw new NumberException("The number of a row" +
                    " or a column is out of bounds!");
        }
        boolean purchasedTickets = myConfig.searchTicket(listPurchasedTickets, seats);
        if (purchasedTickets) {
            throw new SeatsNotNumberException("The ticket has" +
                    " been already purchased!");
        } else {
            tokenAndTicket = myConfig.getTokenAndTicket(myConfig.getToken(), seats);
            listPurchasedTickets.add(tokenAndTicket);
        }
        return tokenAndTicket;
    }

    /**
     * запрос на возврат билета
     *
     * @param token введенный токен для возврата
     * @return возвращает JSON объект (подтверждение что билет возвращен)
     */
    @PostMapping("/return")
    public ReturnTicket getReturnTicket(@RequestBody Token token) {
        ReturnTicket returnTicket = myConfig.getReturnTicket(listPurchasedTickets, token);
        if (returnTicket == null) {
            throw new TokenNotFoundException("Wrong token!");
        } else {
            listPurchasedTickets.remove(
                    myConfig.getIndexListPurchasedTickets(listPurchasedTickets, token));
        }
        return returnTicket;
    }

    /**
     * запрос на статистику купленных билетов, выручку от продажи и свободных местах
     * запрос делается с параметром в URL ключом доступом к статистике
     * сейчас метод требует просто введения любого password, но можно определить пароль для входа
     *
     * @param password пароль
     * @return статистика
     */
    @PostMapping("/stats")
    public Statistic getStatistic(@RequestParam(required = false) String password) {
        Statistic statistic;
        if (password == null) {
            throw new NoRightsException("The password is wrong!");
        } else {
            statistic = myConfig.getStatistic(listPurchasedTickets);
        }
        return statistic; 
    }
}
