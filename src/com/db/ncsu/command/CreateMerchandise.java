package com.db.ncsu.command;

import com.db.database.DatabaseManager;

public class CreateMerchandise extends Command {

	@Override
	public CommandArgument[] getArguments() {
		CommandArgument args[] = new CommandArgument[3];
		args[0] = new CommandArgument("Name","String","Merchandise Name",true);
		args[1] = new CommandArgument("Description","String","Merchandise Description",true);
		args[2] = new CommandArgument("Vendor ID","Int","Vendor ID",true);		
		return args;
	}

	@Override
	public void run(CommandArgument[] args) {
		//INSERT INTO Merchandise(id, name, description, vendorID)
		//VALUES(merch_seq.nextval, 'The Help', 'Book about Ladies that were Nannies', 1)
		String sql = "INSERT INTO Merchandise(id, name, description, vendorID) VALUES(merch_seq.nextval, ?, ?, ?)";
		DatabaseManager.runPreparedStatement(sql,args,false);
		System.out.println("MERCHANDISE ADDED!!!");	
	}

	@Override
	public String getCommandName() {
		return "Create Merchandise";
	}
	
}
