package fun.falco.elypiai.companieshouse.models;

/**
 * The last company accounts filed.
 *
 * @author seth@falco.fun (Seth Falco)
 * @since 4.2.2
 */
public class PreviousAccounts extends AccountingPeriod {

    /**
     * @see #getAccountsType()
     */
    private AccountsType accountsType;

    /**
     * @return Type of the last company accounts filed.
     */
    public AccountsType getAccountsType() {
        return accountsType;
    }
}
