package model.expr;

import controller.MyException;
import model.value.Value;
import utils.MyIDictionary;

public class VarLookUpExp implements Expression {
    private String id;

    public VarLookUpExp(String id){
        this.id = id;
    }

    @Override
    public String toString() {
        return "Look up " + id;
    }

    @Override
    public Value eval(MyIDictionary<String, Value> tbl) throws MyException {
        return tbl.lookup(id);
    }
}
