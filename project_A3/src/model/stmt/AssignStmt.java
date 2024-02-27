package model.stmt;
import controller.MyException;
import model.PrgState;
import model.value.Value;
import utils.MyIDictionary;
import utils.MyIStack;
import model.expr.Expression;
import model.type.Type;

public class AssignStmt implements IStmt{
    private String id;
    private Expression exp;
    public AssignStmt(String id, Expression exp){
        this.id = id;
        this.exp = exp;
    }
    @Override
    public String toString() {
        return id + " = " + exp.toString();
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> stk  = state.getExeStack();
        MyIDictionary<String, Value> symTbl = state.getSymTable();

        if(symTbl.isDefined(id)){
            Value val = exp.eval(symTbl);
            Type typeId = (symTbl.lookup(id)).getType();
            if((val.getType()).equals(typeId)){
                symTbl.update(id, val);
            }
            else
                throw new MyException("declared type of variable "+ id +" and type of the assigned expression do not match");
        }
        else
            throw new MyException("the used variable " + id + " was not declared before");

        return state;
    }
}
