package rps;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.junit.jupiter.api.Test;

class RPSGameTest {
	private static final String START_LINE = "\n--==[[[ Rock - Scissors - Paper]]]==--\n>>> Get ready, Player One! Enter 'r', 's' or 'p' to choose ROCK, SCISSORS or PAPER. Press 'q' to stop\n";
	
	@Test
	void testWrongInput() throws Exception {
		RPSGame rps = new RPSGame();
		StringBuffer inBuf = new StringBuffer("d\nq\n");
		ByteArrayInputStream in = new ByteArrayInputStream(inBuf.toString().getBytes());
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		rps.gameLoop(in, out);
		assertEquals(START_LINE + "\nWrong tool! Enter 'r', 's' or 'p' to choose ROCK, SCISSORS or PAPER. Press 'q' to stop\n\nPlayer One wins: 0\nPlayer One loses: 0\nTotal games: 0", new String(out.toByteArray()));
	}

	@Test
	void testPaper() throws Exception {
		for (int i = 0; i < 20; i++) {
			RPSGame rps = new RPSGame();
			StringBuffer inBuf = new StringBuffer("p\nq\n");
			ByteArrayInputStream in = new ByteArrayInputStream(inBuf.toString().getBytes());
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			
			rps.gameLoop(in, out);
			
			String outStr = new String(out.toByteArray());
			String compTool = outStr.substring((START_LINE + "Computer choosed ").length(), outStr.indexOf(". Player One choosed"));
			
			if ("ROCK".equals(compTool)) {
				assertEquals(START_LINE + "Computer choosed ROCK. Player One choosed PAPER\nPlayer One has won!\n>\nPlayer One wins: 1\nPlayer One loses: 0\nTotal games: 1", outStr);
			}
			else if ("PAPER".equals(compTool)) {
				assertEquals(START_LINE + "Computer choosed PAPER. Player One choosed PAPER\nStandoff!\n>\nPlayer One wins: 0\nPlayer One loses: 0\nTotal games: 1", outStr);
			}
			else if ("SCISSORS".equals(compTool)) {
				assertEquals(START_LINE + "Computer choosed SCISSORS. Player One choosed PAPER\nPlayer One has lose!\n>\nPlayer One wins: 0\nPlayer One loses: 1\nTotal games: 1", outStr);
			}
		}
	}

	@Test
	void testRock() throws Exception {
		for (int i = 0; i < 20; i++) {
			RPSGame rps = new RPSGame();
			StringBuffer inBuf = new StringBuffer("r\nq\n");
			ByteArrayInputStream in = new ByteArrayInputStream(inBuf.toString().getBytes());
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			
			rps.gameLoop(in, out);
			
			String outStr = new String(out.toByteArray());
			String compTool = outStr.substring((START_LINE + "Computer choosed ").length(), outStr.indexOf(". Player One choosed"));
			
			if ("ROCK".equals(compTool)) {
				assertEquals(START_LINE + "Computer choosed ROCK. Player One choosed ROCK\nStandoff!\n>\nPlayer One wins: 0\nPlayer One loses: 0\nTotal games: 1", outStr);
			}
			else if ("PAPER".equals(compTool)) {
				assertEquals(START_LINE + "Computer choosed PAPER. Player One choosed ROCK\nPlayer One has lose!\n>\nPlayer One wins: 0\nPlayer One loses: 1\nTotal games: 1", outStr);
			}
			else if ("SCISSORS".equals(compTool)) {
				assertEquals(START_LINE + "Computer choosed SCISSORS. Player One choosed ROCK\nPlayer One has won!\n>\nPlayer One wins: 1\nPlayer One loses: 0\nTotal games: 1", outStr);
			}
		}
	}

	@Test
	void testScissors() throws Exception {
		for (int i = 0; i < 20; i++) {
			RPSGame rps = new RPSGame();
			StringBuffer inBuf = new StringBuffer("s\nq\n");
			ByteArrayInputStream in = new ByteArrayInputStream(inBuf.toString().getBytes());
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			
			rps.gameLoop(in, out);
			
			String outStr = new String(out.toByteArray());
			String compTool = outStr.substring((START_LINE + "Computer choosed ").length(), outStr.indexOf(". Player One choosed"));
			
			if ("ROCK".equals(compTool)) {
				assertEquals(START_LINE + "Computer choosed ROCK. Player One choosed SCISSORS\nPlayer One has lose!\n>\nPlayer One wins: 0\nPlayer One loses: 1\nTotal games: 1", outStr);
			}
			else if ("PAPER".equals(compTool)) {
				assertEquals(START_LINE + "Computer choosed PAPER. Player One choosed SCISSORS\nPlayer One has won!\n>\nPlayer One wins: 1\nPlayer One loses: 0\nTotal games: 1", outStr);
			}
			else if ("SCISSORS".equals(compTool)) {
				assertEquals(START_LINE + "Computer choosed SCISSORS. Player One choosed SCISSORS\nStandoff!\n>\nPlayer One wins: 0\nPlayer One loses: 0\nTotal games: 1", outStr);
			}
		}
	}
}
