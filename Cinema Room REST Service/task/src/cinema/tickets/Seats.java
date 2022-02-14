package cinema.tickets;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

/**
 * класс для генерации JSON обьекта 1 места в зале
 */
public class Seats {
    private int row;
    private int column;

    public Seats() {
    }

    @Autowired
    public Seats(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seats seats = (Seats) o;
        return row == seats.row && column == seats.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }
}
