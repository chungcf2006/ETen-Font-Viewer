package com.github.chungcf2006.etenfontviewer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Font {
	private int width;
	private int height;
	private FileInputStream infile;
	
	public int getCharSize() {
		return width * height;
	}
	
	public Font(File file) throws FileNotFoundException {
		infile = new FileInputStream(file);
		
		
	}
}
