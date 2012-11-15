package com.db.ncsu.command;

import com.db.database.DatabaseManager;

public class CreateStore extends Command {

	@Override
	public CommandArgument[] getArguments() {
		CommandArgument args[] = new CommandArgument[2];
		args[0] = new CommandArgument("Phone Number","String","Phone Number",true);		
		args[1] = new CommandArgument("Address","String","Address",true);

		return args;
	}

	@Override
	public void run(CommandArgument[] args) {
		//INSERT INTO Store(id, phoneNumber, address)
		//VALUES(store_seq.nextval, '919-555-1212', 'Blah Blah Way 1')
		String sql = "INSERT INTO Store(id, phoneNumber, address) VALUES(store_seq.nextval, ?, ?)";
		DatabaseManager.runPreparedStatement(sql,args,false);
		System.out.println("STORE CREATED!!!");	
	}

	@Override
	public String getCommandName() {
		return "Create a new Store";
	}
	
}
