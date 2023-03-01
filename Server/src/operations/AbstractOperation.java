/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operations;

import repositories.DBRepository;
import repositories.GenericRepository;
import repositories.IRepository;

/**
 *
 * @author Nikola
 */
public abstract class AbstractOperation {

    protected final IRepository repository;

    public AbstractOperation() {
        this.repository = new GenericRepository();
    }

    protected abstract void preconditions(Object param) throws Exception;

    protected abstract void executeOperation(Object param) throws Exception;

    public final void execute(Object param) throws Exception {
        try {
            preconditions(param);
            startTransaction();
            executeOperation(param);
            commitTransaction();
        } catch (Exception ex) {
            ex.printStackTrace();
            rollbackTransaction();
            throw ex;
        } finally {
            disconnect();
        }
    }

    private void startTransaction() throws Exception {
        ((DBRepository) repository).connect();
    }

    private void commitTransaction() throws Exception {
        ((DBRepository) repository).commit();
    }

    private void rollbackTransaction() throws Exception {
        ((DBRepository) repository).rollback();
    }

    private void disconnect() throws Exception {
        ((DBRepository) repository).disconnect();
    }
}
