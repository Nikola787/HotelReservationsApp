/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package creditcards;

import domain.CreditCard;
import domain.Room;
import operations.AbstractOperation;

/**
 *
 * @author Nikola
 */
public class CreateCreditCardOperation extends AbstractOperation {

    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof CreditCard)) {
            throw new Exception("Invalid credit card data!");
        }

        CreditCard cc = (CreditCard) param;

        boolean isValid = (cc.getBankName() != null && cc.getBankName().length() >= 3)
                && (cc.getNumber() != null && cc.getNumber().length() >= 18);

        if (!isValid) {
            throw new Exception("Invalid credit card data!");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        this.repository.add((CreditCard) param);
    }

}
