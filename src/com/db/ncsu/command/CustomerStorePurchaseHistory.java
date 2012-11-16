package com.db.ncsu.command;

import com.db.database.DatabaseManager;

public class CustomerStorePurchaseHistory extends Command {
	public CommandArgument[] getArguments() {
		CommandArgument args[] = new CommandArgument[3];
		args[0] = new CommandArgument("Customer ID","Int","Customer ID",true);
		args[1] = new CommandArgument("To Date","Date","To Date (MM/dd/yy)",true);
		args[2] = new CommandArgument("From Date","Date","From Date (MM/dd/yy)",true);
		return args;
	}

	@Override
	public void run(CommandArgument[] args) {
		//SELECT cb.customerID, sum(quantity * price)
		//FROM CustomerBillingCycle cb, CustomerBill b, CustomerBillItems bi
		//WHERE b.id = bi.customerBillID AND cb.id = b.id AND cb.customerID = 1 AND dateTime > Ô01-JAN-03Õ AND dateTime < Ô01-JAN-04Õ
		//GROUP BY customerID
		String sql = "SELECT cb.customerID, sum(quantity * price) " +
				"FROM CustomerBillingCycle cb, CustomerBill b, CustomerBillItems bi " +
				"WHERE b.id = bi.customerBillID AND cb.id = b.id AND cb.customerID = ? AND dateTime > ? AND dateTime < ? " +
				"GROUP BY customerID";
		DatabaseManager.runPreparedStatement(sql,args,true);
		
	}

	@Override
	public String getCommandName() {
		return "Show total purchases from a customer for the store for a time period";
	}
}