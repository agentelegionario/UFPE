package project.osms.util;

import java.io.IOException;
import java.sql.SQLException;

import org.h2.tools.Server;

public class RunDatabase {
	public static void main(String[] args) throws SQLException, IOException {
		Server server = Server.createTcpServer();
		server.start();
		System.in.read();
		server.stop();
	}
}