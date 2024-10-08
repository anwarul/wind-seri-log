package com.wind.LogsToRedis_329.serilogj.formatting.display;

import com.wind.LogsToRedis_329.serilogj.parsing.Alignment;
import com.wind.LogsToRedis_329.serilogj.parsing.AlignmentDirection;

import java.io.IOException;
import java.io.Writer;

public class Padding {
	/**
	 * Writes the provided value to the output, applying direction-based padding
	 * when <paramref name="alignment"/> is provided.
	 * 
	 * @throws IOException
	 */
	public static void apply(Writer output, String value, Alignment alignment) throws IOException {
		if (alignment == null) {
			output.write(value);
			return;
		}

		int pad = alignment.getWidth() - value.length();

		if (alignment.getDirection() == AlignmentDirection.Right) {
			for (int i = 0; i < pad; i++) {
				output.write(' ');
			}
		}

		output.write(value);

		if (alignment.getDirection() == AlignmentDirection.Left) {
			for (int i = 0; i < pad; i++) {
				output.write(' ');
			}
		}
	}
}
