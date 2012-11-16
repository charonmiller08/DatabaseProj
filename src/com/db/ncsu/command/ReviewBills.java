package com.db.ncsu.command;

import com.db.database.DatabaseManager;

public class ReviewBills extends Command {
	public CommandArgument[] getArguments() {
		CommandArgument args[] = new CommandArgument[1];
		args[0] = new CommandArgument("Customer ID","Int","Customer ID",true);
		return args;
	}

	@Override
	public void run(CommandArgument[] args) {
		//SELECT storeID, status, customerBillID, merchandiseID, quantity, price, dateTime
		//FROM CustomerBillingCycle cb, CustomerBill b, CustomerBillItems bi
		//WHERE b.id = bi.customerBillID AND cb.id = b.id AND cb.customerID = 1;
		String sql = "SELECT storeID, status, customerBillID, merchandiseID, quantity, price, dateTime " +
				"FROM CustomerBillingCycle cb, CustomerBill b, CustomerBillItems bi " +
				"WHERE b.id = bi.customerBillID AND cb.id = b.id AND cb.customerID = ?";
		DatabaseManager.runPreparedStatement(sql,args,true);
		
	}

	@Override
	public String getCommandName() {
		return "Review Customer Bills";
	}
}
