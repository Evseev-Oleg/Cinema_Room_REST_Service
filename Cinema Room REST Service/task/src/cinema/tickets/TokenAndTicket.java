package cinema.tickets;

/**
 * класс для генерации JSON ответа на запрос покупки билета /purchase
 */
public class TokenAndTicket {
    private String token;
    private Ticket ticket;

    public TokenAndTicket() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
}
