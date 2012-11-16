package com.db.ncsu.command;

import com.db.database.DatabaseManager;

public class ReviewCustomerOrder extends Command {
	public CommandArgument[] getArguments() {
		CommandArgument args[] = new CommandArgument[1];
		args[0] = new CommandArgument("Special Order ID","Int","Special Order ID",true);
		return args;
	}

	@Override
	public void run(CommandArgument[] args) {
		//SELECT storeID, status, specialOrderID, merchandiseID, quantity, price
		//FROM SpecialOrder o, SpecialOrderItems oi
		//WHERE o.id = oi.specialOrderID AND o.id = 1
		String sql = "SELECT storeID, status, specialOrderID, merchandiseID, quantity, price " +
				"FROM SpecialOrder o, SpecialOrderItems oi " +
				"WHERE o.id = oi.specialOrderID AND o.id = ?";
		DatabaseManager.runPreparedStatement(sql,args,true);
		
	}

	@Override
	public String getCommandName() {
		return "Review Customer Order";
	}
}