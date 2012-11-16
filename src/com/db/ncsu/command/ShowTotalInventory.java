package com.db.ncsu.command;

import com.db.database.DatabaseManager;

public class ShowTotalInventory extends Command {
	public CommandArgument[] getArguments() {
		CommandArgument args[] = new CommandArgument[0];
		return args;
	}

	@Override
	public void run(CommandArgument[] args) {
		//SELECT merchandiseID, sum(quantity)
		//FROM Store s, StoreItem  si  
		//WHERE s.id = si.storeID
		//GROUP BY merchandiseID
		String sql = "SELECT merchandiseID, sum(quantity) " +
				"FROM Store s, StoreItem  si " +
				"WHERE s.id = si.storeID " +
				"GROUP BY merchandiseID";
		DatabaseManager.runPreparedStatement(sql,args,true);
		
	}

	@Override
	public String getCommandName() {
		return "Show inventory of all stores summed";
	}
}
