package cinema;

import cinema.tickets.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

/**
 * класс реализует логику
 */
@Configuration
public class MyConfig {
    private Ticket ticket;
    private int row = 1;
    private int column = 0;

    /**
     * метод генерирует массив с местами в зале 9*9
     *
     * @return массив
     */
    @Bean
    public Ticket[] getArrTicket() {
        return IntStream.range(0, 81)
                .mapToObj(i -> {
                    if (i <= 35) {
                        if (column < 9) {
                            ticket = new Ticket(row, ++column, 10);
                        } else {
                            row++;
                            column = 0;
                            ticket = new Ticket(row, ++column, 10);
                        }
                    } else {
                        if (column < 9) {
                            ticket = new Ticket(row, ++column, 8);
                        } else {
                            row++;
                            column = 0;
                            ticket = new Ticket(row, ++column, 8);
                        }
                    }
                    return ticket;
                }).toArray(Ticket[]::new);
    }

    /**
     * генерируем токен при покупке билета UUID классом
     *
     * @return сгенерированный токен
     */
    @Bean
    public Token getToken() {
        String strToken = UUID.randomUUID().toString();
        Token token = new Token();
        token.setToken(strToken);
        return token;
    }

    @Bean
    public Seats seatsBean() {
        return new Seats();
    }

    /**
     * создаем ответ на покупку билета
     *
     * @param token созданный токен билета
     * @param seats купленное место
     * @return созданный ответ на покупку
     */
    @Bean
    public TokenAndTicket getTokenAndTicket(Token token, Seats seats) {
        TokenAndTicket tokenAndTicket = new TokenAndTicket();
        tokenAndTicket.setToken(token.getToken());
        int price = seats.getRow() > 4 ? 8 : 10;
        Ticket ticket = new Ticket();
        ticket.setRow(seats.getRow());
        ticket.setColumn(seats.getColumn());
        ticket.setPrice(price);
        tokenAndTicket.setTicket(ticket);
        return tokenAndTicket;
    }

    /**
     * создаем ответ на возврат билета
     *
     * @param list  лист всех купленных билетов
     * @param token токен, который нам передали в запросе
     * @return обьект для возврата билета
     */
    @Bean
    public ReturnTicket getReturnTicket(List<TokenAndTicket> list, Token token) {
        ReturnTicket returnTicket = null;
        for (TokenAndTicket tokenAndTicket : list) {
            if (tokenAndTicket.getToken().equals(token.getToken())) {
                returnTicket = new ReturnTicket();
                returnTicket.setReturned_ticket(tokenAndTicket.getTicket());
            }
        }
        return returnTicket;
    }

    /**
     * метод проверяет доступность места в кинотеатре
     *
     * @param list  лист купленных билетов
     * @param seats место, которое нам передали в запросе
     * @return ответ на доступность места(true/false)
     */
    @Bean
    public boolean searchTicket(List<TokenAndTicket> list, Seats seats) {
        boolean res = false;
        for (TokenAndTicket tokenAndTicket : list) {
            int row = tokenAndTicket.getTicket().getRow();
            int column = tokenAndTicket.getTicket().getColumn();
            if (row == seats.getRow() && column == seats.getColumn()) {
                res = true;
                break;
            }
        }
        return res;
    }

    /**
     * вспомогвтельный метод. вощвращает индекс в List купленного билета
     *
     * @param list  лист купленных билетов
     * @param token токен билета
     * @return индекс
     */
    @Bean
    public int getIndexListPurchasedTickets(List<TokenAndTicket> list, Token token) {
        int res = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getToken().equals(token.getToken())) {
                res = i;
                break;
            }
        }
        return res;
    }

    /**
     * генерируем обьект на ответ по запросу статистики
     *
     * @param list лист купленных билетов
     * @return обьект Statistic
     */
    @Bean
    public Statistic getStatistic(List<TokenAndTicket> list) {
        int current_income = 0;
        int number_of_available_seats = 81 - list.size();
        int number_of_purchased_tickets = list.size();
        Statistic statistic = new Statistic();
        for (TokenAndTicket tokenAndTicket : list) {
            current_income += tokenAndTicket.getTicket().getPrice();
        }
        statistic.setCurrent_income(current_income);
        statistic.setNumber_of_available_seats(number_of_available_seats);
        statistic.setNumber_of_purchased_tickets(number_of_purchased_tickets);
        return statistic;
    }
}
