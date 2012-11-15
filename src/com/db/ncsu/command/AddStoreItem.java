package com.db.ncsu.command;

import com.db.database.DatabaseManager;

public class AddStoreItem extends Command {

	@Override
	public CommandArgument[] getArguments() {
		CommandArgument args[] = new CommandArgument[4];
		args[0] = new CommandArgument("Store ID","Int","Store ID",true);		
		args[1] = new CommandArgument("Merchandise ID","Int","Merchandise ID",true);
		args[2] = new CommandArgument("Quantity", "Int", "Quantity", true);
		args[3] = new CommandArgument("Price", "Float", "Price", true);

		return args;
	}

	@Override
	public void run(CommandArgument[] args) {
		//INSERT INTO StoreItem(StoreID , MerchandiseID, Quantity, Price) VALUES(1,3,1,2.13)
		String sql = "INSERT INTO StoreItem(StoreID , MerchandiseID, Quantity, Price) VALUES(?,?,?,?)";
		DatabaseManager.runPreparedStatement(sql,args,false);
		System.out.println("STORE ITEM ADDED!!!");	
	}

	@Override
	public String getCommandName() {
		return "Add a store item";
	}

}

