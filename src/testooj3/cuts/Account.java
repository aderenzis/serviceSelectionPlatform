package testooj3.cuts;

public class Account implements java.io.Serializable {
    public float mBalance;

    public Account() {
        mBalance = 0;
    }

    public void deposit(double amount) {
        mBalance+= amount;
    }

    public void withdraw(double amount) throws InsufficientBalanceException {
        if (mBalance<amount)
            throw new InsufficientBalanceException();
        mBalance-= amount;
    }
    
    public void transfer(double amount, Account target) throws InsufficientBalanceException {
        this.withdraw(amount);
        target.deposit(amount);
    }

    public String toString() {
        return "" + mBalance;
    }

    public boolean equals(Object o) {
        if (!(o instanceof Account))
            return false;
        Account c = (Account) o;
        return (c.mBalance == this.mBalance);
    }

    public double getBalance() {
        return mBalance;
    }
}
