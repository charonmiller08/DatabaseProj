package com.db.ncsu.command;

import com.db.database.DatabaseManager;

public class VendorStorePurchaseHistory extends Command {
	public CommandArgument[] getArguments() {
		CommandArgument args[] = new CommandArgument[3];
		args[0] = new CommandArgument("From Date","Date","From Date (MM/dd/yy)",true);
		args[1] = new CommandArgument("To Date","Date","To Date (MM/dd/yy)",true);
		args[2] = new CommandArgument("Store ID","Int","Store ID",true);
		return args;
	}

	@Override
	public void run(CommandArgument[] args) {
		//SELECT vendorID, sum(price * quantity)
		//FROM VendorBill, VendorBillItems
		//WHERE dateTime > Ô01-JAN-03Õ AND dateTime < Ô01-JAN-04Õ AND storeID = 1 AND vendorBillID = id
		//GROUP BY vendorID
		String sql = "SELECT vendorID, sum(price * quantity) " +
				"FROM VendorBill, VendorBillItems " +
				"WHERE dateTime > ? AND dateTime < ? AND storeID = ? AND vendorBillID = id " +
				"GROUP BY vendorID";
		DatabaseManager.runPreparedStatement(sql,args,true);
		
	}

	@Override
	public String getCommandName() {
		return "Show total purchases from a vendor for the store for a time period";
	}
}