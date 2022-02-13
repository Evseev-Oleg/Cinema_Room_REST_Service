package cinema.tickets;

/**
 * класс для генерации JSON ответа на положительный результат
 * возврата билета
 */
public class ReturnTicket {
    private Ticket returned_ticket;

    public ReturnTicket() {
    }

    public Ticket getReturned_ticket() {
        return returned_ticket;
    }

    public void setReturned_ticket(Ticket returned_ticket) {
        this.returned_ticket = returned_ticket;
    }
}
