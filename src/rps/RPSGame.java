package rps;

import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.ToIntFunction;

/**
* Rock-Paper-Scissors game by Max (kimerinn@gmail.com)
* January 2019
*/

public class RPSGame {
	static private enum GAME_TOOL {PAPER, SCISSORS, ROCK}; 
	static private final String EXIT_STR = "q";

	static private final Map<String, GAME_TOOL> toolStr = new HashMap<>();
	
	static {
		toolStr.put("r", GAME_TOOL.ROCK);
		toolStr.put("s", GAME_TOOL.SCISSORS);
		toolStr.put("p", GAME_TOOL.PAPER);
	}

	static private Map<GAME_TOOL, ToIntFunction<GAME_TOOL>> toolStrikes = new HashMap<>();
	
	static {
		toolStrikes.put(GAME_TOOL.ROCK, s -> GAME_TOOL.PAPER.equals(s) ? -1 : GAME_TOOL.ROCK.compareTo(s));
		toolStrikes.put(GAME_TOOL.SCISSORS, s -> GAME_TOOL.SCISSORS.compareTo(s));
		toolStrikes.put(GAME_TOOL.PAPER, s -> GAME_TOOL.ROCK.equals(s) ? 1: GAME_TOOL.PAPER.compareTo(s));
	}

	public void gameLoop(InputStream in, OutputStream out ) throws Exception {
		try (BufferedWriter outWriter = new BufferedWriter(new OutputStreamWriter(out));
			Scanner scanner = new Scanner(in)) {
		
			int loses = 0;
			int wins = 0;
			int totals = 0;
			
			outWriter.write("\n--==[[[ Rock - Scissors - Paper]]]==--");
			outWriter.write("\n>>> Get ready, Player One! Enter 'r', 's' or 'p' to choose ROCK, SCISSORS or PAPER. Press 'q' to stop\n");
			outWriter.flush();
			
			while (scanner.hasNextLine()) {
				String playerToolStr = scanner.nextLine().trim().toLowerCase();
				GAME_TOOL compTool = GAME_TOOL.values()[((int)(Math.random()*100))%GAME_TOOL.values().length];
				
				if (EXIT_STR.equals(playerToolStr)) {
					break;
				}
				
				GAME_TOOL playerTool = toolStr.get(playerToolStr);
				
				if (playerTool == null) {
					outWriter.write("\nWrong tool! Enter 'r', 's' or 'p' to choose ROCK, SCISSORS or PAPER. Press 'q' to stop\n");
					outWriter.flush();
					continue;
				}
				
				ToIntFunction<GAME_TOOL> toolStrike = toolStrikes.get(compTool);
				outWriter.write("Computer choosed " + compTool.name() + ". Player One choosed " + playerTool.name() + "\n");
				totals++;
				int strikeResult = toolStrike.applyAsInt(playerTool);
				
				if (strikeResult < 0) {
					wins++;
					outWriter.write("Player One has won!\n>");
				}
				else if (strikeResult > 0) {
					loses++;
					outWriter.write("Player One has lose!\n>");
				}
				else {
					outWriter.write("Standoff!\n>");
				}

				outWriter.flush();
			}
			
			outWriter.write("\nPlayer One wins: " + wins);
			outWriter.write("\nPlayer One loses: " + loses);
			outWriter.write("\nTotal games: " + totals);
			outWriter.flush();
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		RPSGame rps = new RPSGame();
		rps.gameLoop(System.in, System.out);
	}
}
