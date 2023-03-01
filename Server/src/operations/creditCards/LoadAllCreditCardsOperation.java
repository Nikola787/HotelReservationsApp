/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operations.creditCards;

import domain.CreditCard;
import domain.Reservation;
import java.util.List;
import operations.AbstractOperation;

/**
 *
 * @author Nikola
 */
public class LoadAllCreditCardsOperation extends AbstractOperation {

    private List<CreditCard> creditCards;

    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof CreditCard)) {
            throw new Exception("Invalid credit card data!");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        this.creditCards = this.repository.getAll(param);
    }

    public List<CreditCard> getResult() {
        return creditCards;
    }
}
