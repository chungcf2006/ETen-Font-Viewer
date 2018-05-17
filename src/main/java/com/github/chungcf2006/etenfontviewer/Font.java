package com.github.chungcf2006.etenfontviewer;

import java.io.File;
import java.io.RandomAccessFile;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.graphics.RGB;

public class Font {
	private int width;
	private int height;
	private RandomAccessFile infile;
	
	public int getCharSize() {
		return (width/8) * height;
	}
	
	public Font(String filePath, int width, int height) throws FileNotFoundException {
		this(new File(filePath), width, height);
	}

	public Font(File file, int width, int height) throws FileNotFoundException {
		infile = new RandomAccessFile(file, "r");
		this.width = width;
		this.height = height;
	}
	
	private static int getOffset(int in) {
		int hi = (int) ((in >> 8) & 0xFF); // HI = 高位碼
	    int lo = (int) (in & 0XFF); // LO = 低位碼
	    int offset = 0;
	    
        int serCode = (hi - 161) * 157 + (lo >= 161 ? lo - 161 + 1 + 63 : lo - 64 + 1);
        System.out.printf("%2X, %2X, %d\n", hi, lo, serCode);
        if (serCode >= 472 && serCode < 5872)
            offset = (serCode - 472);
        else if (serCode >= 6281 && serCode <= 13973)
            offset = (serCode - 6281) + 5401;
	    System.out.println(offset);
	    return offset;
	}
	
	public byte[] readChar(int offset) throws IOException {
		byte[] b = new byte[512];
		infile.seek(offset * getCharSize());
		infile.read(b, 0, getCharSize());
		return b;
	}
	
	public ImageData getImageData(int big5) throws IOException {
		int offset = getOffset(big5);
		RGB[] rgb = new RGB[2];
		rgb[1] = new RGB(0,0,0);
		rgb[0] = new RGB(255,255,255);
		ImageData imageData = new ImageData(this.width, this.height, 1, new PaletteData(rgb));
		byte[] bytes = this.readChar(offset);
		
		for (int i = 0; i < getCharSize(); i++) {
			for (int j = 0; j < 8; j++) {
				imageData.setPixel(i%(width/8)*8+(7-j), i/(width/8), ((bytes[i] >> j) & 1));
			}
				
		}
		
		return imageData;
	}
}
