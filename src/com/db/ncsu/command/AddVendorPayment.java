package com.db.ncsu.command;

import com.db.database.DatabaseManager;

public class AddVendorPayment extends Command {

	@Override
	public CommandArgument[] getArguments() {
		CommandArgument args[] = new CommandArgument[4];
		args[0] = new CommandArgument("Vendor Bill ID","Int","Vendor Bill ID",true);		
		args[1] = new CommandArgument("Staff ID","Int","Staff ID",true);
		args[2] = new CommandArgument("Paid Date", "String", "Paid Date (format yyyy/mm/dd hh:mm:ss)", true);
		args[3] = new CommandArgument("Payment Information", "String", "Payment Information", true);

		return args;
	}

	@Override
	public void run(CommandArgument[] args) {
		//INSERT INTO VendorPayment(vendorBillID, staffID, paidDate, paymentInformation, confirmationCode)
		//VALUES(1, 1, to_date('2003/05/06 11:15:32', 'yyyy/mm/dd hh24:mi:ss'), 'mastercard 3332 3333 2222 3333', vendor_confirmation_seq.nextval)
		String sql = "INSERT INTO VendorPayment(vendorBillID, staffID, paidDate, paymentInformation, confirmationCode) " +
				"VALUES(?, ?, to_date(?, 'yyyy/mm/dd hh24:mi:ss'), ?, vendor_confirmation_seq.nextval)";
		DatabaseManager.runPreparedStatement(sql,args,false);
		System.out.println("PAYMENT ADDED!!!");	
	}

	@Override
	public String getCommandName() {
		return "Add a vendor payment";
	}

}
