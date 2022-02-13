package cinema.tickets;

import java.util.Arrays;

/**
 * класс для генерации JSON ответа на запрос /seats
 */
public class InformTicket {
    private int total_rows;
    private int total_columns;
    private Ticket[] available_seats;

    public InformTicket() {
    }

    public InformTicket(int total_rows, int total_columns, Ticket[] available_seats) {
        this.total_rows = total_rows;
        this.total_columns = total_columns;
        this.available_seats = available_seats;
    }

    public int getTotal_rows() {
        return total_rows;
    }

    public void setTotal_rows(int total_rows) {
        this.total_rows = total_rows;
    }

    public int getTotal_columns() {
        return total_columns;
    }

    public void setTotal_columns(int total_columns) {
        this.total_columns = total_columns;
    }

    public Ticket[] getAvailable_seats() {
        return available_seats;
    }

    public void setAvailable_seats(Ticket[] available_seats) {
        this.available_seats = available_seats;
    }


    @Override
    public String toString() {
        return "InformSeats{" +
                "total_rows=" + total_rows +
                ", total_columns=" + total_columns +
                ", seats=" + Arrays.toString(available_seats) +
                '}';
    }
}
