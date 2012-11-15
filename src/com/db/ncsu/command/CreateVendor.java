package com.db.ncsu.command;

import com.db.database.DatabaseManager;

public class CreateVendor extends Command {

	@Override
	public CommandArgument[] getArguments() {
		CommandArgument args[] = new CommandArgument[3];
		args[0] = new CommandArgument("Name","String","Vendor Name",true);		
		args[1] = new CommandArgument("Number","String","Phone Number",true);
		args[2] = new CommandArgument("Address", "String", "Address", true);

		return args;
	}

	@Override
	public void run(CommandArgument[] args) {
		//INSERT INTO Vendor(id, name, phoneNumber, address)
		//VALUES(vendor_seq.nextval, 'Vend D', '919-111-1111', '101 Way')
		String sql = "INSERT INTO Vendor(id, name, phoneNumber, address) VALUES(vendor_seq.nextval, ?, ?, ?)";
		DatabaseManager.runPreparedStatement(sql,args,false);
		System.out.println("VENDOR CREATED!!!");	
	}

	@Override
	public String getCommandName() {
		return "Create a new Vendor";
	}

}
