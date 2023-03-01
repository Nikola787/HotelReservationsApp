/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operations.creditCards;

import domain.CreditCard;
import java.util.List;
import operations.AbstractOperation;

/**
 *
 * @author Nikola
 */
public class SearchCreditCardsOperation extends AbstractOperation {

    private List<CreditCard> result;

    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof CreditCard)) {
            throw new Exception("Invalid credit card data!");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        this.result = this.repository.getAll((CreditCard) param);
    }

    public List<CreditCard> getResult() {
        return result;
    }
}
